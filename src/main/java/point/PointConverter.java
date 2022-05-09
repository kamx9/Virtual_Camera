package point;

import window.Display;

import java.awt.*;

public class PointConverter {

    private static final double d = 800;

    public static Point convertPerspectivePoint(MyPoint point3D, double scale) {

        double x2d = point3D.x / ( 1 + (point3D.z / d));
        double y2d = point3D.y / ( 1 + (point3D.z / d));

        int x = (int) (Display.WIDTH / 2 + x2d * scale);
        int y = (int) (Display.HEIGHT / 2 + y2d * scale);

        Point point2D = new Point(x,y);

        return point2D;
    }

    public static MyPoint rotateAxisX(MyPoint p, boolean direct, double degrees){

        double pz = p.z + d;
        double radius = Math.sqrt(p.y*p.y + pz*pz);
        double t = Math.atan2(pz, p.y);

        t += 2 * Math.PI / 360 * degrees * (direct?-1:1);

        double y = radius * Math.cos(t);
        double z = radius * Math.sin(t);

        return new MyPoint(p.x, y, z - d);
    }

    public static MyPoint rotateAxisY(MyPoint p, boolean direct, double degrees){

        double pz = p.z + d;

        double radius = Math.sqrt(p.x*p.x + pz*pz);
        double t = Math.atan2(p.x, pz);

        t += 2 * Math.PI / 360 * degrees * (direct?-1:1);

        double x = radius * Math.sin(t);
        double z = radius * Math.cos(t);

        return new MyPoint(x, p.y, z - d);
    }

    public static MyPoint rotateAxisZ(MyPoint p, boolean direct, double degrees){

        double radius = Math.sqrt(p.y*p.y + p.x*p.x);
        double t = Math.atan2(p.y, p.x);

        t += 2 * Math.PI / 360 * degrees * (direct?-1:1);

        double y = radius * Math.sin(t);
        double x = radius * Math.cos(t);

        return new MyPoint(x, y, p.z);
    }


}
