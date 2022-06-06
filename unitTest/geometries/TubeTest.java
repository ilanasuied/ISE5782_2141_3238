package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    /**
     * testing {@link geometries.Tube#constructorTest()}
     */
    void constructorTest() {
        try {
            new Tube(new Ray(new Point(1, 2, 3), new Vector(1, 1, 1)), 1);
        } catch (IllegalArgumentException e) {
            fail("failed constructing a correct tube");
        }
    }


    /**
     * test method for {@link geometries.Tube#getNormal(Point)}
     */

    @Test
    void testGetNormal() {

        //================================== Equivalence partition Tests =========================

        Tube tube= new Tube(new Ray(new Point(0,0,0),new Vector(1,0,0)),2);

        Vector normal =tube.getNormal(new Point(0,0,2)).normalize();
        double dotProduct=normal.dotProduct(tube.getAxisRay().getDir());

        assertEquals(0.0,dotProduct,"normal is not orthogonal to the tube");



        // ================================ Boundary Values Tests ===============================

        //check if the function normalize return correctly the normal even when the point is on the head of the chepas quoi

        boolean firstnormal = new Vector(0, 0, 1).equals(normal);
        boolean secondtnormal = new Vector(0, 0, -1).equals(normal);

        assertTrue(firstnormal || secondtnormal, "Bad normal to tube");

        assertEquals(new Vector(0, 0, 1), normal, "Bad normal to tube");


    }


    @Test
    void TestFindIntersection(){

        Tube tube1= new Tube(new Ray(new Point(0,0,0),new Vector(1,0,0)),2);

        //================================== Equivalence partition Tests =========================
        //TC01: the ray starts outside the tube
        assertNull(tube1.findIntersections(new Ray(new Point(0,5,0), new Vector(1,0,0)))
                ,"bad intersection tube, there should not be intersections");


        //TC02: the ray starts on the tube
        assertEquals(List.of(new Point(3,0,0)),tube1.findIntersections(new Ray(new Point(0,2,0), new Vector(1,0,0)))
                ,"bad intersection tube"); ////////////////////////


        //TC03: the ray stars inside the tube
        assertNull(tube1.findIntersections(new Ray(new Point(0,0,0),new Vector(1,0,0)))
                ,"bad intersection tube");



        // ================================ Boundary Values Tests ===============================

        //TC11: the ray goes in the opossite direction (0 point)
        assertNull(tube1.findIntersections(new Ray(new Point(0,4,0),new Vector(1,0,0)))
                ,"bad intersection tube");

        //TC12: the ray is outside and cross the tube
        //assertEquals(new Point(),tube1.findIntersections(new Ray(new Point(0,4,0),new Vector(5.25,-7.23,0)))
         //       ,"bad intersection tube");


        //TC31: the ray stars inside the tube and cross it (1 point)
        assertEquals(new Point(0,2,0),tube1.findIntersections(new Ray(new Point(0,1,0),new Vector(0,1,0)))
                ,"bad intersection tube");



    }
}