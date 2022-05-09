package lighting;

import primitives.*;


public class SpotLight extends PointLight{
    private Vector direction;

    /**
     * spotLight's constructor
     * @param intensity the value of the intensity
     * @param position the value of the position
     * @param direction the value of the direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    /**
     * getter for Intensity of spotLight
     */
    public Color getIntensity(Point p) {
        Color pointIntensity = super.getIntensity(p);
        Vector l = getL(p);
        double factor = Math.max(0,direction.dotProduct(l));
        return  pointIntensity.scale(factor);
    }

    @Override
    /**
     * implement method to get distance between the point and ray's head
     */
    public double getDistance(Double3 point) {
        return super.getDistance(point);
    }
}
