package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

    //initialized intersectable list to null
    private List<Intersectable> _intersectables = null;

    /**
     * the constructor
     * @param intersectables list of intersectable
     */
    public Geometries(Intersectable... intersectables) {
        this();
        add(intersectables);
    }

    /**
     * default constructor
     */
    public Geometries() {
        _intersectables = new LinkedList<>();
    }

    /**
     * this function add new link in the list
     * @param intersectables list of intersectable
     */
    public void add(Intersectable... intersectables) {
        Collections.addAll(_intersectables, intersectables);
    }

    /**
     * this function remove link of the list
     * @param intersectables list of intersectable
     */
    public void remove(Intersectable... intersectables) {
        _intersectables.removeAll(List.of(intersectables));
    }

    /**
     * override findGeoIntersectionsHelper function
     * @param ray ray intersecting the geometry
     * @param maxDistance maximum distance to look for intersections geometries
     * @return a list of all the geometries that comes in intersection
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        for (Intersectable item : _intersectables) {
            List<GeoPoint> itemList = item.findGeoIntersectionsHelper(ray, maxDistance);
            if (itemList != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemList);
            }
        }
        return result;

    }
}
