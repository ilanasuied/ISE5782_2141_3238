package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracer {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    //a methods that calculate color of a point
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color ambientLight = scene.getAmbientLight().getIntensity();
        Color emissioncolor = geoPoint.geometry.getEmission();
        Color result = ambientLight.add(emissioncolor)
                .add(calcLocalEffects(geoPoint, ray));
        return result;
    }

    /**
     * this function calculate the local effects on the color
     *
     * @param intersection a geoPoint value for ..................
     * @param ray          the ray that ...................................
     * @return the correct color after all the calculating
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);//a voirrrrrrr c'est le normal
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;

        int nShininess = intersection.geometry.getMaterial().getShininess();
        Double3 kd = intersection.geometry.getMaterial().getKd();
        Double3 ks = intersection.geometry.getMaterial().getKs();

        Color color = Color.BLACK;
        // a ecrire a zut
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n, nl,v, nShininess, lightIntensity));
            }
        }
        return color;
    }

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
     * @param kd
     * @param nl
     * @param lightIntensity
     * @return
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
