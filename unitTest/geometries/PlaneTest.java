package geometries;
import primitives.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testConstructor()
    {
        try{
            new Plane(new Point(1, 2, 3), new Point(4, 5, 6), new Point(3, 6, 9));
        }
        catch (IllegalArgumentException e)
        {
            fail("Failed constructing a correct plane");
        }

        //if two points are equal
        assertThrows(
                IllegalArgumentException.class,
                ()->{ Plane plane = new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(3, 6, 9)); },
        "ERROR- two points are equal");

        //if the 3 points are on the same line
        assertThrows(IllegalArgumentException.class,
                ()->{Plane plane = new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(3, 6, 9)); },
                "ERROR- the 3 points are aligned");


    }

    @Test
    /**
     * Test method for {@link geometries.Plane#Polygon(primitives.Point...)}.
     */
    void testGetNormal() {
        assertEquals(1,new Plane(new Point(1, 2, 3), new Point(5, 4, 6), new Point(3, 6, 9)).getNormal().length(),0.0001,"ERROR-the normal's length must be equal to one");

    }


}