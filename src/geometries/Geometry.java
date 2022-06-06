package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * abstract class wuth get Normal method
 * all the geometries use get normal method, to simplify and improve the design, we make an interface
 * that every geometry will extend.
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();

    public abstract Vector getNormal(Point point);

    /**
     * getter of emission
     *
     * @return the emission of the color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter of emission, this function return the object with the correct value for emission
     *
     * @param emission the value for the emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * setter for material
     *
     * @param material value for material
     * @return this object with the correct values
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * getter for material
     *
     * @return material
     */
    public Material getMaterial() {
        return material;
    }
}
