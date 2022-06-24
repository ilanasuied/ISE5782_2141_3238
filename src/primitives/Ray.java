package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {

    final public Point p0;
    final public Vector dir;

    /**
     * DELTA value to move the point away from original point
     */
    private static final double DELTA = 0.1;

    /**
     * ray constructor
     *
     * @Point the point from where the ray start
     * @Vector the direction of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructor for ray deflected by DELTA
     *
     * @param p origin
     * @param n   normal vector
     * @param dir direction
     */
    public Ray(Point p, Vector n, Vector dir) {
        this.dir = dir.normalize();
        double nv = n.dotProduct(this.dir);
        Vector delta  =n.scale(DELTA);
        if (nv < 0)
            delta = delta.scale(-1);
        this.p0 = p.add(delta);
    }

    /**
     * getter for point p0
     *
     * @return the point where the ray start
     */
    public Point getP0() {
        return p0;
    }


    /**
     * get the vector of the ray's direction
     *
     * @return the vector direction
     */
    public Vector getDir() {
        return dir;
    }


    /**
     * calculate the point where the ray arrived
     *
     * @param t value to add to p0
     * @return p0 + the parameter t
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            return p0;
        }
        return p0.add(dir.scale(t));
    }

    /**
     * override function for equal
     *
     * @param o value for another object
     * @return if the object is equal to this object or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    /**
     * override function for toString
     *
     * @return toString for Ray
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * this function find the closest point in intersection
     *
     * @param intersections list of goePoint
     * @return the closest point that is in intersection
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        GeoPoint closestpoint = null;
        double minDistance = Double.MAX_VALUE;
        double ptDistance;

        for (GeoPoint geoPoint : intersections) {
            ptDistance = geoPoint.point.distanceSquared(p0);
            if (ptDistance < minDistance) {
                minDistance = ptDistance;
                closestpoint = geoPoint;
            }
        }
        return closestpoint;
    }

}
