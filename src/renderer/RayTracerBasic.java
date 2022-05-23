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
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, material,n,v,nv, level, k));
    }

    /**
     * //add here the lights effects
     *
     * @param gp  geopoint of the intersection
     * @param v ray direction
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
                            calcDiffusive(material.getKd(), nl,iL),
                            calcSpecular(material.getKs(), n, l, nl, v,material.getShininess(),iL));
                }
            }
        }
        return color;
    }


    private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl,Vector v,int shininess,Color intensity) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK; // view from direction opposite to r vector
        Double3 amount =kS.scale(Math.pow(minusVR, shininess));
        return intensity.scale(amount);
    }

    private Color calcDiffusive(Double3 kD, double nl,  Color intensity) {
        double abs_nl = Math.abs(nl);
        Double3 amount =kD.scale(abs_nl);
        return intensity.scale(amount);
    }

    private Double3 transparency(LightSource lightSource, Vector l, Vector n, GeoPoint gp) {
        // Pay attention to your method of distance screening
        Vector lightDirection = l.scale(-1); // from point to light source
        Point point = gp.point;
        Ray lightRay = new Ray(point, n, lightDirection);

        double maxDistance = lightSource.getDistance(point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxDistance);

        if (intersections == null)
            return Double3.ONE;

        Double3 ktr = Double3.ONE;
//        loop over intersections and for each intersection which is closer to the
//        point than the light source multiply ktr by 𝒌𝑻 of its geometry.
//        Performance:
//        if you get close to 0 –it’s time to get out( return 0)
        for (var geo : intersections) {
            ktr = ktr.product(geo.geometry.getMaterial().getKt());
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
                return Double3.ZERO;
            }
        }
        return ktr;
    }

    private Color calcGlobalEffects(GeoPoint gp,Material material, Vector n, Vector v, double nv, int level, Double3 k) {
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


//package renderer;
//
//import geometries.Intersectable.GeoPoint;
//import geometries.Triangle;
//import lighting.LightSource;
//import primitives.*;
//import scene.Scene;
//
//import java.util.List;
//
//import static primitives.Util.alignZero;
//import static primitives.Util.isZero;
//
///**
// * RayTracerBasic extends RayTracer calculate color and global effect
// */
//public class RayTracerBasic extends RayTracer {
//
//    private static final double DELTA = 0.1;  //constant to move shadow's rays head
//    private static final int MAX_CALC_COLOR_LEVEL = 10;
//    private static final Double3 INITIAL_K = Double3.ONE;
//    /**
//     * function to check unshaded places between a point and the light source
//     *
//     * @param gp value for geo point
//     * @param l  vector of the light
//     * @param n  value for the normal
//     * @param nv dot product of the normal vector and light vector
//     * @return if the area is unshaded or not
//     */
//    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {
//        Vector lightDirection = l.scale(-1); // from point to light source
//        Vector deltaVector = n.scale(nv < 0 ? DELTA : -DELTA);
//        Point point = gp.point.add(deltaVector);
//        Ray lightRay = new Ray(point, lightDirection);
//
//        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay);
//        if (intersections != null) //if there are points in the intersections list that are closer to the point than light source, return false
//        {
//            double dis = lightSource.getDistance(point); //the distance between the point and the ray's head
//            for (GeoPoint var : intersections) {
//                if (var.point.distance(point) < dis)
//                    return false;
//            }
//        }
//        //else return true
//        return true;
//
//    }
//
//
//    /**
//     * RayTracerBasic's construct
//     *
//     * @param scene for the scene
//     */
//    public RayTracerBasic(Scene scene) {
//        super(scene);
//    }
//
//
//    /**
//     * a methods that calculate color of a point
//     *
//     * @param geopoint the value for geoPoint
//     * @param ray      value Ray
//     * @return the correct color all the calculus
//     */
//    private Color calcColor(GeoPoint geopoint, Ray ray) {
//        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
//                .add(scene.getAmbientLight().getIntensity());
//    }
//
//
//    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
//        Color color = geoPoint.geometry.getEmission();
//
//        Vector v = ray.getDir();
//        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
//
//        // check that ray is not parallel to geometry
//        double nv = alignZero(n.dotProduct(v));
//
//        if (isZero(nv)) {
//            return color;
//        }
//        Material material = geoPoint.geometry.getMaterial();
//        color = color.add(calcLocalEffects(geoPoint, material, n, v, nv, k));
//        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, material,n,v,nv, level, k));
//    }
//    private Color calcGlobalEffects(GeoPoint gp,Material material, Vector n, Vector v, double nv, int level, Double3 k) {
//        Color color = Color.BLACK;
//        Double3 kkr = material.getKr().product(k);
//        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
//            color = color.add(calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.getKr(), kkr));
//        Double3 kkt = material.getKt().product(k);
//        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
//            color = color.add(
//                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.getKt(), kkt));
//        return color;
//    }
//
//
//    /**
//     * this function calculate the local effects on the color
//     *
//     * @param intersection a geoPoint value for point intersected
//     * @param ray          value for the ray
//     * @return the correct color after all the calculating
//     */
//    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
//
//        Vector v = ray.getDir();
//        Vector n = intersection.geometry.getNormal(intersection.point);//the normal
//        double nv = alignZero(n.dotProduct(v));
//
//        // if nv is equal to zero we have to return the background
//        if (nv == 0)
//            return Color.BLACK;
//
//        int nShininess = intersection.geometry.getMaterial().getShininess();
//        Double3 kd = intersection.geometry.getMaterial().getKd();
//        Double3 ks = intersection.geometry.getMaterial().getKs();
//
//        Color color = Color.BLACK;
//        // a ecrire a zut
//        //
//        for (LightSource lightSource : scene.lights) {
//            Vector l = lightSource.getL(intersection.point);
//            double nl = alignZero(n.dotProduct(l));
//
//            if (nl * nv > 0) { // sign(nl) == sign(nv)
//                if (unshaded(intersection, lightSource, l, n, nv)) {
//                    Color lightIntensity = lightSource.getIntensity(intersection.point);
//                    color = color.add(calcDiffusive(kd, nl, lightIntensity),
//                            calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
//                }
//            }
//        }
//        //return the correct color
//        return color;
//    }
//
//
//    /**
//     * @param ks             factor of the diffusion
//     * @param l              vector of the light
//     * @param n              the normal
//     * @param nl             the dot product between the light and the normal
//     * @param v              the vector of the direction
//     * @param nShininess     value of the shininess
//     * @param lightIntensity the color of the intensity
//     * @return the final color of lightIntensity
//     */
//    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color lightIntensity) {
//        // r = l - 2 * (l * n) * n
//        Vector r = ((n.scale(nl)).scale(2d)).subtract(l);
//        double minusVR = -alignZero(r.dotProduct(v));
//        if (minusVR <= 0)
//            return Color.BLACK; // view from direction opposite to r vector
//        Double3 factor = ks.scale(Math.pow(minusVR, nShininess));
//        return lightIntensity.scale(factor);
//    }
//
//
//    /**
//     * this function calculate the diffusive light
//     *
//     * @param kd             value for diffusing factor
//     * @param nl             the dot product between the normal and l
//     * @param lightIntensity value of the color
//     * @return the diffusing factor
//     */
//    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
//        Double3 factor = kd.scale(Math.abs(nl));
//        Color diffusive = lightIntensity.scale(factor);
//        return diffusive;
//    }
//
//    /**
//     * function that check the color of the closest point that the ray come in interaction with
//     *
//     * @param ray value for a ray
//     * @return the color of the closest point
//     */
//    @Override
//    public Color traceRay(Ray ray) {
//
//        //for instance result = background
//        Color result = scene.getBackground();
//
//        //create a list of all the points who's in the intersections
//        List<GeoPoint> allPoints = scene.getGeometries().findGeoIntersections(ray);
//
//        //until the list isn't ended
//        if (allPoints != null) {
//            //find the closest point
//            GeoPoint pt = ray.findClosestPoint(allPoints);
//            //result = the color of the closest point
//            result = calcColor(pt, ray);
//        }
//        //return the color
//        return result;
//    }
//
//}
