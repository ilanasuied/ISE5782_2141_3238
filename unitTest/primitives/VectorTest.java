package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    @Test
    /**
     * testing the {@link Vector#ConstructorNotZero}
     * throws an exception if the vector is zero
     */
    void testConstructorZero()
    {
        assertThrows(
                IllegalArgumentException.class,
                ()->{new Vector(0,0,0);},
                "ERROR: zero vector does not throw an exception");

    }


    @Test
    /**
     * testing the {@link Vector#lengthSquared()}
     */
    void testLengthSquared() {
        assertEquals(14,v1.lengthSquared(),0.0001,"ERROR: lengthSquared() wrong value");
    }



    @Test
    /***
     * testing the {@link Vector#length()}
     */
    void testLength() {
        assertEquals(5,v3.length(),0.0001,"ERROR: length() wrong value");
    }


    @Test
    /**
     * testing the {@link Vector#dotProduct(Vector)}
     */
    void testDotProduct() {
        assertEquals(0,v1.dotProduct(v3),0.0001,"ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(-28,v1.dotProduct(v2),0.0001,"ERROR: dotProduct() wrong value");
    }


    @Test
    /**
     * testing the {@link Vector#crossProduct(Vector)}
     */
    void testCrossProduct() {
        Vector vr=v1.crossProduct(v3);
        assertEquals(0,v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");
        assertEquals(v1.length()*v3.length(),vr.length(),0.0001,"ERROR: crossProduct() wrong result length");
        assertEquals(0,vr.dotProduct(v1),0.0001,"ERROR: crossProduct() result is not orthogonal to its operands");//???
        assertEquals(0,vr.dotProduct(v2),0.0001,"ERROR: crossProduct() result is not orthogonal to its operands");
    }


    @Test
    /**
     * testing the {@link Vector#scale(double)}
     */
    void testScale() {
        assertEquals(new Vector(0,6,-4),v3.scale(2),"Error-scale function doesn't work correctly");
    }

    @Test
    /**
     * testing method for {@link Vector#add(Vector)}.
     */
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(new Vector(0,0,0),p1.add(new Vector(-1,-2,-3)),"ERROR: Point + Vector does not work correctly");
    }

    @Test
    /**
     * testing the {@link Vector#normalize()}
     */
    void testNormalize() {
        Vector u=v1.normalize();
        assertEquals(1,u.length(),0.0001,"ERROR: the normalized vector is not a unit vector");
        assertEquals(0,v1.crossProduct(u),"ERROR: the normalized vector is not parallel to the original one");
    }
}