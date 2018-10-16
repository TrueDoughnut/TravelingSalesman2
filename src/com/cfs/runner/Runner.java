package com.cfs.runner;

public class Runner implements Runnable {

    private int[][] positions;
    private Node[] nodes;
    private boolean[] visited;
    private Node start;

    public Runner(int[][] positions, int startX, int startY){
        this.positions = positions;
        nodes = new Node[positions.length];
        int i = 0;
        for(int[] arr : positions){
            nodes[i] = new Node(positions, arr[0], arr[1]);
            if(arr[0] == startX && arr[1] == startY){
                start = nodes[i];
            }
            i++;
        }
        visited = new boolean[nodes.length];

        if(start == null){
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

    private boolean allVisited(){
        for(boolean x : visited){
            if(!x){
                return false;
            }
        }
        return true;
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
