package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
   final private Vector normal;
    private Point q0;

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
    }

    /**
     * function that get the normal
     * @return normal
     */
    public Vector getNormal(){
        return normal;
    }

    /**
     * override getNormal
     * @param point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point)
    {
        return getNormal();
    }
}
