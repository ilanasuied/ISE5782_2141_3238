package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon
{
    /**
     * constructor with 3 points for triangle
     * @param p1 value for first point
     * @param p2 value for second point
     * @param p3 value for third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }


    /**
     * function that returns a string for triangle
     * @return string
     */
    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }

    /**
     * find all the intersections between the ray with the objects and saves them in a list
     * @param ray Ray pointing towards the graphic object
     * @return the list of the intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        return null;
    }

}
