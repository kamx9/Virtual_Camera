package window;

import point.MyCube;
import polygons.MyPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Display extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;

    private Thread thread;
    private JFrame frame;
    private static String title = "Wirtualna kamera";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static double scale;
    public static boolean running = false;
    public static MyCube [] cubes;

    public static Color[] colors;
    public static boolean isPolygon = false;

    public static boolean isDividePolygon = true;

    public static boolean how = false; // tu zmienia się co ma być rysowane false -> dwie powierzchnie / true -> sześciany

    private Keyboard keyboard;

    public Display() {
        this.frame = new JFrame();

        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);

        this.keyboard = new Keyboard();
        this.scale = 1;

        this.addKeyListener(this.keyboard);
    }

    public static void main(String[] args) {
        Display display = new Display();
        display.frame.setTitle(title);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setResizable(false);
        display.frame.setVisible(true);

        display.start();
    }

    public synchronized void start() {
        this.cubes = createCubes();
        running = true;
        this.colors = new Color[] {Color.BLACK, Color.WHITE, Color.YELLOW, Color.RED, Color.BLUE, Color.MAGENTA, Color.GREEN};
        this.thread = new Thread(this, "window.Display");
        this.thread.start();
        this.frame.addKeyListener(this.keyboard);
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60;
        double delta = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
                render();
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(colors[0]);
        g.fillRect(0,0, WIDTH * 2, HEIGHT * 2);

        renderCubes(g);

        g.dispose();
        bs.show();
    }


    public static MyCube[] createCubes(){

        if(how){
            MyCube[] newCubes = new MyCube[4];

            newCubes[0] = new MyCube(-300,100,200,-100,100,200,
                    -100,-100,200,-300,-100,200,
                    -300,100,400,-100,100,400,
                    -100,-100,400,-300,-100,400, false);

            newCubes[1] = new MyCube(100,100,200,300,100,200,
                    300,-300,200,100,-300,200,
                    100,100,400,300,100,400,
                    300,-300,400,100,-300,400, false);

            newCubes[2] = new MyCube(-300,100,600,-100,100,600,
                    -100,-350,600,-300,-350,600,
                    -300,100,700,-100,100,700,
                    -100,-350,700,-300,-350,700, false);

            newCubes[3] = new MyCube(100,100,700,300,100,700,
                    300,-200,700,100,-200,700,
                    100,100,1000,300,100,1000,
                    300,-200,1000,100,-200,1000, false);

            return newCubes;
        } else {
            MyCube[] newCubes = new MyCube[2];

            newCubes[0] = new MyCube(-100,-100,200,100,-100,200,
                    100,-500,400,-100,-500,400,
                    -100,-100,200,100,-100,200,
                    100,-500,400,-100,-500,400, false); // ten zółty

            newCubes[1] = new MyCube(-150,100,200,150,100,200,
                    150,-300,400,-150,-300,400,
                    -150,100,200,150,100,200,
                    150,-300,400,-150,-300,400, true);  // ten niebieski
            return newCubes;
        }

    }

    private void renderCubes(Graphics g){

        if(isPolygon == false){
            for (MyCube c: this.cubes) {
                c.render(g);
            }
        } else {
            List<MyPolygon[]> allPolygonsCubes = new ArrayList<>();
            for (MyCube c: this.cubes) {

                if(isDividePolygon){
                    MyPolygon[] tmp = c.getPolygons();
                    for (MyPolygon t: tmp) {
                        allPolygonsCubes.add(MyPolygon.dividePolygon(t, t.getColorInt()));
                    }
                } else {
                    allPolygonsCubes.add(c.getPolygons());
                }
            }

            MyPolygon[] result = MyPolygon.sortPolygons(allPolygonsCubes);

            for (MyPolygon p : result) {
                p.render(g);
            }





        }
    }
}
