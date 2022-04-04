package renderer;

import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene.SceneBuilder("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), new Double3(1,1,1))) //
				.setBackground(new Color(75, 127, 90))
				.build();

		scene.getGeometries().add(new Sphere(new Point(0, 0, -100), 50),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
																													// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
																														// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
																													// right
		Camera camera = new Camera.BuilderCamera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setDistance(100) //
				.setViewPlaneHeight(500)
				.setViewPlaneWidth(500) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))				
				.setRayTracer(new RayTracerBasic(scene))
				.build();

		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}

	/**
	 * Test for XML based scene - for bonus
	 */
	@Test
	public void basicRenderXml() {
		Scene scene = new Scene.SceneBuilder("XML Test scene").build();
		// enter XML file name and parse from XML file into scene object
		// ...

		Camera camera = new Camera.BuilderCamera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setDistance(100) //
				.setViewPlaneWidth(500)
				.setViewPlaneHeight(500)
				.setImageWriter(new ImageWriter("xml render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene))
				.build();

		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}
}
