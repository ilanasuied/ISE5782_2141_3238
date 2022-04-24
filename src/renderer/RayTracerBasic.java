package renderer;

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
    private Color calcColor(Point point) {
        return scene.getAmbientLight().getIntensity();
    }


    @Override
    public Color traceRay(Ray ray) {

        //for instance result = background
        Color result = scene.getBackground();

        //create a list of all the points who's in the intersections
        List<Point> allPoints = scene.getGeometries().findIntersections(ray);

        //until the list isn't ended
        if(allPoints != null){
            //find the closest point
            Point pt = ray.findClosestPoint(allPoints);
            //result = the color of the closest point
            result = calcColor(pt);
        }
        //return the color
        return result;
    }

}
