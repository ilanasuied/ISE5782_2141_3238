package primitives;

public class Point {
    Double3 _xyz;

    /**
     * primary point's constructor
     *
     * @double x value for X axis
     * @double y value for Y axis
     * @double z value for Z axis
     */
    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }


    /**
     * point constructor
     * @Double3 insert value for xyz
     */
    public Point(Double3 xyz) {
        this._xyz = xyz;
    }

    /**
     * function for subtraction
     *
     * @return new vector that represent the subtraction of two points
     * @Point other
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
     * @param vector
     * @return new point that represent the addition of a vector and a point
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }


    /**
     * overrided function for equals, compares two object's values
     *
     * @param o
     * @return boolean
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
     * @param point
     * @return double
     */
    public double distanceSquared(Point point) {
        Double3 tmp=_xyz.subtract(point._xyz);
        double xx=tmp._d1* tmp._d1;
        double yy=tmp._d2* tmp._d2;
        double zz=tmp._d3* tmp._d3;
      return (xx+yy+zz);
    }

    /**
     * function that calculate distance between two points
     * @param point
     * @return double
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }


    @Override
    /**
     * function that returns a string
     */
    public String toString() {
        return "Point " + _xyz;
    }
}
