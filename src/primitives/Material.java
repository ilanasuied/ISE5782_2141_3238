package primitives;

public class Material {
    private double kd = 0d;
    private double ks = 0d;
    private int shininess = 0;

    /**
     * set diffuse factor/coefficient with double type parameter
     * @param kd
     * @return material kd
     */
    public Material setKd(double kd) {
        this.kd = kd;
        return this;
    }

    /**
     * set diffuse factor/coefficient with Double3 type parameter
     * @param kd
     * @return
     */
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


    /**
     * set specular factor/coefficient with double type parameter
     * @param ks
     * @return material ks
     */
    public Material setKs(double ks) {
        this.ks = ks;
        return this;
    }

    /**
     * set diffuse factor/coefficient with Double3 type parameter
     * @param ks
     * @return material ks
     */
    public Material setKs(Double3 ks) {
        ks.scale(this.ks);
        return this;
    }


    /**
     * getter for ks specular factor
     * @return
     */
    public double getKs() {
        return ks;
    }

    /**
     * setter for shininess value
     * @param shininess
     * @return material shininess
     */
    public Material setShininess(int shininess) {
        this.shininess = shininess;
        return  this;
    }

    /**
     * getter for shininess value
     * @return
     */
    public int getShininess() {
        return shininess;
    }
}
