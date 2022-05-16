package lighting;

import primitives.*;

abstract class Light {
    private Color intensity;

    /**
     * constructor for intensity
     * @param intensity value for intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity
     * @return the intensity value
     */
    public Color getIntensity() {
        return intensity;
    }
}
