package renderer;

import geometries.Intersectable.*;
import primitives.*;
import lighting.LightSource;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class RayTracerBasic extends RayTracer {

    private static final double EPS = 0.1;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final Double3 INITIAL_K = Double3.ONE;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * method to trace a ray
     *
     * @param rays a list of ray
     * @return the correct color
     */
    @Override
    public Color traceRays(List<Ray> rays) {
        Color sumColor = Color.BLACK;
        for (Ray ray : rays) {
            GeoPoint closestPoint = findClosestIntersection(ray);
            if (closestPoint != null) {
                sumColor = sumColor.add(calcColor(closestPoint, ray));
            } else {
                sumColor = sumColor.add(scene.getBackground(
                ));
            }
        }
        Color result = sumColor.reduce(rays.size());
        return result;
    }

    /**
     * method to trace a ray
     *
     * @param ray a list of ray
     * @return the correct color
     */
    @Override
    public Color traceRay(Ray ray) {
        Color result;
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint != null) {
            result = calcColor(closestPoint, ray);
        } else {
            result = scene.getBackground();
        }

        return result;
    }

    /**
     * this function calculate the correct color
     *
     * @param geopoint value for GeoPoint
     * @param ray      value for the ray
     * @return the correct color
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        Color result = calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.getAmbientLight().getIntensity());
        return result;
    }

    /**
     * method to calculate color with more parameters
     *
     * @param geoPoint value for GeoPoint
     * @param ray      value for the ray
     * @param level    the max level time to do the recursion
     * @param k        helper value to send to calcLocalEffects function
     * @return the correct color
     */
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
        Color result = 1 == level ? color : color.add(calcGlobalEffects(geoPoint, material, n, v, nv, level, k));
        return result;
    }

    /**
     * add here the lights effects
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
                Double3 ktr = transparency(lightSource, l, n, gp);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
//                if (unshaded(gp, lightSource, l, n,nv)) {
                    Color iL = lightSource.getIntensity(point).scale(ktr);
                    color = color.add(
                            calcDiffusive(material.getKd(), nl, iL),
                            calcSpecular(material.getKs(), n, l, nl, v, material.getShininess(), iL));
                }
            }
        }
        return color;
    }

    /**
     * this function calculate the specular color
     *
     * @param kS        value for the specular color
     * @param n         value for the normal vector
     * @param l         value for the vector l
     * @param nl        the dotProduct between l and the normal
     * @param v         value for the vector v
     * @param shininess value for the shininess
     * @param intensity value for the intensity
     * @return the correct color
     */
    private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl, Vector v, int shininess, Color intensity) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK; // view from direction opposite to r vector
        Double3 amount = kS.scale(Math.pow(minusVR, shininess));
        Color result = intensity.scale(amount);
        return result;
    }

    /**
     * this function calculate the diffuse color
     *
     * @param kD        value for the diffusion's level
     * @param nl        the dotProduct between the normal and  the vector l
     * @param intensity the intensity color
     * @return the diffuse color
     */
    private Color calcDiffusive(Double3 kD, double nl, Color intensity) {
        double abs_nl = Math.abs(nl);
        Double3 amount = kD.scale(abs_nl);
        Color result = intensity.scale(amount);
        return result;
    }

    /**
     * this function calculate the correct color affected by the global effects
     *
     * @param gp       value for the GeoPoint
     * @param material value for Material
     * @param n        the normal vector
     * @param v        value for the vector v
     * @param nv       the dotProduct between the normal and the vector v
     * @param level    the maximum level of the recursion
     * @param k        a helper value to send in the calcGlobalEffects function
     * @return the correct color affected by the global effects
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
     * method taht calculate global effect
     *
     * @param ray   value for Ray
     * @param level helper counter
     * @param kx    helper counter
     * @param kkx   helper counter
     * @return the correct color
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * the refracted ray constructor
     *
     * @param point value for Point
     * @param v     value for the vector v
     * @param n     value fot the normal
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, n, v);
    }

    /**
     * the reflected ray constructed
     *
     * @param pointGeo value for Point
     * @param v        value for the vector v
     * @param n        value for the normal vector
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Point pointGeo, Vector v, Vector n) {
        //r = v - 2.(v.n).n
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = n.scale(2 * vn).subtract(v);
        return new Ray(pointGeo, n, r);
    }

    /**
     * The method checks whether there is any object shading the light source from a
     * point
     *
     * @param gp          the point with its geometry
     * @param lightSource light source
     * @param l           direction from light to the point
     * @param n           normal vector to the surface of gp
     * @param nv          dotproduct between n and ray direction
     * @return accumulated transparency attenuation factor
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {

        Vector lightDirection = l.scale(-1); // from point to light source
        double nl = n.dotProduct(lightDirection);

        Vector delta = n.scale(nl > 0 ? EPS : -EPS);
        Point pointRay = gp.point.add(delta);
        Ray lightRay = new Ray(pointRay, lightDirection);

        double maxdistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxdistance);

        if (intersections == null) {
            return true;
        }

        for (var item : intersections) {
            if (item.geometry.getMaterial().getKt().lowerThan(MIN_CALC_COLOR_K)) {
                return false;
            }
        }

        return true;
    }


    /**
     * The method checks whether there is any object shading the light source from a
     * point
     *
     * @param gp          the point with its geometry
     * @param lightSource light source
     * @param l           direction from light to the point
     * @param n           normal vector from the surface towards the geometry
     * @return accumulated transparency attenuation factor
     */
    private Double3 transparency(LightSource lightSource, Vector l, Vector n, GeoPoint gp) {
        // Pay attention to your method of distance screening
        Vector lightDirection = l.scale(-1); // from point to light source
        Point point = gp.point;
        Ray lightRay = new Ray(point, n, lightDirection);

        double maxdistance = lightSource.getDistance(point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxdistance);

        if (intersections == null)
            return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (var geo : intersections) {
            ktr = ktr.product(geo.geometry.getMaterial().getKt());
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * this function return the closest point that comes in intersection this the ray
     *
     * @param ray value for Ray
     * @return the closest point that comes in intersection
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        GeoPoint result = ray.findClosestGeoPoint(intersections);
        return result;
    }


}

