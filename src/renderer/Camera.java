package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    //revoir les valeurs

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

                                 //================================== constructors =========================

    public Camera(Point location,Vector Vup,Vector Vto) {

        if (Vup.dotProduct(Vto)!=0)
            throw new IllegalArgumentException("The direction vectors must be perpendicular to each other");
        this.location = location;
        this.Vto=Vto;
        this.Vup=Vup;
        this.Vright=
        this.Vto.normalize();
        this.Vup.normalize();
        this.Vright.normalize();
    }


    //================================== getters =========================

    /**
     * @return the location of the VP
     */
    public Point getLocation() {
        return location;
    }

    /**
     * return the vector that representing forward direction
     * @return vector Vto
     */
    public Vector getVto() {
        return Vto;
    }

    /**
     * return the vector that representing up direction
     * @return vector Vup
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     * this vector is vertical to the others 2 vectors (Vup and Vto)
     * @return vector Vright
     */
    public Vector getVright() {
        return Vright;
    }

    /**
     * return the value of the VP
     * @return camera's height
     */
    public double get_height() {
        return _height;
    }

    /**
     * return the width of the VP
     * @return camera's width
     */
    public double get_width() {
        return _width;
    }

    /**
     * return the distance of the VP
     * @return camera's distance
     */
    public double get_distance() {
        return _distance;
    }

                                  //================================== setters =========================


    public void set_height(double _height) {
        this._height = _height;
    }

    public void set_width(double _width) {
        this._width = _width;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }




    public Camera setVPSize(double width, double height){





        return this;
    }

    public Camera setVPDistance(double distance){




        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i){

        return null;
    }
}

