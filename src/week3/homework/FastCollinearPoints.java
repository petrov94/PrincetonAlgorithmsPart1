package week3.homework;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private Point[] points;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            } else if (i != points.length - 1 && points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        this.points = points;
    }

    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    public LineSegment[] segments() {
        ArrayList<LineSegment> lineSegments = new ArrayList<>();
        getAllSegments(lineSegments, points);
        return lineSegments.toArray(new LineSegment[0]);
    }

    private void getAllSegments(ArrayList<LineSegment> lineSegments, Point[] points) {

        for (Point point : points) {
            Point[] copyPoints = points.clone();
            Arrays.sort(copyPoints, point.slopeOrder());
            double tmpSlope = point.slopeTo(copyPoints[0]);
            int count = 1;
            int i;
            for (i = 1; i < copyPoints.length; i++) {
                if (point.slopeTo(copyPoints[i]) == tmpSlope) {
                    count++;
                    continue;
                } else {
                    if (count >= 3) {
                        Arrays.sort(copyPoints, i - count, i);
                        if (point.compareTo(copyPoints[i - count]) < 0)
                            lineSegments.add(new LineSegment(point, copyPoints[i - 1]));
                    }
                    tmpSlope = point.slopeTo(copyPoints[i]);
                    count = 1;
                }
            }
            if (count >= 3) {
                Arrays.sort(copyPoints, i - count, i);
                if (point.compareTo(copyPoints[i - count]) < 0)
                    lineSegments.add(new LineSegment(point, copyPoints[i - 1]));
            }
        }
        this.lineSegments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
