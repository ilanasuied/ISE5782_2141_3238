
package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * common interface for all intersect-able geometric objects
 */
public abstract class Intersectable {

    public  static class  GeoPoint{

        public final Geometry geometry;
        public final Point point;

        public GeoPoint(Geometry geometry, Point point){
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
    /**
     * find intersection points from specific Ray
     * @param ray the ray crossing the geometric object
     * @return immutable List of intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                . toList();

    }
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     *
     * @param ray ray intersecting the geometry
     * @param maxDistance maxximum distance to loook for intersections geometries
     * @return list of intersection points
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);
}
//package geometries;
//
//import primitives.Point;
//import primitives.Ray;
//
//import java.awt.image.ImageProducer;
//import java.util.List;
//import java.util.Objects;
//
///**
// * common interface for all graphic 3D objects
// * that intersect with a specific Ray {@link primitives.Ray}
// */
//public abstract class Intersectable {
//
//
//    public  static  class GeoPoint{
//        public Geometry geometry;
//        public Point point;
//
//        public GeoPoint(Geometry geometry, Point point) {
//            this.geometry = geometry;
//            this.point = point;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GeoPoint geoPoint = (GeoPoint) o;
//            return Objects.equals(geometry, geoPoint.geometry) && point.equals(geoPoint.point);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(geometry, point);
//        }
//    }
//    /**
//     * find all intersection points {@link Point}
//     * that intersect the Shape from a specific Ray {@link Ray}
//     * @param ray Ray pointing towards the graphic object
//     * @return immutable list of intersection points
//     */
//    public List<Point> findIntersections(Ray ray){
//        var geoList = findGeoIntersections(ray);
//        return  geoList == null ? null
//                : geoList.stream().map(gp -> gp.point).toList();
//    }
//   public final List<GeoPoint> findGeoIntersections(Ray ray){
//        return  findGeoIntersectionsHelper(ray);
//    }
//
//   protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
//
//
//}
