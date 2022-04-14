package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {



    //point's location of the VP
    final private Point p0;

    //vectors for the VP's direction
    final private Vector Vto;
    final private Vector Vup;
    final private Vector Vright;
    private final RayTracer rayTracer;
    private ImageWriter imageWriter;


    //camera's height
    private double _height;

    //camera's width
    private double _width;

    //camera's distance
    private double _distance;

    /**
     * private default constructor for camera by the builder camera
     * @param builder camera
     */
    private Camera(BuilderCamera builder) {
        p0 = builder._p0;
        Vto = builder._vTo;
        Vup = builder._vUp;
        Vright = builder._vRight;
        _height = builder._height;
        _width = builder._width;
        _distance = builder._distance;
        imageWriter = builder.imageWriter;
        rayTracer = builder.rayTracer;
    }

    /**
     * apply a new value for the distance and return the object with the new values
     * @param distance value for the distance
     * @return the object
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * apply new values for the width and the height for the view plane size and return the object with the new values
     * @param width value for width
     * @param height value for height
     * @return the new object
     */
    public Camera setVPSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * width getter
     * @return the value of the view plane's width
     */
    public double getWidth() {
        return _width;
    }

    /**
     * height getter
     * @return the value of the view plane's height
     */
    public double getHeight() {
        return _height;
    }

    /**
     * constructing a ray passing through pixel(i,j) of the view plane
     * @param nX number of rows
     * @param nY number of columns
     * @param j index for the rows of pixel
     * @param i index for the columns of pixel
     * @return a ray that pass through pixels
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = p0.add(Vto.scale(_distance));

        double Rx = _width / nX;
        double Ry = _height / nY;

        Point Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(p0, p0.subtract(Pij));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(Vup.scale(Yi));
            return new Ray(p0, p0.subtract(Pij));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(Vright.scale(Xj));
            return new Ray(p0, p0.subtract(Pij));
        }

        Pij = Pij.add(Vright.scale(Xj).add(Vup.scale(Yi)));
        return new Ray(p0, p0.subtract(Pij));

    }

    public void writeToImage() {
        imageWriter.writeToImage();
    }

    public void printGrid(int interval, Color color) {
        if(imageWriter!= null){
            imageWriter.printGrid(interval, color);
        }
    }

    public void renderImage() {
        int nx = imageWriter.getNx();;
        int ny = imageWriter.getNy();;

        for (int i = 0; i < nx; i++) {
            for (int j = 0; j < ny  ; j++) {
                Ray ray = constructRay(nx,ny,i,j);
                Color pixelcolor = rayTracer.traceRay(ray);
                imageWriter.writePixel(i,j,pixelcolor);
            }
        }

    }




    // ================================= Builder Class for Camera ===============================


    public static class BuilderCamera {
        //point's location of the VP
        final private Point _p0;

        //vectors for the VP's direction
        final private Vector _vTo;
        final private Vector _vUp;
        final private Vector _vRight;

        //camera's distance
        private double _distance = 10;

        //camera's width
        private double _width = 1;

        //camera's height
        private double _height = 1;

        private ImageWriter imageWriter = null;
        private RayTracer rayTracer = null;

        //setter distance
        public BuilderCamera setDistance(double distance) {
            _distance = distance;
            return this;
        }


        //setter view plane's width
        public BuilderCamera setViewPlaneWidth(double width) {
            _width = width;
            return this;
        }

        //setter view plane's height
        public BuilderCamera setViewPlaneHeight(double height) {
            _height = height;
            return this;
        }

        //camera build default constructor
        public Camera build() {
            Camera camera = new Camera(this);
            return camera;
        }

        //camera build constructor
        public BuilderCamera(Point p0, Vector vTo, Vector vUp) {
            _p0 = p0;

            //check if the 2 vectors are orthogonal
            if (!isZero(vTo.dotProduct(vUp))) {
                throw new IllegalArgumentException("vto and vup are not orthogonal");
            }

            _vTo = vTo.normalize();
            _vUp = vUp.normalize();

            _vRight = _vTo.crossProduct(_vUp);

        }

        public BuilderCamera setImageWriter(ImageWriter imageWriter) {
            this.imageWriter = imageWriter;
            return  this;
        }

        public BuilderCamera setRayTracer(RayTracer rayTracer) {
            this.rayTracer = rayTracer;
            return this;
        }
    }
}