package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public interface LightSource {


    /**
     * method to get the intensity of the light
     * @param p value for Point
     * @return the color of the intensity in this point
     */
    public Color getIntensity(Point p);

    /**
     * method to get the vector direction
     * @param p value for Point
     * @return return the param L in this point
     */
    public Vector getL(Point p);

    /**
     * method to get the distance of a point from the light source
     * @param point value for Point
     * @return the distance
     */
    public double getDistance(Point point);
}
