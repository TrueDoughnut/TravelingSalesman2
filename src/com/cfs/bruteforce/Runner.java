package com.cfs.bruteforce;

import com.cfs.helper.Node;
import com.cfs.helper.Route;

import java.util.*;

public class Runner implements Runnable {

    private Node[] nodes;
    private double min = Double.MAX_VALUE;
    private Route best;

    public volatile static HashMap<Route, Double> map;

    public Runner(int[][] positions, int startX, int startY){
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
        map = new HashMap<>();
    }
    public Runner(int[][] positions){
        this(positions, positions[0][0], positions[0][1]);
    }

    Node[] getNodes(){
        return nodes;
    }

    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    @Override
    public void run() {
        Queue<Route> routes = new LinkedList<>();
        for(int i = 1; i < nodes.length; i++){
            Node[] nodes = new Node[this.nodes.length-1];
            System.arraycopy(this.nodes, 1, nodes, 0, nodes.length);
            Permutations<Node> permutations = new Permutations<>(nodes);

            while(permutations.hasNext()){
                Node[] route = new Node[this.nodes.length+1];
                route[0] = this.nodes[0];
                route[route.length-1] = this.nodes[0];
                int j = 1;
                for(Node node : permutations.next()){
                    route[j++] = node;
                }
                routes.add(new Route(route));
            }
        }

        for(Route route : routes){
            map.put(route, route.getTotal());
        }

        for(Route route : map.keySet()){
            if(map.get(route) < min){
                min = map.get(route);
                best = route;
            }
        }
    }

    @Override
    public String toString(){
        return min + "\n" + best;
    }
}
