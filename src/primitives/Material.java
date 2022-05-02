package primitives;

public class Material {

    //attenuation's factors
    private Double3 kd = Double3.ZERO;
    private Double3 ks = Double3.ZERO;

    private int shininess = 0;

    /**
     * set diffuse factor/coefficient with double type parameter
     *
     * @param kd
     * @return material kd
     */
    public Material setKd(double kd) {
        this.kd = new Double3(kd);
        return this;
    }

    /**
     * set diffuse factor/coefficient with double type parameter
     *
     * @param kd
     * @return material kd
     */
    public Material setKd(Double3 kd) {
        this.kd = kd;
        return this;
    }

    /**
     * getter for kd diffuse factor
     *
     * @return kd value
     */
    public Double3 getKd() {
        return kd;
    }


    /**
     * set specular factor/coefficient with double type parameter
     *
     * @param ks
     * @return material ks
     */
    public Material setKs(double ks) {
        this.ks = new Double3(ks);
        return this;
    }

    /**
     * set diffuse factor/coefficient with Double3 type parameter
     *
     * @param ks
     * @return material ks
     */
    public Material setKs(Double3 ks) {
        this.ks = ks;
        return this;
    }


    /**
     * getter for ks specular factor
     *
     * @return
     */
    public Double3 getKs() {
        return ks;
    }

    /**
     * setter for shininess value
     *
     * @param shininess
     * @return material shininess
     */
    public Material setShininess(int shininess) {
        this.shininess = shininess;
        return this;
    }

    /**
     * getter for shininess value
     *
     * @return shininess coefficient
     */
    public int getShininess() {
        return shininess;
    }
}
