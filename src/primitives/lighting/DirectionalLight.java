package primitives.lighting;

import primitives.*;

public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * primary constructor for directional light
     *
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
    public Color getIntensity(Point p) {
        return super.getIntensity();
        //new Color(getIntensity);
    }

    @Override
    /**
     * getter function for getL
     */
    public Vector getL(Point p) {
        return direction;
    }


    /**
     * this method return the positive infinity as distance
     *
     * @param point value for point
     * @return the positive infinity
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
