package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point position;
    private  double kC=1, kL=0, kQ=0;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }



    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntesity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return new Vector(this.kC,this.kL,this.kQ);
    }


}
