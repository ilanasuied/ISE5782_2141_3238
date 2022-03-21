package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

public class Sphere implements Geometry
{

    final Point _center;
    final double radius;

    /**
     * constructor that receive a point and a radius
     * @param center value for the point that represent the center
     * @param radius value for the radius
     */
    public Sphere(Point center, double radius) {
        this._center = center;
        this.radius = radius;
    }


    /**
     * getter
     * @return point
     */
    public Point getCenter() {
        return _center;
    }

    /**
     * getter
     * @return double
     */
    public double getRadius() {
        return radius;
    }


    /**
     * function that returns a string for the sphere
     * @return string
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + _center +
                ", radius=" + radius +
                '}';
    }

    /**
     * function to get the normal of the sphere
     * @param point
     * @return the normal of the sphere
     */
    @Override
    public Vector getNormal(Point point) {

        return ((_center.subtract(point)).normalize());
    }

    /**
     * find all the intersections between the ray with the objects and saves them in a list
     * @param ray Ray pointing towards the graphic object
     * @return the list of the intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(_center)) {
            return List.of(_center.add(v.scale(radius)));
        }

        Vector U = P0.subtract(_center);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
//            Point3D P1 = P0.add(v.scale(t1));
//            Point3D P2 = P0.add(v.scale(t2));
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(P1, P2);
        }
        if (t1 > 0) {
//            Point3D P1 = P0.add(v.scale(t1));
            Point P1 =ray.getPoint(t1);
            return List.of(P1);
        }
        if (t2 > 0) {
//            Point3D P2 = P0.add(v.scale(t2));
            Point P2 =ray.getPoint(t2);
            return List.of(P2);
        }
        return null;
    }
}
