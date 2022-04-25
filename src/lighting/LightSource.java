package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource {

    public Color getIntesity(Point p);

    public Vector getL(Point p);
}
