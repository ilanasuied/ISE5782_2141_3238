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

        return null;
    }
}
