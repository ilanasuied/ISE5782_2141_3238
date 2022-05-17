package primitives;

/**
 * this class represents the different materials of the surfaces
 * and the reflection of a light component on it,
 * in three known values: diffusion, specular, and shininess.
 */
public class Material {

    /**
     *  Kd - diffuse component, represents the scattering of light rays to all directions from the surface
     */
    private Double3 Kd = Double3.ZERO;

    /**
     *  Ks - specular component, represents the reflectance of the light source over the surface
     */
    private Double3 Ks = Double3.ZERO;

    /**
     *  Shininess - how shiny the material is
     */
    private int nShininess = 0;

    /**
     *  Kt - transparency component
     * 0.0 is opaque
     * 1.0 is clear
     */
    private Double3 Kt = Double3.ZERO;

    /**
     *  Kr - reflection component
     * 0.0 is matte
     * 1.0 is very reflexive
     */
    private Double3 Kr = Double3.ZERO;

    //*********Setters*********

    public Material setKs(double ks) {
        Ks = new Double3(ks);
        return this;
    }

    public Material setKd(double kd) {
        this.Kd = new Double3(kd);
        return this;
    }

    public Material setKs(Double3 ks) {
        Ks = ks;
        return this;
    }

    public Material setKd(Double3 kd) {
        this.Kd = kd;
        return this;
    }


    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Material setKt(double kt) {
        this.Kt = new Double3(kt);
        return this;
    }

    public Material setKr(double kr) {
        this.Kr = new Double3(kr);
        return this;
    }

    public Material setKt(Double3 kt) {
        this.Kt = kt;
        return this;
    }

    public Material setKr(Double3 kr) {
        this.Kr = kr;
        return this;
    }

    public Double3 getKs() {
        return Ks;
    }

    public Double3 getKd() {
        return Kd;
    }

    //*********Getters*********

    public int getShininess() {
        return nShininess;
    }

    public Double3 getKt() {
        return Kt;
    }

    public Double3 getKr() {
        return Kr;
    }


}



//package primitives;
//
//public class Material {
//
//    //attenuation's factors
//    private Double3 kd = Double3.ZERO;  //diffuse factor
//    private Double3 ks = Double3.ZERO;  //specular factor
//    public Double3 Kt = Double3.ZERO;  //refraction factor
//    public Double3 Kr = Double3.ZERO;  //reflection factor
//
//    private int shininess = 0;
//
//
//    /**
//     * set diffuse factor/coefficient with double type parameter
//     * @param kd
//     * @return material kd
//     */
//    public Material setKd(double kd) {
//        this.kd = new Double3(kd);
//        return this;
//    }
//
//    /**
//     * set diffuse factor/coefficient with double type parameter
//     * @param kd
//     * @return material kd
//     */
//    public Material setKd(Double3 kd) {
//        this.kd = kd;
//        return this;
//    }
//
//
//    /**
//     * getter for kd diffuse factor
//     * @return kd value
//     */
//    public Double3 getKd() {
//        return kd;
//    }
//
//
//    /**
//     * set specular factor/coefficient with double type parameter
//     * @param ks value for specular factor
//     * @return material ks
//     */
//    public Material setKs(double ks) {
//        this.ks = new Double3(ks);
//        return this;
//    }
//
//
//    /**
//     * set diffuse factor/coefficient with Double3 type parameter
//     *
//     * @param ks value for specular factor
//     * @return material ks
//     */
//    public Material setKs(Double3 ks) {
//        this.ks = ks;
//        return this;
//    }
//
//
//    /**
//     * set transparency factor with Double3 type parameter
//     * @param kT value for transparency factor / refraction
//     * @return this object with the correct values
//     */
//    public Material setKt(Double3 kT) {
//        this.Kt = kT;
//        return this;
//    }
//
//
//    /**
//     * getter for kt - transparency factor
//     * @return transparency factor
//     */
//    public Double3 getKt() {
//        return Kt;
//    }
//
//
//    /**
//     * set reflection factor with Double3 type parameter
//     * @param kr value for reflection factor
//     * @return this object with the correct values
//     */
//    public Material setKr(Double3 kr) {
//        this.Kr = kr;
//        return this;
//    }
//
//
//    /**
//     * getter for kr - refraction factor
//     * @return refracted factor
//     */
//    public Double3 getKr() {
//        return Kr;
//    }
//
//
//
//    /**
//     * getter for ks - specular factor
//     * @return Double3 ks
//     */
//    public Double3 getKs() {
//        return ks;
//    }
//
//
//    /**
//     * setter for shininess value
//     * @param shininess
//     * @return material shininess
//     */
//    public Material setShininess(int shininess) {
//        this.shininess = shininess;
//        return this;
//    }
//
//
//    /**
//     * getter for shininess value
//     * @return shininess coefficient
//     */
//    public int getShininess() {
//        return shininess;
//    }
//}
