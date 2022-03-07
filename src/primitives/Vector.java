package primitives;

import static primitives.Util.isZero;

public class Vector extends Point
{
    /**
     * constructor with 3 points
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if(isZero(x)&& isZero(y)&& isZero(z))
        {
            throw  new IllegalArgumentException(" ZERO vector not allowed");
        }
    }

    /**
     * constructor with 1 point with three coordinates
     * @param xyz
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
     * @return double
     */
    public double lengthSquared() {
        return _xyz._d1 * _xyz._d1 + _xyz._d2 * _xyz._d2 + _xyz._d3 * _xyz._d3;
    }

    /**
     * function that calculate vector's length
     * @return double
     */
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    /**
     *function for scalar's multiplication
     * @param vector
     * @return double
     */
   public double dotProduct(Vector vector)
   {
       double u1= _xyz._d1;
       double u2= _xyz._d2;
       double u3= _xyz._d3;

       double v1 = vector._xyz._d1;
       double v2 = vector._xyz._d2;
       double v3 = vector._xyz._d3;
       return u1*v1 + u2*v2 + u3*v3;
   }

    /**
     * function for vectorial multiplication
     * @param v
     * @return
     */
   public Vector crossProduct(Vector v)
   {
       double u1 = _xyz._d1;
       double u2 = _xyz._d2;
       double u3 = _xyz._d3;

       double v1 = v._xyz._d1;
       double v2 = v._xyz._d2;
       double v3 = v._xyz._d3;

       return new Vector(new Double3(
               u2*v3-u3*v2,
               u3*v1-u1*v3,
               u1*v2-u2*v1));

   }

    /**
     * function to multiply a vector by number
     * @param scaleFactor
     * @return
     */
   public Vector scale(double scaleFactor)
   {
       if (isZero(scaleFactor))
       {
           throw  new IllegalArgumentException("scale resulting by 0 not valid");
       }
       Double3 coordinates = new Double3(_xyz._d1 * scaleFactor, _xyz._d2 * scaleFactor, _xyz._d3* scaleFactor);
       return new Vector(coordinates._d1,coordinates._d2,coordinates._d3);
   }

    /**
     * function that performs vector adding
     * @param v
     * @return
     */
   public  Vector add (Vector v)
   {
       Double3 coordinates = new Double3(
               _xyz._d1+ v._xyz._d1,
               _xyz._d2+ v._xyz._d2,
               _xyz._d3+ v._xyz._d3
               );
       if(Double3.ZERO.equals(coordinates))
       {
           throw new IllegalArgumentException("add resulting ZERO vector not valid");
       }
       return new Vector(coordinates._d1,coordinates._d2,coordinates._d3);
   }

    /**
     * function that returns normalize vector in the same directory
     * @return
     */
    public Vector normalize()
    {
        double len = length();
        return this.scale (1d/len);
    }
}
