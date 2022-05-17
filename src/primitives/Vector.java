package primitives;

import static primitives.Util.isZero;

public class Vector extends Point
{
    /**
     * constructor with 3 coordinates
     * @param x value for x axis
     * @param y value for y axis
     * @param z value for z axis
     */
    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if(_xyz.equals(Point.ZERO))  {
            throw  new IllegalArgumentException(" ZERO vector not allowed");
        }
    }

    /**
     * constructor with 1 point with three coordinates
     * @param xyz a 3d point
     */
    public Vector(Double3 xyz)
    {
        super(xyz);
        if(xyz.equals(Double3.ZERO))
        {
            throw  new IllegalArgumentException(" ZERO vector not allowed");
        }
    }



    /**
     * function that calculate vector's length squared
     * @return double, value of vector's length squared
     */
    public double lengthSquared() {
        return _xyz.d1 * _xyz.d1 + _xyz.d2 * _xyz.d2 + _xyz.d3 * _xyz.d3;
    }

    /**
     * function that calculate vector's length
     * @return double, value of vector's length
     */
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    /**
     *function for scalar's multiplication
     * @param vector, value of another vector
     * @return double, the value of the dot product between those 2 vectors
     */
   public double dotProduct(Vector vector)
   {
       double u1= _xyz.d1;
       double u2= _xyz.d2;
       double u3= _xyz.d3;

       double v1 = vector._xyz.d1;
       double v2 = vector._xyz.d2;
       double v3 = vector._xyz.d3;
       return u1*v1 + u2*v2 + u3*v3;
   }

    /**
     * function for vector multiplication
     * @param v value of a vector
     * @return a vector, result of cross product
     */
   public Vector crossProduct(Vector v)
   {
       double u1 = _xyz.d1;
       double u2 = _xyz.d2;
       double u3 = _xyz.d3;

       double v1 = v._xyz.d1;
       double v2 = v._xyz.d2;
       double v3 = v._xyz.d3;

       return new Vector(new Double3(
               u2*v3-u3*v2,
               u3*v1-u1*v3,
               u1*v2-u2*v1));

   }

    /**
     * function to multiply a vector by a number
     * @param scaleFactor a scale value
     * @return vector with the correct value after the scale factor
     */
   public Vector scale(double scaleFactor)
   {
       return new Vector(this._xyz.scale(scaleFactor));
   }

    /**
     * function that performs vector adding
     * @param v vector to add
     * @return vector result of adding 2 vectors
     */
   public  Vector add (Vector v)
   {
       return new Vector(_xyz.add(v._xyz));
   }

    /**
     * function that returns normalize vector in the same directory
     * @return normalized vector
     */
    public Vector normalize()
    {
        double len = length();
        return this.scale (1d/len);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _xyz.equals(vector._xyz);
    }

}
