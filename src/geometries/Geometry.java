package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * TODO  explain everything
 */
public interface Geometry extends Intersectable {
    /**
     * lo lo,
     * @param point
     * @return
     */
    Vector getNormal(Point point);

}
