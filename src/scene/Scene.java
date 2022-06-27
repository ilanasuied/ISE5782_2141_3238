package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene or holding all the objects involved
 * using Builder Pattern
 */
public class Scene {

    private final String name;
    private final Color background;
    private AmbientLight ambientLight;
    public final Geometries geometries;
    public List<LightSource> lights;


    /**
     * private default constructor for scene by the builder scene
     *
     * @param builder scene
     */
    private Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
        lights = builder.lights;
    }

    /**
     * name's getter
     *
     * @return name of this object
     */
    public String getName() {
        return name;
    }

    /**
     * setter for ambientLight
     *
     * @param ambientLight value for AmbientLight
     * @return this object with the correct value for his ambientLight param
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * color's getter
     *
     * @return color of this object
     */
    public Color getBackground() {
        return background;
    }

    /**
     * AmbientLight's getter
     *
     * @return AmbientLight of this object
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * getter for light
     *
     * @return the value of the light
     */
    public List<LightSource> getLights() {
        return lights;
    }

    /**
     * getter for geometries
     *
     * @return geometries's value
     */
    public Geometries getGeometries() {
        return geometries;
    }

    /**
     * setter for light
     *
     * @param lights value for Light
     * @return this object with the correct value in his Light param
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }


// ================================= Builder Class for scene ===============================

    public static class SceneBuilder {

        private final String name;
        public List<LightSource> lights = new LinkedList<>();
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        /**
         * scene builder constructor
         *
         * @param name a string for the name of the scene
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        //chaining methods

        /**
         * steer for background
         * @param background color for the background
         * @return this object with the correct values
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        /**
         * setter for ambientLight
         *
         * @param ambientLight value for the ambientLight
         * @return this object with the correct value in his ambientLight param
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * setter fot geometries
         *
         * @param geometries value for Geometries
         * @return this object with the correct value in his geometries param
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * constructor/ builder scene builder
         *
         * @return the new scene
         */
        public Scene build() {
            Scene scene = new Scene(this);
            //....
            return scene;
        }
    }
}
