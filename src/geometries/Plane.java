package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    private Vector normal;
    private Point q0;
    public Plane(Point p1, Point p2, Point p3) {
        Vector U=p2.subtract(p1);
        Vector V=p3.subtract(p1);
        Vector N=U.crossProduct(V);
        normal=N.normalize();
    }
    public Vector getNormal(){
        retrun normal;
    }
    @Override
    public Vector getNormal(Point point)
    {
        return getNormal();
    }
}
