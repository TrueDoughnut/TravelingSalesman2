package com.cfs.geometric.hull;

import com.cfs.helper.Node;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteForce {

    private static ArrayList<Node> constructHull(ArrayList<Node> nodes){
        Node[] possible = (Node[]) nodes.toArray();
        ArrayList<Node[]> hulls = new ArrayList<>();
        for(int i = 0; i < possible.length - 2; i++){
            for(int j = i + 1; j < possible.length - 1; j++){
                for(int k = j + 1; k < possible.length; k++){
                    Node[] arr = {possible[i], possible[j], possible[k]};
                    hulls.add(arr);
                }
            }
        }

        ArrayList<Node> not = new ArrayList<>();
        for (Node[] arr : hulls) {
            int[] xPoints, yPoints;
            xPoints = new int[]{arr[0].getXPos(), arr[1].getXPos(), arr[2].getXPos()};
            yPoints = new int[]{arr[0].getYPos(), arr[1].getYPos(), arr[2].getYPos()};
            Polygon polygon = new Polygon(xPoints, yPoints, 3);

            loop:
            for (Node node : possible) {
                for (int j = 0; j < xPoints.length; j++) {
                    if (node.getXPos() == xPoints[j] && node.getYPos() == yPoints[j]) {
                        continue loop;
                    }
                }
                if(polygon.contains(new Point2D.Double(node.getXPos(), node.getYPos()))) {
                    not.add(node);
                }
            }
        }
        ArrayList<Node> hull = new ArrayList<>();
        for(Node node : possible){
            if(!contains(not, node)){
                hull.add(node);
            }
        }

        return hull;
    }

    private static boolean contains(ArrayList<Node> arr, Node node){
        for(Node x : arr){
            if(x.equals(node)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Node> constructHull(Node[] nodes){
        return BruteForce.constructHull((ArrayList<Node>) Arrays.asList(nodes));
    }
}
