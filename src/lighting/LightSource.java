package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public interface LightSource {


    /**
     *
     * @param p value for Point
     * @return the color of the intensity in this point
     */
    public Color getIntensity(Point p);

    /**
     *
     * @param p value for Point
     * @return return the param L in this point
     */
    public Vector getL(Point p);

    /**
     *
     * @param point value for Point
     * @return the distance
     */
    public double getDistance(Point point);
}
