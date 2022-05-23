package primitives;

public class Material {

    //attenuation's factors
    private Double3 kD = Double3.ZERO;
    private Double3 kS = Double3.ZERO;
    public Double3 kT = Double3.ZERO; //transparency factor
    public Double3 kR = Double3.ZERO; //coefficient of reflection

    private int shininess = 0;

    //those two methods will help us for the stop conditions in the recursion
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    /**
     * set diffuse factor/coefficient with double type parameter
     *
     * @param kd
     * @return material kd
     */
    public Material setKd(double kd) {
        this.kD = new Double3(kd);
        return this;
    }

    /**
     * set diffuse factor/coefficient with double type parameter
     *
     * @param kd
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
     * @param ks
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
        return kD;
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


    public Material setKr(double kr) {
        this.kR = new Double3(kr);
        return this;
    }

    /**
     * getter for coefficient of reflection
     * @return coefficient of reflection
     */
    public Double3 getKr() {
        return kD;
    }
    /**
     * setter for shininess value
     *
     * @param shininess value for the level of shininess
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
