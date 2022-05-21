package window;

import point.MyCube;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private void update(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP){
            for (MyCube c: Display.cubes) {
                c.moveCubeZ(false);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            for (MyCube c: Display.cubes) {
                c.moveCubeZ(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            for (MyCube c: Display.cubes) {
                c.moveCubeX(false);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            for (MyCube c: Display.cubes) {
                c.moveCubeX(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            for (MyCube c: Display.cubes) {
                c.moveCubeY(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            for (MyCube c: Display.cubes) {
                c.moveCubeY(false);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            for (MyCube c: Display.cubes) {
                c.rotateCubeX(false);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            for (MyCube c: Display.cubes) {
                c.rotateCubeX(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_A){
            for (MyCube c: Display.cubes) {
                c.rotateCubeY(false);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_D){
            for (MyCube c: Display.cubes) {
                c.rotateCubeY(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_E){
            for (MyCube c: Display.cubes) {
                c.rotateCubeZ(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_Q){
            for (MyCube c: Display.cubes) {
                c.rotateCubeZ(false);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_1){
            for (MyCube c: Display.cubes) {
                c.zoom(true);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_2){
            for (MyCube c: Display.cubes) {
                c.zoom(false);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            Display.cubes = Display.createCubes();
            Display.scale = 1;
        }

        if(e.getKeyCode() == KeyEvent.VK_0){
            if(Display.colors[0] == Color.BLACK){
                Display.colors = new Color[]{Color.WHITE, Color.BLACK, Color.DARK_GRAY, Color.RED, Color.BLUE, Color.MAGENTA, Color.GREEN};
            } else {
                Display.colors = new Color[]{Color.BLACK, Color.WHITE, Color.YELLOW, Color.RED, Color.BLUE, Color.MAGENTA, Color.GREEN };
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_P){
            if(Display.isPolygon){
                Display.isPolygon = false;
            } else {
                Display.isPolygon = true;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_O){
            if(Display.isDividePolygon){
                Display.isDividePolygon = false;
            } else {
                Display.isDividePolygon = true;
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_Z){
            if(Display.how){
                Display.how = false;
            } else {
                Display.how = true;
            }
        }



    }

    @Override
    public void keyPressed(KeyEvent e) {
        update(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
