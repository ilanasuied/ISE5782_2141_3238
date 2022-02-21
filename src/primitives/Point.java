package primitives;

public class Point
{
    final Double3 xyz;

    public Point(double x,double y,double z)
    {
       xyz = new Double3(x,y,z);
    }
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

    public Point add(Vector vector)
    {
        double x= xyz.d1+vector.xyz.d1;
        double y= xyz.d2+vector.xyz.d2;
        double z= xyz.d3+vector.xyz.d3;

        return new Point(x,y,z);
    }


}
