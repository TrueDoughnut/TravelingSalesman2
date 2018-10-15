package com.cfs.runner;

public class Runner implements Runnable {

    private int[][] positions;
    private int[] distances;
    private int startX, startY;

    public Runner(int startX, int startY){
        this.startX = startX;
        this.startY = startY;
        for(int i = 0; i < positions.length; i++){
            int[] arr = positions[i]; 
        }
    }

    @Override
    public void run() {

    }
}
