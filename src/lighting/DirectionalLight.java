package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light {

    private Vector direction;


    @Override
    /**
     *
     */
    public Color getIntesity(Point p){
        return super.getIntensity();
    }

    public Vector getL(Point p)
    {
        return super.getL();
    }

}
