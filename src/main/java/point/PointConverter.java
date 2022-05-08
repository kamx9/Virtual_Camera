package point;

import window.Display;

import java.awt.*;

public class PointConverter {

    public static Point convertPerspectivePoint(MyPoint point3D, double scale) {

        double d = 200;

        double x2d = point3D.x / ( 1 + (point3D.z / d));
        double y2d = point3D.y / ( 1 + (point3D.z / d));

        int x = (int) (Display.WIDTH / 2 + x2d * scale);
        int y = (int) (Display.HEIGHT / 2 + y2d * scale);

        Point point2D = new Point(x,y);

        return point2D;
    }

    public static MyPoint rotateAxisX(MyPoint p, boolean direct, double degrees){

        double radius = Math.sqrt(p.y*p.y + p.z*p.z);
        double theta = Math.atan2(p.z, p.y);
        theta += 2*Math.PI/360*degrees*(direct?-1:1);
        double y = radius * Math.cos(theta);
        double z = radius * Math.sin(theta);

        return new MyPoint(p.x, y, z);
    }

    public static MyPoint rotateAxisY(MyPoint p, boolean direct, double degrees){

        double radius = Math.sqrt(p.x*p.x + p.z*p.z);
        double theta = Math.atan2(p.x, p.z);
        theta += 2*Math.PI/360*degrees*(direct?-1:1);
        double x = radius * Math.sin(theta);
        double z = radius * Math.cos(theta);

        return new MyPoint(x, p.y, z);
    }

    public static MyPoint rotateAxisZ(MyPoint p, boolean direct, double degrees){

        double radius = Math.sqrt(p.y*p.y + p.x*p.x);
        double theta = Math.atan2(p.y, p.x);
        theta += 2*Math.PI/360*degrees*(direct?-1:1);
        double y = radius * Math.sin(theta);
        double x = radius * Math.cos(theta);

        return new MyPoint(x, y, p.z);
    }


}
