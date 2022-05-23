package primitives.lighting;

import primitives.*;

public class PointLight extends Light implements LightSource {

    private Point position;

    //attenuation's factors
    private Double3 kC = Double3.ONE;
    private Double3 kL = Double3.ZERO;
    private Double3 kQ = Double3.ZERO;

    /**
     * PointLight's constructor
     *
     * @param intensity value for the intensity color
     * @param position  value for the point
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }


    /**
     * kc's setter
     *
     * @param kC value for attenuation
     * @return the object with the correct values
     */
    public PointLight setKc(Double3 kC) {
        this.kC = kC;
        return this;
    }

    /**
     * kc's setter
     *
     * @param kC value for attenuation
     * @return the object with the correct values
     */
    public PointLight setKc(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    /**
     * kL's setter
     *
     * @param kL value for the attenuation
     * @return this object with the correct values
     */
    public PointLight setKl(Double3 kL) {
        this.kL = kL;
        return this;
    }

    /**
     * kL's setter
     *
     * @param kL value for the attenuation
     * @return this object with the correct values
     */
    public PointLight setKl(double kL) {
        this.kL = new Double3(kL);
        return this;
    }

    /**
     * kQ's setter
     *
     * @param kQ value for the attenuation
     * @return this object with the correct values;
     */
    public PointLight setKq(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * kQ's setter
     *
     * @param kQ value for the attenuation
     * @return this object with the correct values;
     */
    public PointLight setKq(double kQ) {
        this.kQ = new Double3(kQ);
        return this;
    }

    /**
     * getter for the intensity
     * this override function calls his super
     *
     * @param p value for the point
     * @return what returned from his super
     */
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        double dquadric = position.distanceSquared(p);
        Color intensity = getIntensity();
        return intensity.reduce(kC.add(kL.scale(d).add(kQ.scale(dquadric))));
    }

    /**
     * getL function
     *
     * @param p value for the point
     * @return a new vector that represent the vector between Point p and position normalized
     */
    @Override
    public Vector getL(Point p) {
        return position.subtract(p).normalize();
    }


    /**
     * override function for getDistance
     *
     * @param point value for point
     * @return the distance
     */
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }
}
