package week5.homework;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private static final double XMIN = 0.0;
    private static final double XMAX = 1.0;
    private static final double YMIN = 0.0;
    private static final double YMAX = 1.0;
    private Node root;
    private int size=0;

    private class Node {
        private final Point2D point2D;
        private final RectHV rectHV;
        private Node left;
        private Node right;

        public Node(Point2D point2D, RectHV rectHV) {
            this.point2D = point2D;
            this.rectHV=rectHV;
            this.left = null;
            this.right = null;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        if (root == null) {
            size++;
            root = new Node(p, new RectHV(XMIN, YMIN, XMAX, YMAX));
            return;
        }
        insert(p, root, false);
    }

    private void insert(Point2D p, Node node, boolean isX) {
        double comparison = compare(p, node.point2D, !isX);
        if (comparison < 0) {
            if (node.left != null)
                insert(p, node.left, !isX);
            else {
                size++;
                if (isX)
                    node.left = new Node(p, new RectHV(node.rectHV.xmin(), node.rectHV.ymin(), node.rectHV.xmax(), node.point2D.y()));
                else
                    node.left = new Node(p, new RectHV(node.rectHV.xmin(), node.rectHV.ymin(), node.point2D.x(),node.rectHV.ymax()));
            }
        } else if (comparison > 0) {
            if (node.right != null)
                insert(p, node.right, !isX);
            else {
                size++;
                if (isX)
                    node.right = new Node(p, new RectHV(node.rectHV.xmin(), node.point2D.y(),node.rectHV.xmax() ,node.rectHV.ymax()));
                else
                    node.right = new Node(p, new RectHV(node.point2D.x(), node.rectHV.ymin(),node.rectHV.xmax(), node.rectHV.ymax()));
            }
        }
    }

//boolean false
    private double compare(Point2D newPoint, Point2D point2D, boolean isX) {
        double comparison;
        if (isX) {
            comparison = newPoint.x() - point2D.x();
            if (comparison == 0) {
                return newPoint.y() - point2D.y();
            } else {
                return comparison;
            }
        } else {
             comparison = newPoint.y() - point2D.y();
            if (comparison == 0) {
                return newPoint.x() - point2D.x();
            } else {
                return comparison;
            }
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        Node secondRoot = root;
        return contains(p, secondRoot, true);
    }

    private boolean contains(Point2D point2D, Node node, boolean isX) {
        if (node == null) return false;
        double comparison = compare(point2D, node.point2D, isX);
        if (comparison < 0) {
            return contains(point2D, node.left, !isX);
        } else if (comparison > 0) {
            return contains(point2D, node.right, !isX);
        } else {
            return true;
        }
    }


    public void draw() {
        StdDraw.clear();
        Node rootNode = root;
        drawLine(rootNode, true);
    }

    private void drawLine(Node node, boolean isX) {
        if (node == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        node.point2D.draw();
        if (isX) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.point2D.x(), node.rectHV.ymin(), node.point2D.x(), node.rectHV.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rectHV.xmin(), node.point2D.y(), node.rectHV.xmax(), node.point2D.y());
        }
        drawLine(node.left,!isX);
        drawLine(node.right,!isX);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException("rect cannot be null");
        SET<Point2D> point2DS = new SET<>();
        Node node = root;
        rangeSubTree(rect, point2DS, node);
        return point2DS;
    }

    private void rangeSubTree(RectHV rectHV, SET<Point2D> point2DS, Node node) {
        if (node == null || !node.rectHV.intersects(rectHV)) {
            return;
        }
        if (rectHV.contains(node.point2D)) {
            point2DS.add(node.point2D);
        }
        rangeSubTree(rectHV, point2DS, node.right);
        rangeSubTree(rectHV, point2DS, node.left);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point cannot be null");
        if(root==null) return null;
        Node node = root;
        Point2D closest = node.point2D;
        double min = Double.MAX_VALUE;
        while (node!=null){
            if(min > node.point2D.distanceSquaredTo(p)){
                closest = node.point2D;
                min=node.point2D.distanceSquaredTo(p);
            }

            double leftSubTree = node.left !=null ? node.left.point2D.distanceSquaredTo(p) : Double.MAX_VALUE;
            double rightSubTree = node.right!=null ? node.right.point2D.distanceSquaredTo(p) : Double.MAX_VALUE;
            
            if(leftSubTree<rightSubTree){
                node=node.left;
            }else if (rightSubTree<leftSubTree) {
                node=node.right;
            }else {
                break;
            }
        }
        return closest;
    }
}
