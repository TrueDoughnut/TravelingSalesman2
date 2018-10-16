package com.cfs.runner;

class Node {

    private int xPos, yPos;
    private double[] distances;
    private int[][] positions;

    Node(int[][] positions, int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.positions = positions;
        this.distances = new double[positions.length];
        calculateDistances();
    }

    private void calculateDistances(){
        int i = 0;
        for(int[] arr : positions){
            int x = arr[0], y = arr[1];
            distances[i++] = calculateDistance(x, y);
        }
    }

    private double calculateDistance(int x, int y){
        return Math.sqrt(Math.pow(xPos - x, 2) + Math.pow(yPos - y, 2));
    }

    double[] getDistances(){
        return distances;
    }
}
