package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

public class Triangle extends Polygon
{
    /**
     * constructor with 3 points for triangle
     * @param p1 value for first point
     * @param p2 value for second point
     * @param p3 value for third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }


    /**
     * function that returns a string for triangle
     * @return string
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * find all the intersections between the ray with the objects and saves them in a list
     * @param ray Ray pointing towards the graphic object
     * @return the list of the intersections
     */
//    @Override
//    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
//        var list = super.findGeoIntersectionsHelper(ray);
//        if(list == null){
//            return null;
//        }
//        return List.of(new GeoPoint(this, list.get(0).point));
//    }



    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> planeIntersections = plane.findGeoIntersectionsHelper(ray, maxDistance);
        if (planeIntersections == null) return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            planeIntersections.get(0).geometry = this;
            return planeIntersections;
        }
        return null;
    }
}
