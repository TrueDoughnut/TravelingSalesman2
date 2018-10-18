package com.cfs.runner;

class Route extends Thread {

    private Node[] route;
    private double total;

    Route(Node[] route){
        this.route = route;
        total = 0;
    }

    @Override
    public void run(){
        for(int i = 0; i < route.length - 1; i++){
            total += route[i].distanceTo(route[i+1]);
        }
    }

    Node[] getRoute(){
        return route;
    }

    double getTotal(){
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
