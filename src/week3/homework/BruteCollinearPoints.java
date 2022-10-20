package week3.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BruteCollinearPoints {

    private Point[] points;
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
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
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        ArrayList<LineSegment> storeSegments = new ArrayList<LineSegment>();
        Arrays.sort(points);
        int n = points.length;
        for (int i = 0; i < n-3; i++)
            for (int j = i + 1; j < n-2; j++)
                for (int k = j + 1; k < n-1; k++) {
                    if (points[i].slopeTo(points[j]) != points[i].slopeTo(points[k]))
                        continue;
                    for (int l = k + 1; l < n; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))
                            storeSegments.add(new LineSegment(points[i], points[l]));
                    }
                }
        lineSegments = storeSegments.toArray(new LineSegment[storeSegments.size()]);
        return lineSegments;
    }


}
