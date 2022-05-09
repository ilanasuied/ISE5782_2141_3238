package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public interface LightSource {

    public Color getIntensity(Point p);

    public Vector getL(Point p);

    /**
     * function to get distance between 2 points
     * @param point value for point to calculate distance from a specific point to it
     * @return the distance
     */
    public double getDistance(Double3 point);
}
