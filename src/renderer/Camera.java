package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

import java.util.stream.*;

public class Camera {


    private static boolean SUPERSAMPLING = true;
    //point's location of the VP
    final private Point p0;

    //vectors for the VP's direction
    final private Vector Vto;
    final private Vector Vup;
    final private Vector Vright;
    private RayTracer rayTracer;
    private ImageWriter imageWriter;
    private int amountColumnPixels;
    private int amountRowPixels;

    //camera's height
    private double _height;

    //camera's width
    private double _width;

    //camera's distance
    private double _distance;

    /**
     * private default constructor for camera by the builder camera
     *
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
     * blabla
     * @param SUPERSAMPLING
     */
    public static void setSUPERSAMPLING(boolean SUPERSAMPLING) {
        Camera.SUPERSAMPLING = SUPERSAMPLING;
    }

    /**
     * apply a new value for the distance and return the object with the new values
     *
     * @param distance value for the distance
     * @return the object
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    /**
     * apply new values for the width and the height for the view plane size and return the object with the new values
     *
     * @param width  value for width
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
     *
     * @return the value of the view plane's width
     */
    public double getWidth() {
        return _width;
    }

    /**
     * height getter
     *
     * @return the value of the view plane's height
     */
    public double getHeight() {
        return _height;
    }

    /**
     * constructing a ray passing through pixel(i,j) of the view plane
     *
     * @param nX number of rows
     * @param nY number of columns
     * @param j  index for the rows of pixel
     * @param i  index for the columns of pixel
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


    /**
     * insert value in imageWriter
     *
     * @param interval value for interval of print grid
     * @param color    the color for print grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter != null) {
            imageWriter.printGrid(interval, color);
        }
    }

    /**
     * renderImage function
     *
     * @return this object with the correct values
     */
    public Camera renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", RayTracer.class.getName(), "");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            IntStream.range(0, nY).parallel().forEach(i -> {
                IntStream.range(0, nY).parallel().forEach(j -> {
                    if (SUPERSAMPLING == true)
                        castRayMultiple(nX, nY, j, i);
                    else
                        castRay(nX,nY,j,i);
                    Pixel.pixelDone();
                    Pixel.printPixel();
                });
            });
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
        return this;
    }

    /**
     * this function juste insert in imageWriter the correct value
     *
     * @param imageWriter the value of the image writer
     * @return this object with the correct values
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * setter for RayTracer
     *
     * @param rayTracer the correct value for the param RayTracer of this object
     * @return this object with the correct values
     */
    public Camera setRayTracer(RayTracer rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * insert in imageWriter the correct value
     *
     * @return this object with the correct values
     */
    public Camera writeToImage() {
        imageWriter.writeToImage();
        return this;
    }

    /**
     * @param amountRowPixels    the count of the pixel's row
     * @param amountColumnPixels the count of the pixel's columns
     * @return this object with the correct values
     */
    public Camera setPixels(int amountRowPixels, int amountColumnPixels) {
        this.amountRowPixels = amountRowPixels;
        this.amountColumnPixels = amountColumnPixels;
        return this;
    }

    /**
     * this function construct the rays
     *
     * @param nX x access
     * @param nY y access
     * @param j  to move up and down
     * @param i  to move right and left
     * @return the correct ray
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        if (amountColumnPixels <= 0 || amountRowPixels <= 0) {
            return List.of(constructRay(nX, nY, j, i));
        }
        Point Pc = p0.add(Vto.scale(_distance));
        List<Ray> rays = new LinkedList<>();
        //ratio
        double Ry = _height / nY;
        double Rx = _width / nX;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        //Pixel[i,j]center:
        Point Pij = Pc;
        //check that Yi isn't 0
        if (!isZero(Yi)) {
            Pij = Pij.add(Vup.scale(Yi));
        }
        //check if Xj isn't equal to 0
        if (!isZero(Xj)) {
            Pij = Pij.add(Vright.scale(Xj));
        }
        Ry = Ry / amountColumnPixels;
        Rx = Rx / amountRowPixels;
        for (int k = 0; k < amountRowPixels; k++) {
            for (int l = 0; l < amountColumnPixels; l++) {

                Point point = Pij;
                double Yii = -(k -
                        (amountColumnPixels - 1) / 2d) *
                        Ry;
                double Xjj = -(l -
                        (amountRowPixels - 1) / 2d) * Rx;
                if (!isZero(Yii)) {
                    point = point.add(Vup.scale(Yii
                    ));
                }
                if (!isZero(Xjj)) {
                    point = point.add(Vright.scale(
                            Xjj));
                }
                rays.add(new Ray(p0, p0.subtract(point)));
            }
        }
        return rays;
    }

    /**
     * this function construct the rays
     *
     * @param nX x access
     * @param nY y access
     * @param i  to move right and left
     * @param j  to move up and down
     */
    private void castRayMultiple(int nX, int nY, int i, int j) {
        List<Ray> rays = constructRays(nX, nY, i, j);
        Color pixelColor = rayTracer.traceRays(rays);
        imageWriter.writePixel(i, j, pixelColor);
    }

    /**
     * this function construct the rays
     *
     * @param nX x access
     * @param nY y access
     * @param i  to move right and left
     * @param j  to move up and down
     */
    private void castRay(int nX, int nY, int i, int j) {
        Ray ray = constructRay(nX, nY, i, j);
        Color pixelColor = rayTracer.traceRay(ray);
        imageWriter.writePixel(i, j, pixelColor);
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

        /**
         * setter for distance
         *
         * @param distance value for the distance of this object
         * @return this object with the correct value for his distance param
         */
        public BuilderCamera setVPDistance(double distance) {
            _distance = distance;
            return this;
        }

        /**
         * setter for viewPlanWidth
         *
         * @param width the correct value for the width
         * @return this object with the correct value for his width param
         */
        public BuilderCamera setViewPlaneWidth(double width) {
            _width = width;
            return this;
        }

        /**
         * setter for VP height
         *
         * @param height the correct value for the height
         * @return this object with the correct value for his height param
         */
        public BuilderCamera setViewPlaneHeight(double height) {
            _height = height;
            return this;
        }

        /**
         * constructor/ builder of this object
         *
         * @return the new object
         */
        public Camera build() {
            Camera camera = new Camera(this);
            return camera;
        }

        /**
         * buildCamera's constructor
         *
         * @param p0  value for point
         * @param vTo value for vector direction to
         * @param vUp value for vector direction up
         */
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

        /**
         * apply the correct value for the image writer to this object and return it
         *
         * @param imageWriter value for the image writer
         * @return this object with the correct value for the image writer
         */
        public BuilderCamera setImageWriter(ImageWriter imageWriter) {
            this.imageWriter = imageWriter;
            return this;
        }

        /**
         * apply the correct value for the ray tracer to this object and return it
         *
         * @param rayTracer value for the ray tracer
         * @return this object with the correct value for the ray tracer
         */
        public BuilderCamera setRayTracer(RayTracer rayTracer) {
            this.rayTracer = rayTracer;
            return this;
        }

        /**
         * setter for view plane width and hight
         *
         * @param width  value for the vp's width
         * @param height value for vp's hieght
         * @return this object
         */
        public BuilderCamera setVPSize(int width, int height) {
            this._width = width;
            this._height = height;
            return this;
        }
    }
}