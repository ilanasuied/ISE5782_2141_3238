package primitives;

public class Point
{
      Double3 _xyz;

    /**
     * primary point's constructor
     * @double x
     * @double y
     * @double z
     */
    public Point(double x,double y,double z)
    {
       _xyz = new Double3(x,y,z);
    }


    /**
     * constructor
     * @Double3 xyz
     */
    public Point(Double3 xyz) { this._xyz = xyz; }

    /**
     * function for substraction
     * @Point other
     * @return
     */
    public Vector subtract(Point other)
    {  Double3 coordinates= new Double3(
                _xyz._d1-other._xyz._d1,
                _xyz._d2-other._xyz._d2,
                _xyz._d3-other._xyz._d3);
    if(Double3.ZERO.equals(coordinates))
    {
        throw new IllegalArgumentException("ERROR");
    }
    return new Vector(coordinates._d1,coordinates._d2,coordinates._d3);
    }

    /**
     * function for adding
     * @param vector
     * @return
     */
    public Point add(Vector vector)
    {
        double x= _xyz._d1 + vector._xyz._d1;
        double y= _xyz._d2 + vector._xyz._d2;
        double z= _xyz._d3 + vector._xyz._d3;

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
        return _xyz.equals(point._xyz);

    }


    /**
     * function that calculate distance squared between two points
     * @param point
     * @return double
     */
   public double distanceSquared(Point point)
   {
       double distanceSquared = ((_xyz._d1-point._xyz._d1)*(_xyz._d1-point._xyz._d1)) +
               ((_xyz._d2-point._xyz._d2)*(_xyz._d2-point._xyz._d2)) +
               ((_xyz._d3-point._xyz._d3)*(_xyz._d3-point._xyz._d3));
       return distanceSquared;

      /** Double3 temp= _xyz.subtract(point._xyz);
       double xx=temp._d1 * temp._d1;
       double yy=temp._d2 * temp._d2;
       double zz=temp._d3 * temp._d3;
       return (xx + yy + zz);
       **/
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


    @Override
    public String toString() {
        return "Point " + _xyz;
    }
}
