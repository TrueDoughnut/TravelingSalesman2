package com.cfs.main;

import com.cfs.runner.Runner;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        Runner runner = new Runner(run(), startX, startY);
        runner.run();
        System.out.println(runner);
    }

    private static int startX, startY;
    private static int[][] run(){
        Random random = new Random();
        int[][] matrix = new int[6][2];
        for(int i = 0; i < matrix.length; i++) {
            int[] arr = new int[2];
            arr[0] = random.nextInt(200) - 100;
            arr[1] = random.nextInt(200) - 100;
            matrix[i] = arr;
        }
        startX = matrix[matrix.length/2][0];
        startY = matrix[matrix.length/2][1];
        return matrix;
    }
}
