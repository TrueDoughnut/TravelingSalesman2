package com.cfs.runner;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

public class Runner implements Runnable {

    private int[][] positions;
    private Node[] nodes;
    private boolean[] visited;

    public Runner(int[][] positions, int startX, int startY){
        this.positions = positions;
        nodes = new Node[positions.length];
        int i = 0;
        for(int[] arr : positions){
            nodes[i] = new Node(arr[0], arr[1]);
            if(arr[0] == startX && arr[1] == startY){
                Node temp = nodes[0];
                nodes[0] = nodes[i];
                nodes[i] = temp;
            }
            i++;
        }
        visited = new boolean[nodes.length];
    }
    public Runner(int[][] positions){
        this(positions, positions[0][0], positions[0][1]);
    }

    Node[] getNodes(){
        return nodes;
    }
    boolean[] getVisited(){
        return visited;
    }

    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    @Override
    public void run() {
        Queue<Route> routes = new SynchronousQueue<>();
        for(int i = 1; i < nodes.length; i++){

            Node[] nodes = new Node[this.nodes.length-1];
            System.arraycopy(this.nodes, 1, nodes, 0, nodes.length);

            Permutations<Node> permutations = new Permutations<>(nodes);
            while(permutations.hasNext()){
                Node[] route = new Node[nodes.length+1];
                route[0] = this.nodes[0];
                route[route.length-1] = this.nodes[0];
                int j = 1;
                for(Node node : permutations.next()){
                    route[j++] = node;
                }
                routes.add(new Route(route));
            }
        }

        ArrayList<Double> vals = new ArrayList<>();
        Route[] threads = new Route[20];
        for(Thread thread : threads){
            thread = routes.remove();
            thread.start();
        }

        while(!routes.isEmpty()){
            for(Route route : threads){
                if(!route.isAlive()){
                    vals.add(route.getTotal());
                    route = routes.remove();
                    route.start();
                }
            }
        }

        double max = 0;
        for(Double x : vals){
            if(x > max){
                max = x;
            }
        }

        System.out.println(max);
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
