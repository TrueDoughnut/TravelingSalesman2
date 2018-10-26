package com.cfs.main;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        int[][] matrix = run();
        com.cfs.geometric.Runner runner1 = new com.cfs.geometric.Runner(matrix);
        runner1.run();
        System.out.println(runner1);
        com.cfs.bruteforce.Runner runner2 = new com.cfs.bruteforce.Runner(matrix);
        runner2.run();
        System.out.println(runner2);
    }

    private static int[][] run(){
        Random random = new Random();
        int[][] matrix = new int[6][2];
        for(int i = 0; i < matrix.length; i++) {
            int[] arr = new int[2];
            arr[0] = random.nextInt(200) - 100;
            arr[1] = random.nextInt(200) - 100;
            matrix[i] = arr;
        }
        return matrix;
    }
}
