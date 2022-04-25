package renderer;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracer{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    //a methods that calculate color of a point
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        Color ambientLight =scene.getAmbientLight().getIntensity();
        Color emissioncolor = geoPoint.geometry.getEmission();

        Color result = ambientLight.add(emissioncolor);
        return result;
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
