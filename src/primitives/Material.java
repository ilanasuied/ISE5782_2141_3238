package primitives;

public class Material {

    //attenuation's factors
    private Double3 kd = Double3.ZERO;  //diffuse factor
    private Double3 ks = Double3.ZERO;  //specular factor
    public Double3 Kt = Double3.ZERO;  //refraction factor
    public Double3 Kr = Double3.ZERO;  //reflection factor

    private int shininess = 0;


    /**
     * set diffuse factor/coefficient with double type parameter
     * @param kd
     * @return material kd
     */
    public Material setKd(double kd) {
        this.kd = new Double3(kd);
        return this;
    }

    /**
     * set diffuse factor/coefficient with double type parameter
     * @param kd
     * @return material kd
     */
    public Material setKd(Double3 kd) {
        this.kd = kd;
        return this;
    }


    /**
     * getter for kd diffuse factor
     * @return kd value
     */
    public Double3 getKd() {
        return kd;
    }


    /**
     * set specular factor/coefficient with double type parameter
     * @param ks value for specular factor
     * @return material ks
     */
    public Material setKs(double ks) {
        this.ks = new Double3(ks);
        return this;
    }


    /**
     * set diffuse factor/coefficient with Double3 type parameter
     *
     * @param ks value for specular factor
     * @return material ks
     */
    public Material setKs(Double3 ks) {
        this.ks = ks;
        return this;
    }


    /**
     * set transparency factor with Double3 type parameter
     * @param kT value for transparency factor / refraction
     * @return Material kt
     */
    public Material setKt(Double3 kT) {
        this.Kt = kT;
        return this;
    }


    /**
     * getter for kt - transparency factor
     * @return transparency factor
     */
    public Double3 getKt() {
        return Kt;
    }


    /**
     * set reflection factor with Double3 type parameter
     * @param kr value for reflection factor
     * @return Material kr
     */
    public Material setKr(Double3 kr) {
        this.Kr = kr;
        return this;
    }


    /**
     * getter for kr - refraction factor
     * @return refracted factor
     */
    public Double3 getKr() {
        return Kr;
    }



    /**
     * getter for ks - specular factor
     * @return Double3 ks
     */
    public Double3 getKs() {
        return ks;
    }


    /**
     * setter for shininess value
     * @param shininess
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
