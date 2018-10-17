package com.cfs.runner;

class Node {

    private int xPos, yPos;

    Node(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    double distanceTo(int x, int y){
        return Math.sqrt(Math.pow(xPos - x, 2) + Math.pow(yPos - y, 2));
    }

    int getXPos(){
        return xPos;
    }
    int getYPos(){
        return yPos;
    }
}
