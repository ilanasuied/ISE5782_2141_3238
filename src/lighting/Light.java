package lighting;

import primitives.Color;

abstract class Light {
    private Color intensity;

    /**
     * constructor for intensity
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
