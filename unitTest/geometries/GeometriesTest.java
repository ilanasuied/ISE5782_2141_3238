package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing collection of geometries
 */
class GeometriesTest {
    Sphere sph = new Sphere(new Point(0,0,1),1);
    Plane pl = new Plane(new Point(0,0,1),new Point(0,1,0),new Point(1,0,0));
    Triangle tr = new Triangle(new Point(0,0,1),new Point(0,1,0),new Point(2,0,0));
    Geometries collection = new Geometries(sph,pl,tr);

    /**
     * testing {@link geometries.Geometries#add(Intersectable...)}
     */
    @Test
    void testAdd() {
        
    }

    /**
     * testing {@link geometries.Geometries#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        // ================================================= Equivalence Partitions Tests ===================================================
        //TC01:some Geometries are intersected (not all of them)
        Ray ray= new Ray(new Point(0,0,0.5),new Vector(3,0,0));
        assertEquals(3,collection.findIntersections(ray).size(),"Wrong number of intersections");


        // ================================================= Boundary Values Tests ==========================================================
        //TC11: All the Geometries are intersected

        //TC12: No Geometries are intersected

        //TC13: Only one Geometrie is intersected

        //TC14: Empty Geometries collection
    }
    
}