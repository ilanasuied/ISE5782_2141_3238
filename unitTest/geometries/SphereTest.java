package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class SphereTest {

    /**
     * testing {@link Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        //================================== Equivalence partition Tests =========================
        //TC01: just one test
        Sphere sphere = new Sphere(new Point(0,0,1),1.0);
        assertEquals(new Vector(0,0,1),sphere.getNormal(new Point(0,0,2)),"incorrect normal for sphere");
    }




    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere( new Point (1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");


        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point (1.5,0,0),
                new Vector(3, 0, 0)));
        //check if the number of intersection's points is correct
        assertEquals(1, result.size(), "Wrong number of points");
        //check if the intersection with the sphere was with the correct point
        assertEquals(List.of(new Point(2,0,0)), result, "Ray crosses sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point (3,0,0),
                new Vector(1, 0, 0))),"Error- ray starts after the sphere");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point (1,1,0),
                new Vector(1, -1, 0)));
        //check if the number of intersection's points is correct
        assertEquals(1, result.size(), "Wrong number of points");
        //check if the intersection with the sphere was with the correct point
        assertEquals(List.of(new Point(2,0,0)), result, "Ray crosses sphere inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull( sphere.findIntersections(new Ray(new Point (1,1,0),
                new Vector(1, 1, 0))), "Error- Ray crosses sphere outside");


        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(-1,0,0),
                new Vector(3,0,0)));
        //check if the number of intersection's points is correct
        assertEquals(2,result.size(),"Wrong number of points");
        //put the intersections's points in the right order,(the closest point to the ray's head first ,then the second point
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        //the sphere is crossed on the X axis in 2 points
        assertEquals(List.of(new Point(0, 0, 0), new Point(2, 0, 0)),result,"Error- Line through the center,ray crosses the sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1,-1,0),
                new Vector(0,1,0)));
        //check if the number of intersection's points is correct
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1,1,0)),result,"Error- Line through the center, ray from and crosses the sphere");

        // TC15: Ray starts inside (1 points)
        assertEquals(List.of(new Point(2,0,0)),sphere.findIntersections(new Ray(new Point(0.5,0,0),
                new Vector(3,0,0))),"Error- Line through the center, ray from inside and cross the sphere");

        // TC16: Ray starts at the center (1 points)
        assertEquals(List.of(new Point(1,1,0)),sphere.findIntersections(new Ray(new Point(1,0,0),
                new Vector(0,2,0))),"Error- Line through the center, ray from the center");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2,0,0),new Vector(1,0,0))),
                "Error-Line through the center,from sphere to outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1,1,0),new Vector(-2,-1,0))),
                "Error-Line through the center,from outside the sphere");


        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0.5,1,0),new Vector(2,0,0))),"Error- ray starts before tangent");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,1,0),new Vector(2,0,0))),"Error- ray at tangent");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1.5,1,0),new Vector(3,0,0))),"Error- ray starts after tangent");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(0, 0, 1))),
                "Ray orthogonal to ray head - Center line");
    }

}