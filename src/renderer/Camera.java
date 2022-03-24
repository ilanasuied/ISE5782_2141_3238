package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {


    //point's location of the VP
    private Point location;

    //vectors for the VP's direction
    private Vector Vto;
    private Vector Vup;
    private Vector Vright;



    //camera's height
    private double _height=3;

    //camera's width
    private double _width=3;

    //camera's distance
    private double _distance=10;



    /**
     * Builder Class for Camera
     */
    public static class BuilderCamera {
        final private Point _p0;
        final private Vector _vTo;
        final private Vector _vUp;
        final private Vector _vRight;

        private double _distance = 10;
        private double _width = 1;
        private double _height = 1;

        public BuilderCamera setDistance(double distance) {
            _distance = distance;
            return this;
        }


        public BuilderCamera setViewPlaneWidth(double width) {
            _width = width;
            return this;
        }

        public BuilderCamera setViewPlaneHeight(double height) {
            _height = height;
            return this;
        }

        public Camera build() {
            Camera camera = new Camera(this);
            return camera;
        }

        public BuilderCamera(Point p0, Vector vTo, Vector vUp) {
            _p0 = p0;

            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("vto and vup are not orthogonal");
            }

            _vTo = vTo.normalize();
            _vUp = vUp.normalize();

            _vRight = _vTo.crossProduct(_vUp);

        }
    }
}