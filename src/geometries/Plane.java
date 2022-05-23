package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class for a 3D plane
 */
public class Plane extends Geometry{
   final  Vector normal;
   final  Point q0 ;


    /**
     * Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming an arc in right direction
     * @param p1 first point to construct a plane
     * @param p2 second point to construct a plane
     * @param p3 third point to construct a plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        q0 = p1;

        Vector U=p2.subtract(p1);
        Vector V=p3.subtract(p1);

        Vector N=U.crossProduct(V);
        normal=N.normalize();
    }

    /**
     *
     * @param p value for the point on the plane
     * @param normal of the plane
     */
    public Plane(Point p,Vector normal) {
        this.normal = normal.normalize();
        q0 = p;
    }

    /**
     * getter for normal vector
     * @return normal
     */
    public Vector getNormal(){
        return normal;
    }

    /**
     * getter for q0 referenced point
     * @return the referenced point of the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * implementing {@link Geometry#getNormal(Point)}
     * @param point reference point
     * @return normal vector to the plane
     */
    @Override
    public Vector getNormal(Point point)
    {
        return getNormal();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = normal;
        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }

        //ray cannot start from the plane
        if (q0.equals(P0)) {
            return null;
        }

        Vector P0_Q0 = P0.subtract(q0);

        //numerator
        double nP0Q0 = alignZero(n.dotProduct(P0_Q0));

        // ray parallel to the plane
        if (isZero(nP0Q0)) {
            return null;
        }

        double t = alignZero(nP0Q0 / nv);

        if (t < 0 ||  alignZero(t - maxDistance) > 0) {
            return null;
        }


        Point point = ray.getPoint(t);

        return List.of(new GeoPoint(this, point));
    }
}
