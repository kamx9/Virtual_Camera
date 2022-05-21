package point;

import polygons.MyPolygon;
import window.Display;

import java.awt.*;

public class MyCube {

    public MyPoint[] myPoints;

    private static final int ROTATE = 2;
    private static final int MOVE = 10;

    public boolean how;

    public MyCube(double a1x, double a1y, double a1z,
                  double b1x, double b1y, double b1z,
                  double c1x, double c1y, double c1z,
                  double d1x, double d1y, double d1z,
                  double a2x, double a2y, double a2z,
                  double b2x, double b2y, double b2z,
                  double c2x, double c2y, double c2z,
                  double d2x, double d2y, double d2z, boolean how){

        this.myPoints = new MyPoint[8];
        this.myPoints[0] = new MyPoint(a1x,a1y,a1z);
        this.myPoints[1] = new MyPoint(b1x,b1y,b1z);
        this.myPoints[2] = new MyPoint(c1x,c1y,c1z);
        this.myPoints[3] = new MyPoint(d1x,d1y,d1z);
        this.myPoints[4] = new MyPoint(a2x,a2y,a2z);
        this.myPoints[5] = new MyPoint(b2x,b2y,b2z);
        this.myPoints[6] = new MyPoint(c2x,c2y,c2z);
        this.myPoints[7] = new MyPoint(d2x,d2y,d2z);

        this.how = how;
    }

    public void render(Graphics g){
        g.setColor(Display.colors[1]);
        if(this.how){
            g.setColor(Display.colors[3]);
        }
        Point points[] = new Point[8];

        for (int i = 0; i < this.myPoints.length; i++) {
            points[i] = PointConverter.convertPerspectivePoint(this.myPoints[i], Display.scale);
        }

        for (int i = 0; i < points.length/2; i++) {
            if(i != 3) {
                g.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
            } else {
                g.drawLine(points[i].x, points[i].y, points[i-3].x, points[i-3].y);
            }
        }

        g.setColor(Display.colors[2]);
        if(this.how){
            g.setColor(Display.colors[3]);
        }

        for (int i = 4; i < points.length; i++) {
            if(i != 7) {
                g.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
            } else {
                g.drawLine(points[i].x, points[i].y, points[i-3].x, points[i-3].y);
            }

        }
        g.setColor(Display.colors[3]);
        if(this.how){
            g.setColor(Display.colors[3]);
        }
        for (int i = 0; i < points.length/2; i++) {
            g.drawLine(points[i].x, points[i].y, points[i+4].x, points[i+4].y);
        }

    }

    public MyPolygon[] getPolygons(){
        MyPolygon[] polygons = new MyPolygon[6];

        for(int i = 0; i < 2; i++){
            int k = i*4;
            if(how){
                polygons[i] = new MyPolygon(4, this.myPoints[k], this.myPoints[k+1], this.myPoints[k+2], this.myPoints[k+3]);

            } else {
                polygons[i] = new MyPolygon(i+1, this.myPoints[k], this.myPoints[k+1], this.myPoints[k+2], this.myPoints[k+3]);

            }
        }
        for(int i = 0; i < 3; i++){
            if(how){
                polygons[i+2] = new MyPolygon(4, this.myPoints[i], this.myPoints[i+1], this.myPoints[i+5], this.myPoints[i+4]);

            } else {
                polygons[i+2] = new MyPolygon(i+3, this.myPoints[i], this.myPoints[i+1], this.myPoints[i+5], this.myPoints[i+4]);

            }
        }
        if(how){
            polygons[5] = new MyPolygon(4, this.myPoints[0],this.myPoints[4], this.myPoints[7],this.myPoints[3]);

        } else{
            polygons[5] = new MyPolygon(6, this.myPoints[0],this.myPoints[4], this.myPoints[7],this.myPoints[3]);

        }

        return polygons;
    }



    public void rotateCubeX(boolean direct) {
        for (int i = 0; i < myPoints.length; i++) {
            myPoints[i] = PointConverter.rotateAxisX(myPoints[i], direct, ROTATE);
        }
    }

    public void rotateCubeY(boolean direct) {
        for (int i = 0; i < myPoints.length; i++) {
            myPoints[i] = PointConverter.rotateAxisY(myPoints[i], direct, ROTATE);
        }
    }

    public void rotateCubeZ(boolean direct) {
        for (int i = 0; i < myPoints.length; i++) {
            myPoints[i] = PointConverter.rotateAxisZ(myPoints[i], direct, ROTATE);
        }
    }

    public void moveCubeX(boolean direct) {
        for (int i = 0; i < myPoints.length; i++) {
            myPoints[i].x += MOVE * (direct? 1:-1);
        }
    }

    public void moveCubeY(boolean direct) {
        for (int i = 0; i < myPoints.length; i++) {
            myPoints[i].y += MOVE * (direct? 1:-1);
        }
    }

    public void moveCubeZ(boolean direct) {
        for (int i = 0; i < myPoints.length; i++) {
            myPoints[i].z += MOVE * (direct? 1:-1);
        }
    }

    public void zoom(boolean in_out){
        if(Display.scale >= 0.1 && in_out == false) {
            Display.scale *= 0.95;
        }
        if(in_out == true){
            Display.scale *= 1.05;
        }
    }




}
