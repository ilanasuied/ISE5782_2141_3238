package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {


    /**
     * test method for {@link geometries.Cylinder#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
//================================== Equivalence partition Tests =========================

        Cylinder cylinder= new Cylinder(new Ray(new Point(0,0,0),new Vector(1,0,0)),2,1.0);

        Vector normal =cylinder.getNormal(new Point(0,0,2)).normalize();
        double dotProduct=normal.dotProduct(cylinder.getAxisRay().getDir());

        assertEquals(0.0,dotProduct,"normal is not orthogonal to the tube");



        // =============== Boundary Values Tests ==================

        Cylinder cylinder2= new Cylinder(new Ray(new Point(0,0,0),new Vector(1,0,0)),2,1.0);

        //check if the function normalize return correctly the normal even when the point is on the head of the chepas quoi

        boolean firstnormal = new Vector(0, 0, 1).equals(normal);
        boolean secondtnormal = new Vector(0, 0, -1).equals(normal);

        assertTrue(firstnormal || secondtnormal, "Bad normal to tube");

        assertEquals(new Vector(0, 0, 1), normal, "Bad normal to tube");



    }
}