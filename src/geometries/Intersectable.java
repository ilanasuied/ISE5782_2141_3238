package geometries;

import primitives.Point;
import primitives.Ray;

import java.awt.image.ImageProducer;
import java.util.List;
import java.util.Objects;

/**
 * common interface for all graphic 3D objects
 * that intersect with a specific Ray {@link primitives.Ray}
 */
public abstract class Intersectable {


    public  static  class GeoPoint{
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }
    /**
     * find all intersection points {@link Point}
     * that intersect the Shape from a specific Ray {@link Ray}
     * @param ray Ray pointing towards the graphic object
     * @return immutable list of intersection points
     */
    public List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return  geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * function that find intersection between the ray and a point at infinity
     * @param ray value for the ray
     * @return a list of intersections's points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }


    /**
     * function that finds intersections and receive parametre of maximum distance
     * @param ray value for the ray
     * @param maxDistance max distance between a point and ray's head
     * @return a list of intersections's points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }



    /**
     * abstract function helper to find intersections
     * @param ray
     * @param maxDistance
     * @return
     */
    protected abstract List<GeoPoint>
    findGeoIntersectionsHelper(Ray ray, double maxDistance);


}
