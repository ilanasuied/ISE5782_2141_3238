package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point position;
    private  double kC=1, kL=0, kQ=0;

    /**
     * PointLight's constructor
     * @param intensity value for the intensity color
     * @param position value for the point
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }


    /**
     * kc's setter
     * @param kC value for attenuation
     * @return the object with the correct values
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * kL's setter
     * @param kL value for the attenuation
     * @return this object with the correct values
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * kQ's setter
     * @param kQ value for the attenuation
     * @return this object with the correct values;
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * getter for the intensity
     * this override function calls his super
     * @param p value for the point
     * @return what returned from his super
     */
    @Override
    public Color getIntesity(Point p) {
        return super.getIntensity();
    }

    /**
     * getL function
     * @param p value for the point
     * @return a new vector that represent something..........................
     */
    @Override
    public Vector getL(Point p) {
        return new Vector(this.kC,this.kL,this.kQ);
    }


}
