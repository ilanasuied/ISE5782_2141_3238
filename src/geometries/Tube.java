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


    /**
     * function that helps finding geopoint intersections for tube
     * @param ray ray intersecting the geometry
     * @param maxDistance maximum distance to look for intersections geometries
     * @return list of the geometries that comes in intersection, if there isn't, return null
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Vector vAxis = axisRay.getDir();
        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        // At^2+Bt+C=0
        double a = 0;
        double b = 0;
        double c = 0;

        double vVa = alignZero(v.dotProduct(vAxis));
        Vector vVaVa;
        Vector vMinusVVaVa;
        if (vVa == 0) // the ray is orthogonal to the axis
            vMinusVVaVa = v;
        else {
            vVaVa = vAxis.scale(vVa);
            try {
                vMinusVVaVa = vVaVa.subtract(v);
            } catch (IllegalArgumentException e1) { // the rays is parallel to axis
                return null;
            }
        }
        // A = (v-(v*va)*va)^2
        a = vMinusVVaVa.lengthSquared();

        Vector deltaP = null;
        try {
            deltaP = p0.subtract(axisRay.getP0());
        } catch (IllegalArgumentException e1) { // the ray begins at axis P0
            if (vVa == 0 && alignZero(radius - maxDistance) <= 0) { // the ray is orthogonal to Axis
                return List.of(new GeoPoint(this, ray.getPoint(radius)));
            }
            double t = alignZero(Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared()));
            return alignZero(t - maxDistance) >= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
        }

        double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
        Vector dPVaVa;
        Vector dPMinusdPVaVa;
        if (dPVAxis == 0)
            dPMinusdPVaVa = deltaP;
        else {
            dPVaVa = vAxis.scale(dPVAxis);
            try {
                dPMinusdPVaVa = deltaP.subtract(dPVaVa);
            } catch (IllegalArgumentException e1) {
                double t = alignZero(Math.sqrt(radius * radius / a));
                return alignZero(t - maxDistance) >= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
        c = dPMinusdPVaVa.lengthSquared() - radius * radius;

        // A*t^2 + B*t + C = 0 - lets resolve it
        double discr = alignZero(b * b - 4 * a * c);
        if (discr <= 0) return null; // the ray is outside or tangent to the tube

        double doubleA = 2 * a;
        double tm = alignZero(-b / doubleA);
        double th = Math.sqrt(discr) / doubleA;
        if (isZero(th)) return null; // the ray is tangent to the tube

        double t1 = alignZero(tm + th);
        if (t1 <= 0) // t1 is behind the head
            return null; // since th must be positive (sqrt), t2 must be non-positive as t1

        double t2 = alignZero(tm - th);

        // if both t1 and t2 are positive
        if (t2 > 0 && alignZero(t2 - maxDistance) < 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)),new GeoPoint(this, ray.getPoint(t2)));
        else if (alignZero(t1 - maxDistance) < 0)// t2 is behind the head
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        return null;
    }
}
