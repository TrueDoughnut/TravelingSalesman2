package com.cfs.geometric.hull;

import com.cfs.helper.Node;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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

        Iterator<Node[]> iterator = hulls.iterator();
        while(iterator.hasNext()){
            Node[] arr = iterator.next();
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
                if(polygon.contains(new Point2D.Double(node.getXPos(), node.getYPos()))){
                    iterator = remove(iterator, node);
                }
            }
        }

        return nodes;
    }

    private static Iterator<Node[]> remove(Iterator<Node[]> old, Node node){
        while(old.hasNext()){
            Node[] arr = old.next();
            for(Node x : arr){
                if(x.equals(node)){
                    old.remove();
                }
            }
        }
        return old;
    }

    public static ArrayList<Node> constructHull(Node[] nodes){
        return BruteForce.constructHull((ArrayList<Node>) Arrays.asList(nodes));
    }
}
