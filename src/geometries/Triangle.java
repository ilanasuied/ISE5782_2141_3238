package geometries;

import primitives.Point;
import primitives.Vector;

public class Triangle extends Polygon
{
    /**
     * constructor with 3 points for triangle
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);

    }

    /**
     * overrided function of to string for triangle
     * @return
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * overrided function of get normal for triangle
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
