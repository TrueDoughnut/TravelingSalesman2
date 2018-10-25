package com.cfs.geometric;

import com.cfs.geometric.hull.QuickHull;
import com.cfs.helper.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class Runner {

    private Node[] nodes;
    private ArrayList<Edge> edges;
    private ArrayList<Node> hull;
    private Node[] path;

    public Runner(int[][] positions){
        this.constructNodes(positions);
        this.constructEdges();
        hull = QuickHull.constructHull((ArrayList<Node>)Arrays.asList(nodes));

        path = new Node[nodes.length];
    }
    private void constructNodes(int[][] positions){
        nodes = new Node[positions.length];
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(positions[i][0], positions[i][1]);
        }
    }
    private void constructEdges(){
        edges = new ArrayList<>();
        for(int i = 0; i < nodes.length; i++){
            for(int j = i + 1; j < nodes.length; j++){
                try {
                    edges.add(new Edge(nodes[i], nodes[j]));
                } catch(IndexOutOfBoundsException e){
                    edges.add(new Edge(nodes[i], nodes[0]));
                    e.printStackTrace();
                }
            }
        }
    }

    public void run(){

    }

    private double calcAngle(int a, int b, int c){
        return Math.toDegrees(Math.acos((double)(c * c - a * a - b * b) / (2 * a * b)));
    }

    @Override
    public String toString(){
        return Arrays.toString(path);
    }
}
