package window;

import point.MyCube;

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

        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
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
