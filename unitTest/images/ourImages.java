package images;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class ourImages {

    static final Point ZERO_POINT = new Point(0, 0, 0);
    private Scene scene = new Scene.SceneBuilder("Test scene").build();

    @Test
    public void circle() {
        Scene scene = new Scene.SceneBuilder("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), //
                        new Double3(0.15))) //
                .setBackground(new Color(0, 0, 0))
                .build();

        scene.getGeometries().add(
                //le fond
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(90, -90, -150),
                        new Point(90, 90, -150)
                ),
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(-90, 90, -150),
                        new Point(90, 90, -150)
                ),

                //the side- right
                new Triangle(
                        new Point(90, -90, -150),
                        new Point(90, 90, -150),
                        new Point(100, 110, -120)
                ),
                new Triangle(
                        new Point(100, 110, -120),
                        new Point(100, -120, -120),
                        new Point(90, -90, -150)
                ),
                //the side- left
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(-90, 90, -150),
                        new Point(-100, 110, -120)
                ),
                new Triangle(
                        new Point(-100, 110, -120),
                        new Point(-100, -120, -120),
                        new Point(-90, -90, -150))


        );
//                //Sa tete
//                new Sphere(new Point(0, 0, -100), 50d).setEmission(new Color(150, 75, 0)),
//
//                //ses oreilles
//                new Sphere(new Point(40, 60, -100), 25d).setEmission(new Color(150, 75, 0)),
//                new Sphere(new Point(-40, 60, -100), 25d).setEmission(new Color(150, 75, 0)),
//
//                //son ventre
//                new Sphere(new Point(0, -110, -100), 60d).setEmission(new Color(150, 75, 0)),
//
//                //ses yeux
//                new Sphere(new Point(-5, 0, -19), 5d).setEmission(new Color(150, 75, 0)),
//                new Sphere(new Point(5, 0, -19), 5d).setEmission(new Color(150, 75, 0)),
//
//                //son nez
//                new Triangle(
//                        new Point(-3, -3, -19d),
//                        new Point(3, -3, -19d),
//                        new Point(0, -6, -19d)).
//                        setEmission(new Color(250, 75, 0)),
//                new Sphere(new Point(-4, 0, -15d), 2d).setEmission(new Color(0, 0, 255)),
//                new Sphere(new Point(4, 0, -15d), 2d).setEmission(new Color(0, 0, 255)));
//

        Camera camera = new Camera.BuilderCamera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setViewPlaneWidth(500)
                .setViewPlaneHeight(500) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .setImageWriter(new ImageWriter("ourNounours", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene))
                .build();

        camera.renderImage();
        camera.printGrid(100, new Color(BLACK));
        camera.writeToImage();


    }

    @Test
    public void images() {

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(.15)))
                .build();
        Camera camera = new Camera.BuilderCamera(new Point(20, -10, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene)).build();

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(.15)));

        scene.getGeometries().add( //
                new Plane(new Point(-150, -150, -150), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.9).setShininess(60)).setEmission(new Color(blue)), //
                new Sphere(new Point(-50, 80, 160), 25d) //
                        .setEmission(new Color(yellow))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                //first
                new Triangle(new Point(50, 50, 100), new Point(60, 48, 80), new Point(50, 45, 100))
                        .setEmission(new Color(black)),
                new Triangle(new Point(70, 50, 100), new Point(60, 48, 80), new Point(70, 45, 100))
                        .setEmission(new Color(black)),

                //second
                new Triangle(new Point(40, 40, 100), new Point(50, 38, 80), new Point(40, 35, 100))
                        .setEmission(new Color(black)),
                new Triangle(new Point(60, 40, 100), new Point(50, 38, 80), new Point(60, 35, 100))
                        .setEmission(new Color(black)),


                //third
                new Triangle(new Point(25, 52, 100), new Point(35, 50, 80), new Point(25, 47, 100))
                        .setEmission(new Color(black)),
                new Triangle(new Point(45, 52, 100), new Point(35, 50, 80), new Point(45, 47, 100))
                        .setEmission(new Color(black))


        );
        scene.lights.add( //
                new SpotLight(new Color(200, 700, 400), new Point(-50, 80, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        camera.setImageWriter(new ImageWriter("ILANA", 600, 600)) //
                .renderImage().writeToImage();
    }

    @Test
    public void project1() {

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(.15)));
        Camera camera = new Camera.BuilderCamera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(300, 300).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene)).build();
        Color tableColor = new Color(140, 60, 0);
        scene.getGeometries().add( //

                //le fond
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(90, -90, -150),
                        new Point(90, 90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(0, 204, 150)),
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(-90, 90, -150),
                        new Point(90, 90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(0, 204, 150)),

                // side- right
                new Triangle(
                        new Point(90, -90, -150),
                        new Point(90, 90, -150),
                        new Point(150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(0, 220, 160)),
                new Triangle(
                        new Point(150, 130, -140),
                        new Point(150, -150, -140),
                        new Point(90, -90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(0, 220, 160)),

                // side- left
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(-90, 90, -150),
                        new Point(-150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(0, 220, 160)),
                new Triangle(
                        new Point(-150, 130, -140),
                        new Point(-150, -150, -140),
                        new Point(-90, -90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(0, 220, 160)),

                // the floor
                new Triangle(
                        new Point(90, -90, -150),
                        new Point(150, -150, -140),
                        new Point(-90, -90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(gray)),
                new Triangle(
                        new Point(-150, -150, -140),
                        new Point(-90, -90, -150),
                        new Point(150, -150, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(gray)),


                // plafond
                new Triangle(
                        new Point(-90, 90, -150),
                        new Point(90, 90, -150),
                        new Point(150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80).setkT(0.8))
                        .setEmission(new Color(gray)),
                new Triangle(
                        new Point(-90, 90, -150),
                        new Point(150, 130, -140),
                        new Point(-150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80).setkT(0.8))
                        .setEmission(new Color(gray)),


                new Triangle(
                        new Point(-25, 120, -130),
                        new Point(25, 120, -130),
                        new Point(20, 100, -130)

                ).setEmission(new Color(white)),
                new Triangle(
                        new Point(-25, 120, -130),
                        new Point(-20, 100, -130),
                        new Point(20, 100, -130)

                ).setEmission(new Color(white)),

                //books on the shelf
                new Triangle(
                        new Point(-112, 60, 0),
                        new Point(-112, 26, 0),
                        new Point(-106, 28, 0)
                ).setEmission(new Color(red)),
                new Triangle(
                        new Point(-106, 28, 0),
                        new Point(-112, 60, 0),
                        new Point(-106, 62, 0)
                ).setEmission(new Color(red)),
                new Triangle(
                        new Point(-112, 60, 0),
                        new Point(-112, 26, 0),
                        new Point(-122, 30, 0)
                ).setEmission(new Color(255, 0, 50)),
                new Triangle(
                        new Point(-122, 30, 0),
                        new Point(-112, 60, 0),
                        new Point(-122, 62, 0)
                ).setEmission(new Color(222, 0, 50)),
//                new Triangle(
//                        new Point(-105, 55, 0),
//                        new Point(-105, 30.5, 0),
//                        new Point(-110, 30.5, 0)
//                ).setEmission(new Color(blue)),
//                new Triangle(
//                        new Point(-105, 55, 0),
//                        new Point(-110, 55, 0),
//                        new Point(-110, 30.5, 0)
//                ).setEmission(new Color(blue)),
//                new Triangle(
//                        new Point(-105, 49, 0),
//                        new Point(-100, 30.5, 0),
//                        new Point(-100, 50, 0)
//                ).setEmission(new Color(green)),
//                new Triangle(
//                        new Point(-100, 50, 0),
//                        new Point(-95, 30.5, 0),
//                        new Point(-100, 30.5, 0)
//                ).setEmission(new Color(green)),
//                new Triangle(
//                        new Point(-100, 50, 0),
//                        new Point(-95, 30.5, 0),
//                        new Point(-100, 30.5, 0)
//                ).setEmission(new Color(green)),


                //box on the floor
                new Triangle(
                        new Point(45, -90, 10),
                        new Point(70, -90, 10),
                        new Point(70, -65, 10)
                ).setEmission(new Color(pink))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(45, -90, 10),
                        new Point(45, -65, 10),
                        new Point(70, -65, 10)
                ).setEmission(new Color(pink))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(45, -90, 10),
                        new Point(45, -65, 10),
                        new Point(40, -60, 10)
                ).setEmission(new Color(green))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(45, -90, 10),
                        new Point(40, -85, 10),
                        new Point(40, -60, 10)
                ).setEmission(new Color(green))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(40, -60, 10),
                        new Point(45, -65, 10),
                        new Point(70, -65, 10)
                ).setEmission(new Color(magenta))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(70, -65, 10),
                        new Point(65, -60, 10),
                        new Point(40, -60, 10)
                ).setEmission(new Color(magenta))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3))
                ,


                // head
                new Sphere(new Point(-5, -16, -70), 25d).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //ears
                new Sphere(new Point(15, 8, -70), 12.5).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(-25, 8, -70), 12.5).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //belly
                new Sphere(new Point(-5, -70, -70), 30).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //hands + fingers
                //right hand
                new Sphere(new Point(28, -50, -70), 10).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                //left hand
                new Sphere(new Point(-38, -50, -65), 10).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                //right fingers
                new Sphere(new Point(39, -49, -70), 2).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(38, -45, -70), 2).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(38, -53, -72), 2).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                //left fingers
                new Sphere(new Point(-48, -49, -60), 2).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(-48, -45, -60), 2).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(-48, -53, -60), 2).setEmission(new Color(150, 75, 0))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),


                //eyes
                new Sphere(new Point(-10, -16, -40d), 3.5).setEmission(new Color(0, 0, 255))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),
                new Sphere(new Point(5, -16, -40d), 3.5).setEmission(new Color(0, 0, 255))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                //nose
                new Triangle(
                        new Point(-5, -22, -20),
                        new Point(1, -22, -20),
                        new Point(-2, -26, -20)).
                        setEmission(new Color(250, 75, 0)),


                //shelf -etagere ???
                new Triangle(
                        new Point(-100, 35, -50),
                        new Point(-100, 33, -50),
                        new Point(-135, 30, -50)).setEmission(new Color(yellow)),
                new Triangle(
                        new Point(-135, 30, -50),
                        new Point(-100, 35, -50),
                        new Point(-135, 32, -50)).setEmission(new Color(yellow)),
                new Triangle(
                        new Point(-100, 35, -50),
                        new Point(-90, 30, -45),
                        new Point(-135, 32, -50)).setEmission(new Color(yellow)),
                new Triangle(
                        new Point(-90, 30, -45),
                        new Point(-125, 25, -45),
                        new Point(-135, 32, -50)).setEmission(new Color(yellow)),

                new Triangle(
                        new Point(-100, 30, -45),
                        new Point(-100, 55, -45),
                        new Point(-92, 30, -50)).setEmission(new Color(yellow)),


                //the table
                //right foot front
                new Triangle(
                        new Point(60, -120, 20),
                        new Point(64, -120, 20),
                        new Point(64, -100, 20)
                ).setEmission(tableColor),
                new Triangle(
                        new Point(60, -120, 20),
                        new Point(60, -100, 20),
                        new Point(64, -100, 20)
                ).setEmission(tableColor),
                //left foot front
                new Triangle(
                        new Point(10, -120, 20),
                        new Point(14, -120, 20),
                        new Point(14, -100, 20)
                ).setEmission(tableColor),
                new Triangle(
                        new Point(10, -120, 20),
                        new Point(10, -100, 20),
                        new Point(14, -100, 20)
                ).setEmission(tableColor),
                //right foot in the back
                new Triangle(
                        new Point(65, -110, 20),
                        new Point(69, -110, 20),
                        new Point(69, -98, 20)
                ).setEmission(tableColor),
                new Triangle(
                        new Point(65, -110, 20),
                        new Point(65, -98, 20),
                        new Point(69, -98, 20)
                ).setEmission(tableColor),
                //left foot in the back
                new Triangle(
                        new Point(15, -110, 20),
                        new Point(19, -110, 20),
                        new Point(19, -98, 20)
                ).setEmission(tableColor),
                new Triangle(
                        new Point(15, -110, 20),
                        new Point(15, -98, 20),
                        new Point(19, -98, 20)
                ).setEmission(tableColor),
                //the plate
                new Triangle(
                        new Point(-10, -108, 20),
                        new Point(75, -85, 20),
                        new Point(0, -85, 20)
                ).setEmission(tableColor)
                        .setMaterial(new Material().setKd(0.01).setKs(0.5).setKr(1).setShininess(30)),
                new Triangle(
                        new Point(-10, -108, 20),
                        new Point(75, -85, 20),
                        new Point(85, -108, 20)
                ).setEmission(tableColor)
                        .setMaterial(new Material().setKd(0.01).setKs(0.5).setKr(1).setShininess(30)),

                //balls
                new Sphere(new Point(-60, -90, 17), 17d) //
                        .setEmission(new Color(red)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKr(1).setShininess(30)),
                new Sphere(new Point(-70, -100, 50), 10d) //
                        .setEmission(new Color(blue)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKr(1).setShininess(30))


        );
        scene.lights.add( //
                new SpotLight(new Color(white), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        scene.lights.add(new DirectionalLight(new Color(CYAN), new Vector(-0.0003, -1.0003, 0)));

        camera.setImageWriter(new ImageWriter("project1", 600, 600)) //
                .renderImage().writeToImage();
    }


    @Test
    public void targil8() {

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(.1)));
        Camera camera = new Camera.BuilderCamera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000) //
                .setRayTracer(new RayTracerBasic(scene)).build();
        scene.getGeometries().add(//

                //the front wall
                new Polygon(
                        new Point(370, -900, -100),
                        new Point(400, -900, -100),
                        new Point(400, 900, -100),
                        new Point(370, 900, -100))
                        .setEmission(new Color(gray))

//                //left side
//                new Polygon(
//                        new Point(-100,100,-500),
//                        new Point(-242, 160, 0),
//                        new Point(-242, -160, 0),
//                        new Point(-100, -100, -500)).setEmission(new Color(red))

        );


        camera.setImageWriter(new ImageWriter("targil8", 500, 500)) //
                .renderImage().writeToImage();


    }
}