package primitives;

public class Point
{
      Double3 xyz;

    /**
     * point's constructor
     * @double x
     * @double y
     * @double z
     */
    public Point(double x,double y,double z)
    {
       xyz = new Double3(x,y,z);
    }


    /**
     * constructor
     * @Double3 xyz
     */
    public Point(Double3 xyz) { this.xyz = xyz; }

    /**
     * funcion for substraction
     * @Point other
     * @return
     */
    public Vector subtract(Point other)
    {  Double3 coordinates= new Double3(
                xyz.d1-other.xyz.d1,
                xyz.d2-other.xyz.d2,
                xyz.d3-other.xyz.d3);
    if(Double3.ZERO.equals(coordinates))
    {
        throw new IllegalArgumentException("ERROR");
    }
    return new Vector(coordinates.d1,coordinates.d2,coordinates.d3);
    }

    /**
     * function for adding
     * @param vector
     * @return
     */
    public Point add(Vector vector)
    {
        double x= xyz.d1 + vector.xyz.d1;
        double y= xyz.d2 + vector.xyz.d2;
        double z= xyz.d3 + vector.xyz.d3;

        return new Point(x,y,z);
    }


    /**
     * overrided function for equals, compares two object's values
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o)
    {
        if(this==o)
            return  true;
        if(o==null || getClass()!= o.getClass()) return false;
        Point point=(Point) o;
        return xyz.equals(point.xyz);

    }


    /**
     * function that calculate distance squared between two points
     * @param point
     * @return double
     */
   public double distanceSquared(Point point)
   {
       double distanceSquared = ((xyz.d1-point.xyz.d1)*(xyz.d1-point.xyz.d1)) +
               ((xyz.d2-point.xyz.d2)*(xyz.d2-point.xyz.d2)) +
               ((xyz.d3-point.xyz.d3)*(xyz.d3-point.xyz.d3));
       return distanceSquared;
   }

    /**
     * function that calculate distance between two points
     * @param point
     * @return
     */
   public double distance(Point point)
   {
       return Math.sqrt(distanceSquared(point));
   }



}
