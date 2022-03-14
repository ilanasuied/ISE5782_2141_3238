package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}