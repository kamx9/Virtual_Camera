package polygons;

import point.MyPoint;
import point.PointConverter;
import window.Display;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyPolygon {

    private MyPoint[] points;
    public int colorInt;
    private Color color;

    public MyPolygon(int color, MyPoint... points) {
        this.colorInt = color;
        this.color = Display.colors[color];
        this.points = new MyPoint[points.length];
        for(int i = 0; i < points.length; i++) {
            MyPoint p = points[i];
            this.points[i] = new MyPoint(p.x, p.y, p.z);
        }
    }

    public MyPoint getPoint(int i){
        return this.points[i];
    }

    public void render(Graphics g) {
        Polygon poly = new Polygon();
        for(int i = 0; i < this.points.length; i++) {
            Point p = PointConverter.convertPerspectivePoint(this.points[i], Display.scale);
            poly.addPoint(p.x, p.y);
        }

        g.setColor(this.color);
        g.fillPolygon(poly);
    }

    public double getAverageZ() {
        double sum = 0;
        for(MyPoint p : this.points) {
            sum += p.z;
        }

        return sum / this.points.length;
    }

    public double getAverageX() {
        double sum = 0;
        for(MyPoint p : this.points) {
            sum += p.x;
        }

        return sum / this.points.length;
    }

    public double getAverageY() {
        double sum = 0;
        for(MyPoint p : this.points) {
            sum += p.y;
        }

        return sum / this.points.length;
    }

    public double getMaxZ() {
        double max = 0;
        for(MyPoint p : this.points) {
            if(p.z > max){
                max = p.z;
            }
        }
        return max;
    }

    public static MyPolygon[] sortPolygons(List<MyPolygon[]> polygons) {
        List<MyPolygon> polygonsList = new ArrayList<>();

        for(MyPolygon[] poly : polygons) {
            for (MyPolygon p : poly) {
                polygonsList.add(p);
            }
        }

        Collections.sort(polygonsList, new Comparator<MyPolygon>() {
            @Override
            public int compare(MyPolygon p1, MyPolygon p2) {
                double diff = 0;
                if(diff == 0) {
                    double p2Average = Math.sqrt((Math.pow(p2.getAverageX(), 2)
                                                   + Math.pow(p2.getAverageY(), 2)
                                                   + Math.pow(p2.getAverageZ(), 2)));
                    double p1Average = Math.sqrt((Math.pow(p1.getAverageX(), 2)
                            + Math.pow(p1.getAverageY(), 2)
                            + Math.pow(p1.getAverageZ(), 2)));
                    diff = p2Average - p1Average;

                }

                if(diff == 0) {
                    return 0;
                }

                return diff < 0 ? -1 : 1;
            }
        });

        MyPolygon[] result = new MyPolygon[polygonsList.size()];

        for(int i = 0; i < result.length; i++) {
            result[i] = polygonsList.get(i);
        }

        return result;
    }

    public int getColorInt(){
        return this.colorInt;
    }

    public static MyPolygon[] dividePolygon(MyPolygon myP, int color){

        int k = 8;
        int kk = k*k;

        MyPoint[] newPoints = new MyPoint[81];

        newPoints[0] = myP.getPoint(0);
        newPoints[8] = myP.getPoint(1);
        newPoints[80] = myP.getPoint(2);
        newPoints[72] = myP.getPoint(3);

        newPoints[36] = averagePoint(newPoints[0], newPoints[72]);
        newPoints[18] = averagePoint(newPoints[0], newPoints[36]);
        newPoints[9] = averagePoint(newPoints[0], newPoints[18]);
        newPoints[27] = averagePoint(newPoints[18], newPoints[36]);
        newPoints[54] = averagePoint(newPoints[36], newPoints[72]);
        newPoints[45] = averagePoint(newPoints[36], newPoints[54]);
        newPoints[63] = averagePoint(newPoints[54], newPoints[72]);

        newPoints[44] = averagePoint(newPoints[8], newPoints[80]);
        newPoints[26] = averagePoint(newPoints[8], newPoints[44]);
        newPoints[17] = averagePoint(newPoints[8], newPoints[26]);
        newPoints[35] = averagePoint(newPoints[26], newPoints[44]);
        newPoints[62] = averagePoint(newPoints[44], newPoints[80]);
        newPoints[53] = averagePoint(newPoints[44], newPoints[62]);
        newPoints[71] = averagePoint(newPoints[80], newPoints[62]);

        for (int i = 0; i < 9; i++){
            int t = i*(k+1);

            newPoints[4+t] = averagePoint(newPoints[0+t], newPoints[8+t]);
            newPoints[2+t] = averagePoint(newPoints[0+t], newPoints[4+t]);
            newPoints[1+t] = averagePoint(newPoints[0+t], newPoints[2+t]);
            newPoints[3+t] = averagePoint(newPoints[2+t], newPoints[4+t]);
            newPoints[6+t] = averagePoint(newPoints[4+t], newPoints[8+t]);
            newPoints[5+t] = averagePoint(newPoints[4+t], newPoints[6+t]);
            newPoints[7+t] = averagePoint(newPoints[6+t], newPoints[8+t]);
        }

        MyPolygon[] polygons = new MyPolygon[kk];

        int j = 0;
        for(int i = 0; i < kk; i++){
            if(i%8 == 0 && i != 0){
                j += 1;
            }
            polygons[i] = new MyPolygon(color, newPoints[0+j], newPoints[1+j], newPoints[10+j], newPoints[9+j]);
            j++;
        }


        return polygons;
    }

    private static MyPoint averagePoint(MyPoint a, MyPoint b){
        return new MyPoint((a.x+b.x)/2, (a.y+b.y)/2, (a.z+b.z)/2);
    }

}
