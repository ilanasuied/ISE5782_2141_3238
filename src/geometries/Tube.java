package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Tube extends Geometry
{
    final Ray axisRay;
    final double radius;

    /**
     * tube's constructor
     * @param axisRay value for ray
     * @param radius value for radius
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
     * getter for axisRay
     * @return value of axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * radius's getter
     * @return value of the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * function that returns a string
     * @return a string
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
     * @param point value for point
     * @return the normal of the tube in this point
     */
    @Override
    public Vector getNormal(Point point) {

        Point p0= axisRay.getP0();
        Vector v = axisRay.getDir();

        //calculate the vector from p0 t0 p
        Vector P0_P = p0.subtract(point);

        //if the number almost equal to zero return zero else return the number itself
        double t = alignZero(v.dotProduct(P0_P));

        //if the number is equal to 0, normalize the vector and return  it
        if (isZero(t)) {
            return P0_P.normalize();
        }

        //else

        //add to p0 the vector of the direction multiply by t
        Point o = p0.add(v.scale(t));

        //check if the new point isn't on the axis
        if (point.equals(o)) {
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }

        //to get the normal, subtract from o the point
        Vector n = o.subtract(point).normalize();

        //return the normal
        return n;


    }

    @Override
    /**
     * function to find intersections
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxdistance) {return null;
    }
}
