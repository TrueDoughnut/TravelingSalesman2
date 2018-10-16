package com.cfs.main;

import com.cfs.runner.Runner;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        Runner runner = new Runner(run(), startX, startY);
        System.out.println(runner);
    }

    private static int startX, startY;
    private static int[][] run(){
        Random random = new Random();
        int[][] matrix = new int[30][2];
        for(int i = 0; i < matrix.length; i++) {
            int[] arr = new int[2];
            arr[0] = random.nextInt(200) - 100;
            arr[1] = random.nextInt(200) - 100;
            matrix[i] = arr;
        }
        startX = matrix[15][0];
        startY = matrix[15][1];
        return matrix;
    }
}
