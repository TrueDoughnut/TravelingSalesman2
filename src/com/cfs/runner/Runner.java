package com.cfs.runner;

public class Runner implements Runnable {

    private int[][] positions;
    private Node[] nodes;
    private boolean[] visited;
    private int startX, startY;

    public Runner(int[][] positions, int startX, int startY){
        this.positions = positions;
        nodes = new Node[positions.length];
        int i = 0;
        for(int[] arr : positions){
            nodes[i++] = new Node(positions, arr[0], arr[1]);
        }
        visited = new boolean[nodes.length];
        this.startX = startX;
        this.startY = startY;

        boolean exists = false;
        for(int[] arr : positions){
            if(arr[0] == startX && arr[1] == startY){
                exists = true;
                break;
            }
        }
        if(!exists){
            System.err.println("Invalid input");
            System.exit(15);
        }
    }

    Node[] getNodes(){
        return nodes;
    }
    boolean[] getVisited(){
        return visited;
    }



    @Override
    public void run() {

    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int[] arr : positions){
            for(int x : arr){
                str.append(x).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}
