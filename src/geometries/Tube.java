package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry
{
    final Ray axisRay;
    final double radius;

    /**
     * tube's constructor
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
        if(radius<0)
        {
            throw new IllegalArgumentException("a tube cannot receive a negative radius");
        }
    }

    /**
     * get function
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * override the function toString for tube
     * @return a str
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * override the getNormal for tube
     * @param point point
     * @return null
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
