package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Cylinder  extends Tube{

    final double height;

    /**
     * cylinder constructor
     * @param  axisRay value for the Ray
     * @param radius value for the radius of the cylinder
     * @param height value for the cylinder's height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * get cylinder's height
     * @return height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     * override the function toString for cylinder
     * @return string
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
     * @return the normal of the cylinder
     */
    @Override
    public Vector getNormal(Point point) {
        Point o = axisRay.getP0();
        Vector v = axisRay.getDir();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(o.subtract(point).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return o.subtract(point).normalize();

    }
}
