package primitives;

public class Material {
    private double kd = 0d;
    private double ks = 0d;
    private int shininess = 0;

    public Material setKd(double kd) {
        this.kd = kd;
        return this;
    }

    public Material setKd(Double3 kd) {
        kd.scale(this.kd);
        return this;
    }

    /**
     * getter for kd diffuse factor
     * @return kd value
     */
    public double getKd() {
        return kd;
    }


    public Material setKs(double ks) {
        this.ks = ks;
        return this;
    }
    public Material setKs(Double3 ks) {
        ks.scale(this.ks);
        return this;
    }

    public double getKs() {
        return ks;
    }

    public Material setShininess(int shininess) {
        this.shininess = shininess;
        return  this;
    }

    public int getShininess() {
        return shininess;
    }
}
