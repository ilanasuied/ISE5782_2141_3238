package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * Testing Camera Class
 *
 * @author Dan
 *
 */
class CameraTest {
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * Test method for
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
    @Test
    public void testConstructRay() {
        Camera camera = new Camera.BuilderCamera(ZERO_POINT, new Vector(0, 0, 1), new Vector(0, -1, 0))
                .setDistance(10)
                .setViewPlaneHeight(6)
                .setViewPlaneWidth(6)
                .build();

        // ============ Equivalence Partitions Tests ==============
        // TC01: 3X3 Corner (0,0)
        camera.setVPSize(6,6);
        assertEquals(new Ray(ZERO_POINT, new Vector(-2, -2, 10)),
                camera.constructRay(3, 3, 0, 0),
                "Bad ray");

        // TC02: 4X4 Corner (0,0)
        camera.setVPSize(8,8);
        assertEquals(new Ray(ZERO_POINT, new Vector(-3, -3, 10)),
                camera.constructRay(4, 4, 0, 0),
                "Bad ray");

        // TC03: 4X4 Side (0,1)
        camera.setVPSize(8,8);
        assertEquals(new Ray(ZERO_POINT, new Vector(-1, -3, 10)),
                camera.constructRay(4, 4, 1, 0),
                "Bad ray");

        // TC04: 4X4 Inside (1,1)
        camera.setVPSize(8,8);
        assertEquals(new Ray(ZERO_POINT, new Vector(-1, -1, 10)),
                camera.constructRay(4, 4, 1, 1),
                "Bad ray");

        // =============== Boundary Values Tests ==================
        // TC11: 3X3 Center (1,1)
        camera.setVPSize(6,6);
        assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, 10)),
                camera.constructRay(3, 3, 1, 1),
                "Bad ray");

        // TC12: 3X3 Center of Upper Side (0,1)
        camera.setVPSize(6,6);
        assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, 10)),
                camera.constructRay(3, 3, 1, 0),
                "Bad ray");

        // TC13: 3X3 Center of Left Side (1,0)
        camera.setVPSize(6,6);
        assertEquals(new Ray(ZERO_POINT, new Vector(-2, 0, 10)),
                camera.constructRay(3, 3, 0, 1),
                "Bad ray");

    }

}
