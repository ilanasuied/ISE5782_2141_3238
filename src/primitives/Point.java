package primitives;

import renderer.Camera;

public class Point {

    public static final Point ZERO = new Point(0d, 0d, 0d);       // origin of axis
    Double3 _xyz;

    /**
     * primary point's constructor
     *
     * @param x value for X axis
     * @param y value for Y axis
     * @param z value for Z axis
     */
    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }


    /**
     * point constructor
     *
     * @param xyz value for coordinates
     */
    public Point(Double3 xyz) {
        this._xyz = xyz;
    }

    /**
     * function for subtraction
     *
     * @return new vector that represent the subtraction of two points
     * @param other value for the second Point
     */
    public Vector subtract(Point other) {
        Double3 coordinates = other._xyz.subtract(_xyz);
        if (Double3.ZERO.equals(coordinates)) {
            throw new IllegalArgumentException("ERROR");
        }
        return new Vector(coordinates);
    }

    /**
     * function for adding
     *
     * @param vector value for vector
     * @return new point that represent the addition of a vector and a point
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }


    /**
     * override function for equals, compares two object's values
     *
     * @param o another object, compare if this object is equal to o
     * @return boolean response if they are equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return _xyz.equals(point._xyz);

    }


    /**
     * function that calculate distance squared between two points
     *
     * @param point calculate the distance between this object and 'point'
     * @return double, the distance between those 2 points
     */
    public double distanceSquared(Point point) {
        Double3 tmp = _xyz.subtract(point._xyz);
        double xx = tmp.d1 * tmp.d1;
        double yy = tmp.d2 * tmp.d2;
        double zz = tmp.d3 * tmp.d3;
        double result = (xx + yy + zz);
        return result;
    }

    /**
     * function that calculate distance between two points
     *
     * @param point value of a point
     * @return double, the distance between those 2 points
     */
    public double distance(Point point) {
        double result = Math.sqrt(distanceSquared(point));
        return result;
    }


    @Override
    /**
     * function that returns a string
     */
    public String toString() {
        return "Point " + _xyz;
    }

    /**
     * get the value of x
     *
     * @return the value of the coordinate x
     */
    public double getX() {
        return _xyz.d1;
    }

    /**
     * get the value of y
     *
     * @return the value of the coordinate y
     */
    public double getY() {
        return _xyz.d2;
    }

    /**
     * get the value of z
     *
     * @return the value of the coordinate z
     */
    public double getZ() {
        return _xyz.d3;
    }
}
