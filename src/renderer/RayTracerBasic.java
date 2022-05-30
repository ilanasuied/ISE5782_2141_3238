package renderer;

import geometries.Intersectable.*;
import primitives.lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class RayTracerBasic extends RayTracer {

    //those two methods will help us for the stop conditions in the recursion
    private static final double EPS = 0.1;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final Double3 INITIAL_K = Double3.ONE;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null) {
            return scene.getBackground();
        }
        return calcColor(closestPoint, ray);

    }

    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.getAmbientLight().getIntensity());
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission();

        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);

        // check that ray is not parallel to geometry
        double nv = alignZero(n.dotProduct(v));

        if (isZero(nv)) {
            return color;
        }
        Material material = geoPoint.geometry.getMaterial();
        color = color.add(calcLocalEffects(geoPoint, material, n, v, nv, k));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, material, n, v, nv, level, k));
    }

    /**
     * //add here the lights effects
     *
     * @param gp geopoint of the intersection
     * @param v  ray direction
     * @return resulting color with diffuse and specular
     */
    private Color calcLocalEffects(GeoPoint gp, Material material, Vector n, Vector v, double nv, Double3 k) {
        Color color = Color.BLACK;

        Point point = gp.point;

        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(point).scale(ktr);
                    color = color.add(
                            calcDiffusive(material.getKd(), nl, iL),
                            calcSpecular(material.getKs(), n, l, nl, v, material.getShininess(), iL));
                }
            }
        }
        return color;
    }


    private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl, Vector v, int shininess, Color intensity) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK; // view from direction opposite to r vector
        Double3 amount = kS.scale(Math.pow(minusVR, shininess));
        return intensity.scale(amount);
    }

    private Color calcDiffusive(Double3 kD, double nl, Color intensity) {
        double abs_nl = Math.abs(nl);
        Double3 amount = kD.scale(abs_nl);
        return intensity.scale(amount);
    }

    /**
     * The function calculates the transparency of a point on a geometry, by checking if there are any other geometries
     * between the point and the light source
     *
     * @param geoPoint The point on the geometry that we're currently shading.
     * @param ls       the light source
     * @param l        the vector from the point to the light source
     * @param n        the normal vector of the point on the geometry
     * @return The transparency of the point.
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = ls.getDistance(geoPoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return new Double3(1.0); //no intersection
        Double3 ktr = new Double3(1.0);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                //ktr *= gp.geometry.getMaterial().kT;
                ktr = ktr.product(gp.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * @param gp       value for geoPoint
     * @param material value for material
     * @param n        value of the normal
     * @param v        value of the directional vector
     * @param nv       the dotProduct between the normal and the directional vector
     * @param level    the value for the stop condition on the loop
     * @param k        initial k -> 1.0
     * @return the correct global effect
     */
    private Color calcGlobalEffects(GeoPoint gp, Material material, Vector n, Vector v, double nv, int level, Double3 k) {
        Color color = Color.BLACK;
        Double3 kkr = material.getKr().product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.getKr(), kkr));
        Double3 kkt = material.getKt().product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.getKt(), kkt));
        return color;
    }


    /**
     * @param ray   value for ray
     * @param level the value for stopped the loop
     * @param kx    value for kx
     * @param kkx   value for kkx
     * @return if there isn't intersection's point return the color of the background, else go to calculate the right color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }


    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, n, v);
    }

    private Ray constructReflectedRay(Point pointGeo, Vector v, Vector n) {
        //r = v - 2.(v.n).n
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = n.scale(2 * vn).subtract(v);
        return new Ray(pointGeo, n, r);
    }


    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {

        Vector lightDirection = l.scale(-1); // from point to light source
        double nl = n.dotProduct(lightDirection);

        Vector delta = n.scale(nl > 0 ? EPS : -EPS);
        Point pointRay = gp.point.add(delta);
        Ray lightRay = new Ray(pointRay, lightDirection);

        double maxDistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxDistance);

        return intersections == null;
    }


    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        return ray.findClosestGeoPoint(intersections);
    }

}
