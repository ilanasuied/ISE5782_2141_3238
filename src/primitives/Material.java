package primitives;

public class Material {

    //attenuation's factors

    /*Kd is the objects diffuse coefficient*/
    private Double3 kD = Double3.ZERO;

    /* Ks is the objects specular coefficient*/
    private Double3 kS = Double3.ZERO;

    /*transparency factor*/
    public Double3 kT = Double3.ZERO;

    /*Kr represents the object's reflectivity coefficient*/
    public Double3 kR = Double3.ZERO;

    private int shininess = 0;


    /**
     * set diffuse factor/coefficient with double type parameter
     *
     * @param kd value of the objects diffuse
     * @return material kd
     */
    public Material setKd(double kd) {
        this.kD = new Double3(kd);
        return this;
    }

    /**
     * set diffuse factor/coefficient with double type parameter
     *
     * @param kd value of objects diffuse
     * @return material kd
     */
    public Material setKd(Double3 kd) {
        this.kD = kd;
        return this;
    }

    /**
     * getter for kd diffuse factor
     *
     * @return kd value
     */
    public Double3 getKd() {
        return kD;
    }


    /**
     * set specular factor/coefficient with double type parameter
     *
     * @param ks level of the specular coefficient
     * @return material ks
     */
    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }


    /**
     * set diffuse factor/coefficient with Double3 type parameter
     *
     * @param ks value of the diffuse factor
     * @return material ks
     */
    public Material setKs(Double3 ks) {
        this.kS = ks;
        return this;
    }


    /**
     * getter for ks specular factor
     *
     * @return the value of specular factor
     */
    public Double3 getKs() {
        return kS;
    }


    public Material setkT(double kt) {
        this.kT = new Double3(kt);
        return this;
    }


    /**
     * setter for the transparency factor
     * @param kt value of the transparency factor
     * @return this object with correct values
     */
    public Material setkT(Double3 kt) {
        this.kT = kt;
        return this;
    }


    /**
     * getter for the transparency factor
     * @return transparency factor
     */
    public Double3 getKt() {
        return kT;
    }


    /**
     * setter for the coefficient of reflection
     * @param kr value of coefficient of reflection
     * @return this object with correct values
     */
    public Material setKr(Double3 kr) {
        this.kR = kr;
        return this;
    }


    /**
     * setter for the coefficient of reflection with double parameter
     * @param kr value of coefficient of reflection
     * @return this object with correct values
     */
    public Material setKr(double kr) {
        this.kR = new Double3(kr);
        return this;
    }


    /**
     * getter for coefficient of reflection
     * @return coefficient of reflection
     */
    public Double3 getKr() {
        return kR;
    }



    /**
     * setter for shininess value
     * @param shininess value for the level of shininess
     * @return material shininess
     */
    public Material setShininess(int shininess) {
        this.shininess = shininess;
        return this;
    }


    /**
     * getter for shininess value
     * @return shininess coefficient
     */
    public int getShininess() {
        return shininess;
    }
}
