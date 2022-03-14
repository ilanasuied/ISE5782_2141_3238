package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Point}
 */
class PointTest {

    Point p1 = new Point(1, 2, 3);

    @Test
    /**
     * testing the {@link Point#subtract(Point)}
     */
    void testSubtract() 
    {
        assertEquals(new Vector(1,1,1), p1.subtract(new Point(2,3,4)),"ERROR: Point - Point does not work correctly");
    }

    @Test
    void testAdd() {
        fail("not yet implemented");
    }

    
    @Test
    /**
     * testing the {@link Point#distanceSquared(Point)
     */
    void testDistanceSquared() {
        Point point3 = new Point(0.5, 0, -100);
        assertEquals(0.25,  new Point(0, 0, -100).distanceSquared(point3),  0.0001, "distance squared method doesn't work correctly");
    }

    /**
     * testing the {@link Point#distance(Point)
     */
    @Test
    void testDistance() {
        Point point3 = new Point(0.5, 0, -100);
        assertEquals(0.5,  point3.distance(new Point(0, 0, -100)),  0.0001, "distance method doesn't work correctly");
    }
}