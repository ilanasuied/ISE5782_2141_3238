package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 *  Scene or holding all the objects involved
 *  using Builder Pattern
 */
public class Scene {

    private final String name;
    private final Color background;
    private final AmbientLight ambientLight;
    private final Geometries geometries;



    /**
     * private default constructor for scene by the builder scene
     * @param builder scene
     */
    private Scene(SceneBuilder builder){
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
    }

    /**
     * name's getter
     * @return name of this object
     */
    public String getName() {
        return name;
    }

    /**
     * color's getter
     * @return color of this object
     */
    public Color getBackground() {
        return background;
    }

    /**
     * AmbientLight's getter
     * @return AmbientLight of this object
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }


    public Geometries getGeometries() {
        return geometries;
    }





    // ================================= Builder Class for scene ===============================

    public static class SceneBuilder {

        private final String name;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        public SceneBuilder(String name){
            this.name = name;
        }

        //chaining methods

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        // build
        public Scene build(){
            Scene scene = new Scene(this);
            //....
            return scene;
        }
    }
}
