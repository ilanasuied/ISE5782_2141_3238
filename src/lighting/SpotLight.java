package lighting;

import primitives.*;


public class SpotLight extends PointLight{

    //the vector direction
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
        Color result= pointIntensity.scale(factor);
        return result;
    }



    /**
     * override function for getDistance,this function use his super's function
     *
     * @param point value for point
     * @return the distance
     */
    @Override
    public double getDistance(Point point) {
        return super.getDistance(point);
    }
}

