package com.cfs.helper;

import com.cfs.bruteforce.Runner;

public class Route extends Thread {

    private Node[] route;
    private double total;

    public Route(Node[] route){
        this.route = route;
        total = 0;
    }

    @Override
    public void run(){
        total = calcTotal();
        Runner.map.put(this, total);
    }

    Node[] getRoute(){
        return route;
    }

    public double getTotal(){
        return calcTotal();
    }

    private double calcTotal(){
        double total = 0.0;
        for(int i = 0; i < route.length - 1; i++){
            total += route[i].distanceTo(route[i+1]);
        }
        return total;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Node node : route){
            builder.append(node.toString());
        }
        return builder.toString();
    }
}
