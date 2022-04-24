package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;


public abstract class RayTracer {

    protected final Scene scene;

    //scene's constructor
    public RayTracer(Scene scene){
        this.scene = scene;
    }

    public abstract Color traceRay(Ray ray);
}
