package com.cfs.geometric;

import com.cfs.helper.Node;

class Edge {

    private Node start, end;
    private double weight;

    Edge(Node start, Node end){
        this.start = start;
        this.end = end;
        weight = this.start.distanceTo(this.end);
    }

    double getWeight(){
        return weight;
    }
    Node getStart(){
        return start;
    }
    Node getEnd(){
        return end;
    }

    @Override
    public String toString(){
        return this.start.toString() + "->" + this.end.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Edge){
            Edge edge = (Edge)o;
            return edge.getStart().equals(this.getStart()) && edge.getEnd().equals(this.getEnd());
        }
        return false;
    }
}
