package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void constructorTest() {
        try {
            new Tube(new Ray(new Point(1, 2, 3), new Vector(1, 1, 1)), 1);
        } catch (IllegalArgumentException e) {
            fail("failed constructing a correct tube");
        }
    }


    @Test
    void testGetNormal() {
    }
}