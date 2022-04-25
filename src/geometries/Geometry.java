package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * TODO  explain everything
 */
public abstract class Geometry extends Intersectable {

   protected Color emission= Color.BLACK;
    private Material material = new Material();

    public abstract Vector getNormal(Point point);

    /**
     * getter of emission
     * @return the emission of the color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter of emission, this function return the object with the correct value for emission
     * @param emission the value for the emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return  this;
    }

    public Material getMaterial() {
        return material;
    }
}
