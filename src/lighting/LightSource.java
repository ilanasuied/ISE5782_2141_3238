package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public interface LightSource {

    public Color getIntensity(Point p);

    public Vector getL(Point p);

    /**
     * method to get the distance between a point and the light
     * @param point value for point
     * @return the distance
     */
    public double getDistance(Point point);
}
