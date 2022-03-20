package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * testing {@link geometries.Triangle#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        //================================== Equivalence partition Tests =========================
        //TC01:
        Triangle triangle= new Triangle(new Point(0,0,1),new Point(1,0,0),new Point(0,1,0));
        double sqrt=Math.sqrt(1.0/3);
        assertEquals(new Vector(sqrt,sqrt,sqrt),triangle.getNormal(new Point(0,0,1)),"incorrect normal for triangle");
    }

    /**
     * testing {@link #testfindIntersectionsRay()}
     */
    @Test
    public void testfindIntersectionsRay() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Inside triangle
        // TC02: Against edge
        // TC03: Against vertex

        // =============== Boundary Values Tests ==================
        // TC11: In vertex
        // TC12: On edge
        // TC13: On edge continuation
    }


}