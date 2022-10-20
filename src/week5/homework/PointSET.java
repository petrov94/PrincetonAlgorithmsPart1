package week5.homework;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

public class PointSET {

    private final TreeSet<Point2D> points = new TreeSet<>();

    public PointSET() {

    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if(p==null) throw new IllegalArgumentException("point cannot be null");
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if(p==null) throw new IllegalArgumentException("point cannot be null");
        return points.contains(p);
    }

    public void draw() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        for(Point2D point2D : points){
            point2D.draw();
        }
        StdDraw.show();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if(rect==null) throw new IllegalArgumentException("rect cannot be null");
        SET<Point2D> point2DS = new SET<>();
        for(Point2D point2D : points){
            if(rect.contains(point2D)){
                point2DS.add(point2D);
            }
        }
        return point2DS;
    }

    public Point2D nearest(Point2D p) {
        if(p==null) throw new IllegalArgumentException("rect cannot be null");
        Point2D point=null;
        for(Point2D point2D : points){
            if(point==null ||point.distanceSquaredTo(p)>point2D.distanceSquaredTo(p)){
                point=point2D;
            }
        }
        return point;
    }
}