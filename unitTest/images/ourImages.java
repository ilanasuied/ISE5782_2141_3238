package images;

import geometries.*;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import lighting.AmbientLight;
import lighting.SpotLight;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class ourImages {

    private Scene scene = new Scene.SceneBuilder("Test scene").build().setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(.15)));
    private Scene scene2 = new Scene.SceneBuilder("Test scene").build().setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(.15)));
    Camera camera = new Camera.BuilderCamera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(300, 300).setVPDistance(1000) //
            .setRayTracer(new RayTracerBasic(scene)).build();

    Camera camera2 = new Camera.BuilderCamera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(300, 300).setVPDistance(1000) //
            .setRayTracer(new RayTracerBasic(scene)).build();



    public Color blackColor = new Color(black);
    public Color pinkColor = new Color(pink);
    public Color whiteColor = new Color(white);
    public  Color blueColor = new Color(blue);
    public Color redColor = new Color(red);
    public Color grayColor = new Color(gray);
    public Color sideColor = new Color(178, 178, 178);
    public Color poohColor = new Color(150, 75, 0);
    public Color reliefsColor = new Color(40, 40, 40);




    /**
     * exercise 7
     */
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
    /**
     * this is our final project1
     */
    public void AllTheImage1(){
        RoomWalls();
        Books();
        BoxOnTheFloor();
        Balls();
        Pooh();
        Shelf();
        Table();
        // Chair();
        scene2.lights.add( //
                new SpotLight(pinkColor, new Point(50, -100, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        scene2.lights.add(new PointLight(new Color(yellow), new Point(0, 110, -130)));

        camera2.setImageWriter(new ImageWriter("project1", 600, 600)) //
                .setPixels(5, 5).renderImage().writeToImage().setSUPERSAMPLING(false);
    }


    @Test
    /**
     * this is our final project2
     */
    public void AllTheImage2(){
        RoomWalls();
        Books();
        BoxOnTheFloor();
        Balls();
        Pooh();
        Shelf();
        Table();
       // Chair();
        scene.lights.add( //
                new SpotLight(pinkColor, new Point(50, -100, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        scene.lights.add(new PointLight(new Color(yellow), new Point(0, 110, -130)));

        camera.setImageWriter(new ImageWriter("project2", 600, 600)) //
                .setPixels(5, 5).renderImage().writeToImage();
    }


    @Test
    /**
     * build walls
     */
    public void RoomWalls() {
        scene.getGeometries().add( //

                //le fond - the background
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(90, -90, -150),
                        new Point(90, 90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(169, 169, 169)),
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(-90, 90, -150),
                        new Point(90, 90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(new Color(169, 169, 169)),

                // side- right
                new Triangle(
                        new Point(90, -90, -150),
                        new Point(90, 90, -150),
                        new Point(150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(sideColor),
                new Triangle(
                        new Point(150, 130, -140),
                        new Point(150, -150, -140),
                        new Point(90, -90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(sideColor),

                // side- left
                new Triangle(
                        new Point(-90, -90, -150),
                        new Point(-90, 90, -150),
                        new Point(-150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(sideColor),
                new Triangle(
                        new Point(-150, 130, -140),
                        new Point(-150, -150, -140),
                        new Point(-90, -90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(sideColor),

                // the floor
                new Triangle(
                        new Point(90, -90, -150),
                        new Point(150, -150, -140),
                        new Point(-90, -90, -150)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(grayColor),
                new Triangle(
                        new Point(-150, -150, -140),
                        new Point(-90, -90, -150),
                        new Point(150, -150, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80))
                        .setEmission(grayColor),


                // plafond - ceiling
                new Triangle(
                        new Point(-90, 90, -150),
                        new Point(90, 90, -150),
                        new Point(150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80).setkT(0.8))
                        .setEmission(grayColor),
                new Triangle(
                        new Point(-90, 90, -150),
                        new Point(150, 130, -140),
                        new Point(-150, 130, -140)
                ).setMaterial(new Material().setKd(0.5).setKs(0.8).setShininess(80).setkT(0.8))
                        .setEmission(grayColor),


                //light
                new Triangle(
                        new Point(-25, 120, -130),
                        new Point(25, 120, -130),
                        new Point(20, 100, -130)

                ).setEmission(whiteColor),
                new Triangle(
                        new Point(-25, 120, -130),
                        new Point(-20, 100, -130),
                        new Point(20, 100, -130)

                ).setEmission(whiteColor));
    }

    @Test
    /**
     * build books
     */
    public void Books(){
        //books on the shelf
        scene.getGeometries().add(
        new Triangle(
                new Point(-112, 44, 0),
                new Point(-112, 17, 0),
                new Point(-107, 17, 0)
        ).setEmission(redColor),
                new Triangle(
                        new Point(-107, 44, 0),
                        new Point(-112, 44, 0),
                        new Point(-107, 17, 0)
                ).setEmission(redColor),
                new Triangle(
                        new Point(-112, 44, 0),
                        new Point(-112, 17, 0),
                        new Point(-122, 24, 0)
                ).setEmission(new Color(255, 0, 50)),
                new Triangle(
                        new Point(-122, 24, 0),
                        new Point(-112, 44, 0),
                        new Point(-122, 48, 0)
                ).setEmission(new Color(222, 0, 50)),
                new Triangle(
                        new Point(-107, 44, 0),
                        new Point(-112, 44, 0),
                        new Point(-122, 48, 0)
                ).setEmission(whiteColor),
                new Triangle(
                        new Point(-107, 44, 0),
                        new Point(-122, 48, 0),
                        new Point(-118, 48, 0)
                ).setEmission(whiteColor),
                new Triangle(
                        new Point(-107, 17, 0),
                        new Point(-102, 17, 0),
                        new Point(-102, 44, 0)
                ).setEmission(blueColor),
                new Triangle(
                        new Point(-107, 17, 0),
                        new Point(-107, 44, 0),
                        new Point(-102, 44, 0)
                ).setEmission(blueColor),
                new Triangle(
                        new Point(-107, 44, 0),
                        new Point(-102, 44, 0),
                        new Point(-118, 48, 0)
                ).setEmission(whiteColor),
                new Triangle(
                        new Point(-102, 44, 0),
                        new Point(-118, 48, 0),
                        new Point(-112, 48, 0)
                ).setEmission(whiteColor),
                new Triangle(
                        new Point(-107, 44, 10),
                        new Point(-108, 44, 10),
                        new Point(-118, 48, 10)
                ).setEmission(new Color(222, 0, 50)),
                new Triangle(
                        new Point(-107, 44, 10),
                        new Point(-117, 48, 10),
                        new Point(-118, 48, 10)
                ).setEmission(new Color(222, 0, 50)),
                new Triangle(
                        new Point(-97, 17, 10),
                        new Point(-94, 20, 10),
                        new Point(-98, 40, 10)
                ).setEmission(new Color(0, 255, 50)),
                new Triangle(
                        new Point(-97, 17, 10),
                        new Point(-101, 38, 10),
                        new Point(-98, 40, 10)
                ).setEmission(new Color(0, 255, 50)),
                new Triangle(
                        new Point(-101, 40, 10),
                        new Point(-101, 19, 10),
                        new Point(-97, 17, 10)
                ).setEmission(new Color(0, 150, 50)),
                new Triangle(
                        new Point(-101, 38, 10),
                        new Point(-101, 40, 10),
                        new Point(-98, 40, 10)
                ).setEmission(whiteColor));

    }

    @Test
    /**
     * build box on the floor
     */
    public void BoxOnTheFloor(){
        //box on the floor
        scene.getGeometries().add(
        new Triangle(
                new Point(-45, -90, 10),
                new Point(-70, -90, 10),
                new Point(-70, -65, 10)
        ).setEmission(pinkColor)
                .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(-45, -90, 10),
                        new Point(-45, -65, 10),
                        new Point(-70, -65, 10)
                ).setEmission(pinkColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(-45, -90, 10),
                        new Point(-45, -65, 10),
                        new Point(-40, -60, 10)
                ).setEmission(new Color(255, 105, 180))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),


                new Triangle(
                        new Point(-45, -90, 10),
                        new Point(-40, -85, 10),
                        new Point(-40, -60, 10)
                ).setEmission(new Color(255, 105, 180))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(-40, -60, 10),
                        new Point(-45, -65, 10),
                        new Point(-70, -65, 10)
                ).setEmission(new Color(magenta))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                new Triangle(
                        new Point(-70, -65, 10),
                        new Point(-65, -60, 10),
                        new Point(-40, -60, 10)
                ).setEmission(new Color(magenta))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)));
    }

    @Test
    /**
     * build pooh
     */
    public void Pooh(){
        scene.getGeometries().add(
                // head
                new Sphere(new Point(-5, -20, -70), 25d).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //ears
                new Sphere(new Point(15, 4, -70), 12.5).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(-25, 4, -70), 12.5).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //belly
                new Sphere(new Point(-5, -74, -70), 30).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //hands + fingers
                //right hand
                new Sphere(new Point(28, -54, -70), 10).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                //left hand
                new Sphere(new Point(-38, -54, -65), 10).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //right fingers
                new Sphere(new Point(39, -53, -70), 2).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(38, -49, -70), 2).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(38, -57, -72), 2).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),

                //left fingers
                new Sphere(new Point(-48, -53, -60), 2).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(-48, -49, -60), 2).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),
                new Sphere(new Point(-48, -57, -60), 2).setEmission(poohColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30)),


                //eyes
                new Sphere(new Point(-10, -20, -40d), 4).setEmission(new Color(0, 0, 255))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),
                new Sphere(new Point(5, -20, -40d), 4).setEmission(new Color(0, 0, 255))
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),
                new Sphere(new Point(5.5, -20, -38), 2.3).setEmission(blackColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),
                new Sphere(new Point(-9.5, -20, -38), 2.3).setEmission(blackColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),
                new Sphere(new Point(5.5, -20, -35), 0.5).setEmission(whiteColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),
                new Sphere(new Point(-9.5, -20, -35), 0.5).setEmission(whiteColor)
                        .setMaterial(new Material().setKd(0.1).setKs(0.2).setShininess(30).setKr(0.3)),

                //nose
                new Triangle(
                        new Point(-5, -26, -20),
                        new Point(1, -26, -20),
                        new Point(-2, -30, -20)).
                        setEmission(new Color(250, 75, 0)));

    }

    @Test
    /**
     * build shelf
     */
    public void Shelf(){
        scene.getGeometries().add(
                //shelf -etagere
                new Triangle(
                        new Point(-95, 25, -50),
                        new Point(-85, 17, -45),
                        new Point(-135, 22, -50)).setEmission(blackColor),
                new Triangle(
                        new Point(-85, 18, -45),
                        new Point(-123, 15, -45),
                        new Point(-134, 22, -50)).setEmission(blackColor),

                new Triangle(
                        new Point(-100, 24, -40),
                        new Point(-100, 45, -40),
                        new Point(-92, 22, -40)).setEmission(blackColor),

                new Triangle(
                        new Point(-85, 18, -45),
                        new Point(-124, 15, -45),
                        new Point(-124, 12, -50))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(10)),

                new Triangle(
                        new Point(-85, 18, -45),
                        new Point(-85, 16, -45),
                        new Point(-124, 12, -50))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(10)),

                new Triangle(
                        new Point(-134, 23, -50),
                        new Point(-124, 16, -45),
                        new Point(-124, 12, -50))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(10)),

                new Triangle(
                        new Point(-134, 23, -50),
                        new Point(-134, 20, -50),
                        new Point(-124, 12, -50))
                        .setEmission(new Color(40, 40, 40))
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(10)));

    }

    @Test
    /**
     * build table
     */
    public void Table(){

        //the table
        scene.getGeometries().add(
                //right foot front
                new Triangle(
                new Point(65, -118, 20),
                new Point(69, -118, 20),
                new Point(69, -80, 20)
        ).setEmission(blackColor),
                new Triangle(
                        new Point(65, -118, 20),
                        new Point(65, -80, 20),
                        new Point(69, -80, 20)
                ).setEmission(blackColor),
                //left foot front
                new Triangle(
                        new Point(15, -118, 20),
                        new Point(19, -118, 20),
                        new Point(19, -80, 20)
                ).setEmission(blackColor),
                new Triangle(
                        new Point(15, -118, 20),
                        new Point(15, -80, 20),
                        new Point(19, -80, 20)
                ).setEmission(blackColor),

                //right foot in the back
                new Triangle(
                        new Point(60, -107, 20),
                        new Point(64, -107, 20),
                        new Point(64, -80, 20)
                ).setEmission(blackColor),
                new Triangle(
                        new Point(60, -107, 20),
                        new Point(60, -80, 20),
                        new Point(64, -80, 20)
                ).setEmission(blackColor),

                //left foot in the back
                new Triangle(
                        new Point(10, -107, 20),
                        new Point(14, -107, 20),
                        new Point(14, -80, 20)
                ).setEmission(blackColor),
                new Triangle(
                        new Point(10, -107, 20),
                        new Point(10, -80, 20),
                        new Point(14, -80, 20)
                ).setEmission(blackColor),

                //the plate
                new Triangle(
                        new Point(0, -90, 30),
                        new Point(75, -77, 30),
                        new Point(-7, -77, 30)
                ).setEmission(blackColor)
                        .setMaterial(new Material().setKd(0.001).setKs(0.4).setShininess(50)),
                new Triangle(
                        new Point(0, -90, 30),
                        new Point(75, -77, 30),
                        new Point(85, -90, 30)
                ).setEmission(blackColor)
                        .setMaterial(new Material().setKd(0.001).setKs(0.4).setShininess(50)),

                //reliefs
                new Triangle(
                        new Point(0, -90, 30),
                        new Point(-7, -77, 30),
                        new Point(-7, -81, 30)
                ).setEmission(reliefsColor)
                        .setMaterial(new Material().setKd(0.001).setKs(0.4).setShininess(50)),
                new Triangle(
                        new Point(0, -90, 30),
                        new Point(0, -94, 30),
                        new Point(-7, -81, 30)
                ).setEmission(reliefsColor)
                        .setMaterial(new Material().setKd(0.001).setKs(0.4).setShininess(50)),
                new Triangle(
                        new Point(0, -90, 30),
                        new Point(0, -93, 30),
                        new Point(85, -93, 30)
                ).setEmission(reliefsColor)
                        .setMaterial(new Material().setKd(0.001).setKs(0.4).setShininess(50)),
                new Triangle(
                        new Point(85, -93, 30),
                        new Point(85, -90, 30),
                        new Point(0, -90, 30)
                ).setEmission(reliefsColor)
                        .setMaterial(new Material().setKd(0.001).setKs(0.4).setShininess(50)));

    }


    @Test
    /**
     * build balls
     */
    public void Balls(){
        //balls
        scene.getGeometries().add(
                new Sphere(new Point(-76, -100, 30), 14) //
                .setEmission(redColor) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setKr(0.5).setShininess(30)),
                new Sphere(new Point(-70, -110, 60), 10d) //
                        .setEmission(blueColor) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setKr(0.5).setShininess(30)));
    }





    /**
     * another text
     */
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