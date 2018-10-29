package com.cfs.geometric;

import com.cfs.geometric.hull.QuickHull;
import com.cfs.helper.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Runner {

    private Node[] nodes;
    private ArrayList<Node> hull;
    private ArrayList<Edge> hullEdge;
    private ArrayList<Node> inner;
    private double length = 0;

    public Runner(int[][] positions){
        this.constructNodes(positions);
        this.hull = QuickHull.constructHull(new ArrayList<>(Arrays.asList(nodes)));

        hullEdge = new ArrayList<>();
        for(int i = 0; i < hull.size(); i++){
            try {
                hullEdge.add(new Edge(this.hull.get(i), this.hull.get(i + 1)));
            } catch(IndexOutOfBoundsException e){
                hullEdge.add(new Edge(this.hull.get(i), this.hull.get(0)));
            }
        }
        inner = new ArrayList<>();
        for(Node node : nodes){
            for(Node x : hull){
                if(!node.equals(x)){
                    inner.add(node);
                }
            }
        }
    }
    private void constructNodes(int[][] positions){
        nodes = new Node[positions.length];
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(positions[i][0], positions[i][1]);
        }
    }

    public void run(){
        if(inner.size() == 0){
            return;
        }
        for(Node node : inner) {
            HashMap<Edge, Double> angles = new HashMap<>();
            for(int i = 0; i < hull.size() - 1; i++){
                double a = node.distanceTo(hull.get(i));
                double b = node.distanceTo(hull.get(i+1));
                double c = hull.get(i).distanceTo(hull.get(i+1));
                double angle = this.calcAngle(a, b, c);
                angles.put(new Edge(hull.get(i), hull.get(i+1)), angle);
            }

            double max = -1;
            Edge remove = null;
            for(Edge edge : angles.keySet()){
                if(angles.get(edge) > max){
                    max = angles.get(edge);
                    remove = edge;
                }
            }

            for(int i = 0; i < hullEdge.size(); i++){
                Edge edge = hullEdge.get(i);
                if(edge.equals(remove)){
                    hullEdge.set(i, new Edge(edge.getStart(), node));
                    hullEdge.add(i+1, new Edge(node, edge.getStart()));
                }
            }
        }
        for(Edge edge : hullEdge){
            length += edge.getWeight();
        }
    }

    private double calcAngle(double a, double b, double c){
        return Math.toDegrees(Math.acos((c * c - a * a - b * b) / (-2 * a * b)));
    }

    @Override
    public String toString(){
        return length + "\n" + hullEdge;
    }
}
