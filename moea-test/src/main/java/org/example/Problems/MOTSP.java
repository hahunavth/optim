package org.example.Problems;

import jmetal.encodings.variable.Int;
import org.example.Model.GraphData;
import org.example.Parser.TSPParser;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.Permutation;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MOTSP extends AbstractProblem {

    private final GraphData data;
    private final List<Integer> keyList;
    private Random rand;
    private int max, min;

    public MOTSP () throws FileNotFoundException {
        super(1, 1);
        TSPParser parser = new TSPParser();

        this.data = parser.Parse("/home/kryo/Desktop/optim/moea-test/src/main/resources/vm1084.tsp");

        this.keyList = data.getData().keySet().stream().sorted().collect(Collectors.toList());
        this.max = Collections.max(this.keyList);
        this.min = Collections.min(this.keyList);

        this.rand = new Random();
    }

    public MOTSP (GraphData data) {
        super(1, 2);

        this.data = data;
        this.keyList = data.getData().keySet().stream().sorted().collect(Collectors.toList());
        this.max = Collections.max(this.keyList);
        this.min = Collections.min(this.keyList);

        this.rand = new Random();
    }

    @Override
    public void evaluate(Solution solution) {
        int[] permutation = EncodingUtils.getPermutation(solution.getVariable(0));
        double mse = 0;
        double mae = 0;

        for(int i = 0; i < permutation.length - 1; i++) {
            Double[] coor1 = data.getData().get(permutation[i] + this.min);
            Double[] coor2 = data.getData().get(permutation[i+1] + this.min);

            mse += euclideanDistance(coor1, coor2);
            mae += MAEDistance(coor1, coor2);
        }

        solution.setObjective(0, mse);
//        solution.setObjective(1, mae);
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(numberOfVariables, numberOfObjectives);
        List<Integer> keys = new ArrayList<>(this.keyList);

        Permutation var = EncodingUtils.newPermutation(this.keyList.size());
        for (int i = 0; i < this.keyList.size(); i++) {
            int id = this.rand.nextInt(keys.size());
            var.insert(i, keys.get(id) - this.min);
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
