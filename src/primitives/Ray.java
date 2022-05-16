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
     * function to find the closest point
     *
     * @param pointList list of geoPoint
     * @return the closest point
     */
    public GeoPoint findClosestPoint(List<Intersectable.GeoPoint> pointList) {
        GeoPoint result = null;

        double minDistance = Double.MAX_VALUE;
        double ptDistance;

        //check that pointList isn't null
        if(pointList!=null) {
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
