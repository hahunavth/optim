package org.Main.Utils;

import java.util.Random;
import java.util.stream.IntStream;

public class MatrixUtils {
    private final Random random;
    private static volatile MatrixUtils instance;
    private MatrixUtils () {
        random = new Random();
    }
    public static MatrixUtils getInstance() {
        if(instance == null) {
            synchronized(MatrixUtils.class) {
                if(null == instance) {
                    instance  = new MatrixUtils();
                }
            }
        }
        return instance;
    }

    public double[][] generateRandomMatrix(int n) {
        double[][] randomMatrix = new double[n][n];
        IntStream.range(0, n)
                .forEach(i -> IntStream.range(0, n)
                        .forEach(j -> randomMatrix[i][j] = Math.abs(random.nextInt(100) + 1)));
        return randomMatrix;
    }

    public int randInt(int i) {
        return random.nextInt(i);
    }
    public int randInt() {
        return random.nextInt();
    }

}
