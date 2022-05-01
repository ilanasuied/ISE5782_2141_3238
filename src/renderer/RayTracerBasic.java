package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracer{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    //a methods that calculate color of a point
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        Color ambientLight =scene.getAmbientLight().getIntensity();
        Color emissioncolor = geoPoint.geometry.getEmission();
        Color result = ambientLight.add(emissioncolor)
        .add(calcLocalEffects(geoPoint, ray));
        return result;
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);//a voirrrrrrrrrrrrrrrrrrrrrrr
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getShininess();
        double kd = intersection.geometry.getMaterial().getKd(), ks = intersection.geometry.getMaterial().getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntesity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    @Override
    public Color traceRay(Ray ray) {

        //for instance result = background
        Color result = scene.getBackground();

        //create a list of all the points who's in the intersections
        List<GeoPoint> allPoints = scene.getGeometries().findGeoIntersections(ray);

        //until the list isn't ended
        if(allPoints != null){
            //find the closest point
            GeoPoint pt = ray.findClosestPoint(allPoints);
            //result = the color of the closest point
            result = calcColor(pt,ray);
        }
        //return the color
        return result;
    }

}
