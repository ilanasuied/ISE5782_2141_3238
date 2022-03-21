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
public class Plane implements Geometry{
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

    /**
     *
     * @param ray Ray pointing towards the graphic object
     * @return a list of all the intersections with the object and if there isn't return  null
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = normal;

        double nv = alignZero(n.dotProduct(v));

        //if ray is parallel to plane : no intersection points
        if (isZero(nv)) {
            return null;
        }

        Vector P0_Q = P0.subtract(q0);

        double t = alignZero(n.dotProduct(P0_Q) / nv);

        //if ( t == 0) origin of ray lay on the plane
        if (isZero(t)) {
            return null;
        }

        // if (t < 0) the direction of the ray points in the opposite direction
        if (t > 0) {
            Point P = ray.getPoint(t);//  P0.add(v.scale(t));
            return List.of(P);
        }

        return null;
    }
}
