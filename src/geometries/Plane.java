package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
   final  Vector normal;
   final  Point q0 = null;


    /**
     * plane's constructor
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector U=p2.subtract(p1);
        Vector V=p3.subtract(p1);
        Vector N=U.crossProduct(V);
        normal=N.normalize();
        if(normal.length()!=1)
        {
            throw new IllegalArgumentException("ERROR- normal must be equal to one");
        }
    }

    public Plane(Point p,Vector normal) {
        this.normal = normal;
    }

    /**
     *
     * @return normal
     */
    public Vector getNormal(){
        return normal;
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
}
