package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
     * overrided get the normal of the triangle
     * @param point on the triangle
     * @return the normal og the triangle
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
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
