package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

public class Triangle extends Polygon {
    final Point p0;
    final Point p1;
    final Point p2;

    /**
     * constructor with 3 points for triangle
     *
     * @param p1 value for first point
     * @param p2 value for second point
     * @param p3 value for third point
     */
    public Triangle(Point p1, Point p2, Point p3) {

        super(p1, p2, p3);
        this.p0 = p1;
        this.p1 = p2;
        this.p2 = p3;
    }


    /**
     * function that returns a string for triangle
     *
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
     *
     * @param ray ray intersecting the geometry
     * @param maxDistance maximum distance to look for intersections geometries
     * @return list of the geometries that comes in intersection, if there isn't, return null
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray, maxDistance);
        if (planeIntersections == null)
            return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = this.p0.subtract(p0);
        Vector v2 = this.p1.subtract(p0);
        Vector v3 = this.p2.subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1))
            return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2))
            return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3))
            return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            Point point = planeIntersections.get(0).point;
            return List.of(new GeoPoint(this, point));
        }
        return null;
    }
}
