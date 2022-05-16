package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

public class Sphere extends Geometry {

    final Point _center;
    final double radius;

    /**
     * constructor that receive a point and a radius
     *
     * @param center value for the point that represent the center
     * @param radius value for the radius
     */
    public Sphere(Point center, double radius) {
        this._center = center;
        this.radius = radius;
    }


    /**
     * getter for center
     *
     * @return center's point
     */
    public Point getCenter() {
        return _center;
    }

    /**
     * getter for radius
     *
     * @return radius
     */
    public double getRadius() {
        return radius;
    }


    /**
     * function that returns a string for the sphere
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + _center +
                ", radius=" + radius +
                '}';
    }

    /**
     * function to get the normal of the sphere
     *
     * @param point value for point
     * @return the normal of the sphere
     */
    @Override
    public Vector getNormal(Point point) {

        return ((_center.subtract(point)).normalize());
    }

    /**
     * find all the intersections between the ray with the objects and saves them in a list
     *
     * @param ray Ray pointing towards the graphic object
     * @return the list of the intersections
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, (ray.getPoint(this.radius))));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this.radius * this.radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        double t1dist = alignZero(maxDistance - t1);
        double t2dist = alignZero(maxDistance - t2);

        if (t1 <= 0 && t2 <= 0) {
            return null;
        }

        if (t1 > 0 && t2 > 0) {
            if (t1dist > 0 && t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getPoint(t1))),
                        new GeoPoint(this, (ray.getPoint(t2)))); //P1 , P2
            } else if (t1dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getPoint(t1))));
            } else if (t2dist > 0) {
                return List.of(
                        new GeoPoint(this, (ray.getPoint(t2))));
            }
        }

        if ((t1 > 0) && (t1dist > 0))
            return List.of(new GeoPoint(this, (ray.getPoint(t1))));
        else if ((t2 > 0) && (t2dist > 0))
            return List.of(new GeoPoint(this, (ray.getPoint(t2))));
        return null;
    }
//    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
//        Point P0 = ray.getP0();
//        Vector v = ray.getDir();
//
//        if (P0.equals(_center)) {
//            return List.of(new GeoPoint(this, _center.add(v.scale(radius))));
//        }
//
//        Vector U = P0.subtract(_center);
//
//        double tm = alignZero(v.dotProduct(U));
//        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));
//
//        // no intersections : the ray direction is above the sphere
//        if (d >= radius) {
//            return null;
//        }
//
//        double th = alignZero(Math.sqrt(radius * radius - d * d));
//        double t1 = alignZero(tm - th);
//        double t2 = alignZero(tm + th);
//
//        if (t1 > 0 && t2 > 0) {
////            Point3D P1 = P0.add(v.scale(t1));
////            Point3D P2 = P0.add(v.scale(t2));
//            Point P1 = ray.getPoint(t1);
//            Point P2 = ray.getPoint(t2);
//            return List.of(new GeoPoint(this, P1), new GeoPoint(this, P2));
//        }
//        if (t1 > 0) {
////            Point3D P1 = P0.add(v.scale(t1));
//            Point P1 = ray.getPoint(t1);
//            return List.of(new GeoPoint(this, P1));
//        }
//        if (t2 > 0) {
////            Point3D P2 = P0.add(v.scale(t2));
//            Point P2 = ray.getPoint(t2);
//            return List.of(new GeoPoint(this, P2));
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @param ray
//     * @param maxDistance
//     * @return
//     */
//    @Override
//    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
//        return null;
//    }
}
