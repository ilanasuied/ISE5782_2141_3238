package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class to gather other {@link Geometry} based objects
 */

public class Geometries implements Intersectable {
    private List <Intersectable> _intersectables = null;

    /**
     * constructor of Geometries
     * @param intersectables array of {@link Intersectable} objects
     */
    public Geometries(Intersectable... intersectables){
        _intersectables = new LinkedList<>();
        Collections.addAll(_intersectables, intersectables);
    }

    /**
     * default constructor
     */
    public Geometries(){
        _intersectables = new LinkedList<>();
    }

    public void  add(Intersectable... intersectables) {
        Collections.addAll(_intersectables, intersectables);
    }

    @Deprecated
    public void remove(Intersectable... intersectables) {
        _intersectables.removeAll(List.of(intersectables));
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (Intersectable item :_intersectables ) {
            List<Point> itemPointsList = item.findIntersections(ray);
            if( itemPointsList != null){
                if(result== null){
                    result = new LinkedList<>();
                }
                result.addAll(itemPointsList);
            }
        }
        return result;
    }
}
