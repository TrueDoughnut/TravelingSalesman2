package com.cfs.geometric.hull;

import com.cfs.helper.Node;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteForce {

    private static Node[] possible;

    private static ArrayList<Node> constructHull(ArrayList<Node> nodes){
        possible = (Node[]) nodes.toArray();
        for(int i = 0; i < possible.length - 2; i++){
            for(int j = i + 1; j < possible.length - 1; j++){
                for(int k = j + 1; k < possible.length; k++){

                }
            }
        }
        return nodes;
    }

    public static ArrayList<Node> constructHull(Node[] nodes){
        return BruteForce.constructHull((ArrayList<Node>) Arrays.asList(nodes));
    }
}
