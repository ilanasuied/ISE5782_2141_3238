package geometries;

import primitives.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testConstructor() {
        testConstructor1();
        testConstructor2();
        testConstructor3();
    }

    @Test
    void testConstructor1() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }


    @Test
    void testConstructor2() {
        //if two points are equal
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Plane plane = new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(3, 6, 9));
                },
                "ERROR- two points are equal");
    }

    @Test
    void testConstructor3() {

        //if the 3 points are on the same line
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Plane plane = new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(3, 6, 9));
                },
                "ERROR- the 3 points are aligned");


    }

    @Test
    /**
     * Test method for {@link geometries.Plane#Polygon(primitives.Point...)}.
     */
    void testGetNormal() {
        assertEquals(1, new Plane(new Point(1, 2, 3), new Point(5, 4, 6), new Point(3, 6, 9)).getNormal().length(), 0.0001, "ERROR-the normal's length must be equal to one");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormalPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }
    @Test
    /**
     * testing {@link geometries.Plane#testFindIntersections()}
     */
    void testFindIntersections() {
        Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));
        // ================================================= Equivalence Partitions Tests ===================================================
        /////

        // TC01: Ray into plane
        assertEquals(List.of(new Point(1, 0, 0)),
                pl.findIntersections(new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0))),
                "Bad plane intersection");

        // TC02: Ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "Must not be plane intersection");





        // =============================================== Boundary Values Tests ===================================================


        // TC11: Ray parallel to plane
        assertNull(pl.findIntersections(new Ray(new Point(-1, -1, -1), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC13: Orthogonal ray into plane
        assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1))),
                "incorrect plane intersection");

        // TC14: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 2, 2), new Vector(1, 1, 1))),
                "Must not be plane intersection");


        // TC15: Orthogonal ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC16: Ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 0))),
                "Must not be plane intersection");

        // TC17: Ray from plane's Q point
        assertNull(pl.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 1))),
                "Must not be plane intersection");

    }
}