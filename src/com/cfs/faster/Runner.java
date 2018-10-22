package com.cfs.faster;

import com.cfs.helper.Node;

public class Runner {

    private Node[] nodes;

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
    }
}
