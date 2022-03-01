package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


public class Cylinder  extends Tube{

    final double height;

    /**
     * cylinder constructor
     * @param axisRay
     * @param radius
     * @param height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * get height
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * override the function toString for cylinder
     * @return null
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * override the getNormal function for cylinder
     * @param point point
     * @return null
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
