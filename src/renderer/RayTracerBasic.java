package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * RayTracerBasic extends RayTracer calculate color and global effect
 */
public class RayTracerBasic extends RayTracer {

    private static final double DELTA = 0.1;  //constant to move shadow's rays's head

    /**
     * function to check unshaded places between a point and the light source
     * @param gp value for geoPoint
     * @param l vector of the light
     * @param n the normal
     * @return if the area is shaded or not
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv)
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) return true;
        //if there are points in the intersections list that are closer to the point
        // than light source – return false
        //otherwise – return true
    }




}

    /**
     * RayTracerBasic's construct
     *
     * @param scene for the scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     * a methods that calculate color of a point
     *
     * @param geoPoint the value for geoPoint
     * @param ray value Ray
     * @return the correct color all the calculus
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color ambientLight = scene.getAmbientLight().getIntensity();
        Color emissionColor = geoPoint.geometry.getEmission();
        Color result = ambientLight.add(emissionColor)
                .add(calcLocalEffects(geoPoint, ray));
        return result;
    }

    /**
     * this function calculate the local effects on the color
     *
     * @param intersection a geoPoint value for point intersected
     * @param ray value for the ray
     * @return the correct color after all the calculating
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {

        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);//a voirrrrrrr c'est le normal
        double nv = alignZero(n.dotProduct(v));

        // if nv is equal to zero we have to return the background
        if (nv == 0)
            return Color.BLACK;

        int nShininess = intersection.geometry.getMaterial().getShininess();
        Double3 kd = intersection.geometry.getMaterial().getKd();
        Double3 ks = intersection.geometry.getMaterial().getKs();

        Color color = Color.BLACK;
        // a ecrire a zut
        //
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n, nl, v, nShininess, lightIntensity));
            }
        }
        //return the correct color
        return color;
    }

    /**
     *
     * @param ks factor of the diffusion
     * @param l vector of the light
     * @param n the normal
     * @param nl the dot product between the light and the normal
     * @param v the vector of the direction
     * @param nShininess value of the shininess
     * @param lightIntensity the color of the intensity
     * @return the final color of lightIntensity
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color lightIntensity) {
        // r = l - 2 * (l * n) * n
        Vector r = ((n.scale(nl)).scale(2d)).subtract(l);
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK; // view from direction opposite to r vector
        Double3 factor = ks.scale(Math.pow(minusVR, nShininess));
        return lightIntensity.scale(factor);
    }

    /**
     * this function calculate the diffusive light
     *
     * @param kd value for diffusing factor
     * @param nl the dot product between the normal and l
     * @param lightIntensity value of the color
     * @return the diffusing factor
     */
    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
        Double3 factor = kd.scale(Math.abs(nl));
        Color diffusive = lightIntensity.scale(factor);
        return diffusive;
    }

    /**
     * function that check the color of the closest point that the ray come in interaction with
     *
     * @param ray value for a ray
     * @return the color of the closest point
     */
    @Override
    public Color traceRay(Ray ray) {

        //for instance result = background
        Color result = scene.getBackground();

        //create a list of all the points who's in the intersections
        List<GeoPoint> allPoints = scene.getGeometries().findGeoIntersections(ray);

        //until the list isn't ended
        if (allPoints != null) {
            //find the closest point
            GeoPoint pt = ray.findClosestPoint(allPoints);
            //result = the color of the closest point
            result = calcColor(pt, ray);
        }
        //return the color
        return result;
    }

}
