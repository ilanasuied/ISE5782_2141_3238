package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * primary constructor for directional light
     * @param _intensity value for the intensity of the light
     * @param _direction value for the diection of the light
     */
    public DirectionalLight(Color _intensity, Vector _direction) {
        super(_intensity);
        this.direction = _direction.normalize();
    }

    @Override
    /**
     * getter function for intensity
     */
    public Color getIntensity(Point p){
        return super.getIntensity();
        //new Color(getIntensity);
    }

    @Override
    /**
     * getter function for getL
     */
    public Vector getL(Point p)
    {
        return direction;
    }


    @Override
    /**
     * implement method to get distance
     */
    public double getDistance(Double3 point) {
        ////
        return Double.POSITIVE_INFINITY;
    }
}
