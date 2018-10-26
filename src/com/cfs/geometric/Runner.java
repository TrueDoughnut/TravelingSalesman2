package com.cfs.geometric;

import com.cfs.geometric.hull.QuickHull;
import com.cfs.helper.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Runner {

    private Node[] nodes;
    private ArrayList<Node> hull;
    private ArrayList<Node> inner;
    private Node[] path;

    public Runner(int[][] positions){
        this.constructNodes(positions);
        hull = QuickHull.constructHull((ArrayList<Node>)Arrays.asList(nodes));

        inner = new ArrayList<>();
        for(Node node : nodes){
            for(Node x : hull){
                if(!node.equals(x)){
                    inner.add(node);
                }
            }
        }

        path = new Node[nodes.length];
    }
    private void constructNodes(int[][] positions){
        nodes = new Node[positions.length];
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(positions[i][0], positions[i][1]);
        }
    }

    public void run(){
        for(Node node : inner){
            HashMap<Edge, Double> angles = new HashMap<>();
            for(int i = 0; i < hull.size(); i++){
                angles.put(new Edge(hull.get(i), hull.get(i+1)),
                        this.calcAngle(node.distanceTo(hull.get(i)),
                                node.distanceTo(hull.get(i+1)),
                                hull.get(i).distanceTo(hull.get(i+1))));
            }
            double max = -1;
            for(Edge edge : angles.keySet()){
                if(angles.get(edge) > max){
                    max = angles.get(edge);
                }
            }

        }
    }

    private double calcAngle(double a, double b, double c){
        return Math.toDegrees(Math.acos((double)(c * c - a * a - b * b) / (2 * a * b)));
    }

    @Override
    public String toString(){
        return Arrays.toString(path);
    }
}
