package org.MOEABased.Problems;

import org.Main.Model.GraphData;
import org.Main.Parser.TSPParser;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.Permutation;
import org.moeaframework.problem.AbstractProblem;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MOTSP extends AbstractProblem {

    private Random rand;
    public static GraphData data;

    public MOTSP () throws FileNotFoundException {
        super(1, 1);
        TSPParser parser = new TSPParser();
        if (data == null) {
            data = parser.Parse("/home/kryo/Desktop/optim/moea-test/src/main/resources/eil51.tsp");
        }
        this.rand = new Random();
    }

    public MOTSP (GraphData data) {
        super(1, 2);

        MOTSP.data = data;

        this.rand = new Random();
    }

    @Override
    public void evaluate(Solution solution) {
        int[] permutation = EncodingUtils.getPermutation(solution.getVariable(0));
        double mse = 0;
        double mae = 0;

        for(int i = 0; i < permutation.length - 1; i++) {
            Double[] coor1 = data.getDataById(permutation[i]); // data.getData().get(permutation[i] + this.min);
            Double[] coor2 = data.getDataById(permutation[i+1]); // data.getData().get(permutation[i+1] + this.min);

            mse += euclideanDistance(coor1, coor2);
            mae += MAEDistance(coor1, coor2);
        }

        solution.setObjective(0, mse);
//        solution.setObjective(1, mae);
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(numberOfVariables, numberOfObjectives);
        List<Integer> keys = IntStream.range(0, data.getSize())
                .boxed().collect(Collectors.toList());

        Permutation var = EncodingUtils.newPermutation(data.getSize());
        for (int i = 0; i < data.getSize(); i++) {
            int id = this.rand.nextInt(keys.size());
            var.insert(i, keys.get(id));
            keys.remove(id);
        }
        solution.setVariable(0, var);

        // for int variable
//        for (int i = 0; i < this.keyList.size(); i++) {
//            int id = this.rand.nextInt(keys.size());
//            RealVariable variable = EncodingUtils.newInt(0, this.max + 1);
//            variable.setValue(keys.get(id));
//            solution.setVariable(i, variable);
//            keys.remove(id);
//        }

        return solution;
    }

    public double euclideanDistance(Double[] p1, Double[] p2) {
        return Math.sqrt(
                Math.pow((p1[0] - p2[0]), 2) +
                Math.pow((p1[1] - p2[1]), 2)
        );
    }

    public double MAEDistance (Double[] p1, Double[] p2) {
        return (
                Math.abs((p1[0] - p2[0])) +
                        Math.abs((p1[1] - p2[1]))
        );
    }

}
