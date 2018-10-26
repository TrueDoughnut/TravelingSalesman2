package com.cfs.geometric.hull;

import com.cfs.helper.Node;

import java.awt.*;
import java.util.ArrayList;

public class QuickHull {

    private static ArrayList<Point> quickHull(ArrayList<Point> points){
        ArrayList<Point> convexHull = new ArrayList<>();
        if (points.size() < 3)
            return (ArrayList<Point>) points.clone();

        int minPoint = -1, maxPoint = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < points.size(); i++)
        {
            if (points.get(i).x < minX)
            {
                minX = points.get(i).x;
                minPoint = i;
            }
            if (points.get(i).x > maxX)
            {
                maxX = points.get(i).x;
                maxPoint = i;
            }
        }
        Point A = points.get(minPoint);
        Point B = points.get(maxPoint);
        convexHull.add(A);
        convexHull.add(B);
        points.remove(A);
        points.remove(B);

        ArrayList<Point> leftSet = new ArrayList<Point>();
        ArrayList<Point> rightSet = new ArrayList<Point>();

        for (int i = 0; i < points.size(); i++)
        {
            Point p = points.get(i);
            if (pointLocation(A, B, p) == -1)
                leftSet.add(p);
            else if (pointLocation(A, B, p) == 1)
                rightSet.add(p);
        }
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);

        return convexHull;
    }

    private static int distance(Point A, Point B, Point C){
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        if (num < 0)
            num = -num;
        return num;
    }

    private static void hullSet(Point A, Point B, ArrayList<Point> set,
                        ArrayList<Point> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
            return;
        if (set.size() == 1)
        {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++)
        {
            Point p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist)
            {
                dist = distance;
                furthestPoint = i;
            }
        }
        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        // Determine who's to the left of AP
        ArrayList<Point> leftSetAP = new ArrayList<Point>();
        for (Point M : set) {
            if (pointLocation(A, P, M) == 1) {
                leftSetAP.add(M);
            }
        }

        // Determine who's to the left of PB
        ArrayList<Point> leftSetPB = new ArrayList<Point>();
        for (Point M : set) {
            if (pointLocation(P, B, M) == 1) {
                leftSetPB.add(M);
            }
        }
        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);

    }

    private static int pointLocation(Point A, Point B, Point P){
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        return Integer.compare(cp1, 0);
    }

    public static ArrayList<Node> constructHull(ArrayList<Node> nodes){
        ArrayList<Point> points = new ArrayList<>();
        for(Node node : nodes){
            points.add(new Point(node.getXPos(), node.getYPos()));
        }
        ArrayList<Point> get = quickHull(points);
        ArrayList<Node> returnArr = new ArrayList<>();
        for(Point point : get){
            returnArr.add(new Node(point.x, point.y));
        }
        return returnArr;
    }
}
