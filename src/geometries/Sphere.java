package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

public class Sphere implements Geometry
{

    final Point center;
    final double radius;

    /**
     * constructor that receive a point and a radius
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }


    /**
     * getter
     * @return point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter
     * @return double
     */
    public double getRadius() {
        return radius;
    }


    /**
     * overrided function of tostring() for sphere
     * @return
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /**
     * ovverided function get normal for sphere
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
