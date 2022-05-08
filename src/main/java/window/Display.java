package window;

import point.MyCube;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends Canvas implements Runnable{

    private static final long serialVersionUID = 1L;

    private Thread thread;
    private JFrame frame;
    private static String title = "Wirtualna kamera";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static boolean running = false;
    public static MyCube [] cubes;

    private Keyboard keyboard;

    public Display() {
        this.frame = new JFrame();

        Dimension size = new Dimension(WIDTH, HEIGHT);
        this.setPreferredSize(size);

        this.keyboard = new Keyboard();

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
        createCubes();
        running = true;
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
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60;
        double delta = 0;
        int frames = 0;


        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
                render();
                frames++;
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

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH * 2, HEIGHT * 2);

        renderCubes(g);

        g.dispose();
        bs.show();
    }


    private void createCubes(){

        this.cubes = new MyCube[4];

        this.cubes[0] = new MyCube(-300,100,200,-100,100,200,
                -100,-100,200,-300,-100,200,
                -300,100,400,-100,100,400,
                -100,-100,400,-300,-100,400);

        this.cubes[1] = new MyCube(100,100,200,300,100,200,
                300,-300,200,100,-300,200,
                100,100,400,300,100,400,
                300,-300,400,100,-300,400);

        this.cubes[2] = new MyCube(-300,100,600,-100,100,600,
                -100,-350,600,-300,-350,600,
                -300,100,700,-100,100,700,
                -100,-350,700,-300,-350,700);

        this.cubes[3] = new MyCube(100,100,700,300,100,700,
                300,-200,700,100,-200,700,
                100,100,1000,300,100,1000,
                300,-200,1000,100,-200,1000);
    }

    private void renderCubes(Graphics g){
        for (MyCube c: this.cubes) {
            c.render(g);
        }
    }
}