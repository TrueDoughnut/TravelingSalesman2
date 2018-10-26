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
    private ArrayList<Node> inner;
    private Edge[] path;
    private double length = 0;

    public Runner(int[][] positions){
        this.constructNodes(positions);
        this.hull = QuickHull.constructHull(new ArrayList<>(Arrays.asList(nodes)));

        inner = new ArrayList<>();
        for(Node node : nodes){
            for(Node x : hull){
                if(!node.equals(x)){
                    inner.add(node);
                }
            }
        }

        path = new Edge[nodes.length];
    }
    private void constructNodes(int[][] positions){
        nodes = new Node[positions.length];
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(positions[i][0], positions[i][1]);
        }
    }

    public void run(){
        if(inner.size() == 0){
            createPath(hull);
            return;
        }
        for(Node node : inner){
            HashMap<Edge, Double> angles = new HashMap<>();
            for(int i = 0; i < hull.size(); i++){
                try {
                    angles.put(new Edge(hull.get(i), hull.get(i + 1)),
                            this.calcAngle(node.distanceTo(hull.get(i)),
                                    node.distanceTo(hull.get(i + 1)),
                                    hull.get(i).distanceTo(hull.get(i + 1))));
                } catch(IndexOutOfBoundsException e){
                    angles.put(new Edge(hull.get(i), hull.get(0)),
                            this.calcAngle(node.distanceTo(hull.get(i)),
                                    node.distanceTo(hull.get(0)),
                                    hull.get(i).distanceTo(hull.get(0))));
                }
            }
            double max = -1;
            Edge largest = null;
            angles.values().removeAll(Collections.singleton(Double.NaN));
            for(Edge edge : angles.keySet()){
                if(angles.get(edge) > max){
                    max = angles.get(edge);
                    largest = edge;
                }
            }

            for(int i = 0; i < hull.size(); i++){
                if (largest != null && hull.get(i).equals(largest.getStart())) {
                    try {
                        hull.add(i + 1, node);
                    } catch (IndexOutOfBoundsException e) {
                        hull.add(node);
                    }
                }
            }
        }
        createPath(hull);
        for(Edge edge : path){
            length += edge.getWeight();
        }
    }

    private void createPath(ArrayList<Node> hull){
        for(int i = 0; i < path.length; i++){
            try {
                path[i] = new Edge(hull.get(i), hull.get(i + 1));
            } catch(IndexOutOfBoundsException e){
                path[i] = new Edge(hull.get(i), hull.get(0));
            }
        }
    }

    private double calcAngle(double a, double b, double c){
        return Math.toDegrees(Math.acos((c * c - a * a - b * b) / (2 * a * b)));
    }

    @Override
    public String toString(){
        return length + "\n" + Arrays.toString(path);
    }
}
