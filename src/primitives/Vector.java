package primitives;

import static primitives.Util.isZero;

public class Vector extends Point
{
    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if(isZero(x)&& isZero(y)&& isZero(z))
        {
            throw  new IllegalArgumentException(" ZERO vector not allowed");
        }
    }

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
     * @return
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 +xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * function that calculate vector's length
     * @return
     */
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    /**
     *function for scalar's multiplication
     * @param vector
     * @return
     */
   public  double dotProduct(Vector vector)
   {
       double u1= xyz.d1;
       double u2= xyz.d2;
       double u3= xyz.d3;

       double v1 = vector.xyz.d1;
       double v2 = vector.xyz.d2;
       double v3 = vector.xyz.d3;
       return u1*v1 + u2*v2 + u3*v3;
   }

    /**
     * function for vectorial multiplication
     * @param v
     * @return
     */
   public Vector crossProduct(Vector v)
   {
       double u1 = xyz.d1;
       double u2 = xyz.d2;
       double u3 = xyz.d3;

       double v1 = v.xyz.d1;
       double v2 = v.xyz.d2;
       double v3 = v.xyz.d3;

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
       Double3 coordinates = new Double3(xyz.d1 * scaleFactor, xyz.d2 * scaleFactor, xyz.d3* scaleFactor);
       return new Vector(coordinates.d1,coordinates.d2,coordinates.d3);
   }

    /**
     * function that performs vector adding
     * @param v
     * @return
     */
   public  Vector add (Vector v)
   {
       Double3 coordinates = new Double3(
               xyz.d1+ v.xyz.d1,
               xyz.d2+ v.xyz.d2,
               xyz.d3+ v.xyz.d3
               );
       if(Double3.ZERO.equals(coordinates))
       {
           throw new IllegalArgumentException("add resulting ZERO vector not valid");
       }
       return new Vector(coordinates.d1,coordinates.d2,coordinates.d3);
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
