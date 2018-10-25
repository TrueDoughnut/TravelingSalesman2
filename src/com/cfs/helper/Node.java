package com.cfs.helper;

public class Node {

    private int xPos, yPos;

    public Node(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    double distanceTo(int x, int y){
        return Math.sqrt(Math.pow(xPos - x, 2) + Math.pow(yPos - y, 2));
    }
    public double distanceTo(Node node){
        return Math.sqrt(Math.pow(xPos - node.xPos, 2) + Math.pow(yPos - node.yPos, 2));
    }


    public int getXPos(){
        return xPos;
    }
    public int getYPos(){
        return yPos;
    }

    @Override
    public String toString(){
        return "(" + xPos + ", " + yPos + ")";
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Node){
            Node x = (Node)o;
            return x.getXPos() == this.getXPos() && x.getYPos() == this.getYPos();
        }
        return super.equals(o);
    }
}
