package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public interface LightSource {

    public Color getIntensity(Point p);

    public Vector getL(Point p);

    public double getDistance(Point point);
}
