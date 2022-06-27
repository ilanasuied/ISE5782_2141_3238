package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;


public abstract class RayTracer {

    protected final Scene scene;

    /**
     * scene constructor
     *
     * @param scene tue correct value for this scene
     */
    public RayTracer(Scene scene) {
        this.scene = scene;
    }

    public abstract Color traceRays(List<Ray> rays);

    public abstract Color traceRay(Ray ray);
}
