package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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



        // =============== Boundary Values Tests ==================

        Tube tube2= new Tube(new Ray(new Point(0,0,0),new Vector(1,0,0)),2);

        //check if the function normalize return correctly the normal even when the point is on the head of the chepas quoi

        boolean firstnormal = new Vector(0, 0, 1).equals(normal);
        boolean secondtnormal = new Vector(0, 0, -1).equals(normal);

        assertTrue(firstnormal || secondtnormal, "Bad normal to tube");

        assertEquals(new Vector(0, 0, 1), normal, "Bad normal to tube");


    }

}