package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Ray {
    private static final double DELTA = 0.1;  //constant to move rays head towards he normal vector

    final public Point p0;
    final public Vector dir;

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
     * ray constructor
     *
     * @Point the point from where the ray start
     * @Vector the direction of the ray
     */
    public Ray(Point p0, Vector dir, Vector n) {
        this.dir = dir.normalize();

        double vn = alignZero(dir.dotProduct(n));

        Vector delta;
        if (vn < 0) {
            delta = n.scale(-DELTA);
        } else {
            delta = n.scale(DELTA);
        }

        this.p0 = p0.add(delta);
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
        Point result = p0;
        Vector target;

        if (!isZero(t)) {
             try {
                target = dir.scale(t);
                result = result.add(target);
             } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return getP0().equals(ray.getP0()) && getDir().equals(ray.getDir());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getP0(), getDir());
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
     * function to find the closest point
     *
     * @param pointList list of geoPoint
     * @return the closest point
     */
    public GeoPoint findClosestPoint(List<GeoPoint> pointList) {
        GeoPoint result = null;

        double minDistance = Double.MAX_VALUE;
        double ptDistance;

        //check that pointList isn't null
        if (pointList != null) {
            //for each item in the list
            for (GeoPoint geoPoint : pointList) {
                ptDistance = geoPoint.point.distanceSquared(p0);
                //check if this the smallest distance
                if (ptDistance < minDistance) {
                    minDistance = ptDistance;
                    //result become the geoPoint with the smallest distance
                    result = geoPoint;
                }
            }
        }
        //return result
        //if pintList is null -> return null
        return result;
    }
}
