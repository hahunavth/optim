package org.MOEABased.Problems;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;

public class Kursawe extends AbstractProblem {

    public Kursawe() {
        super(3, 2);
    }

    @Override
    public void evaluate(Solution solution) {
        double[] x = EncodingUtils.getReal(solution);
        double f1 = 0.0, f2 = 0.0;

        for(int i = 0; i < numberOfVariables - 1; i++) {
            f1 += -10.0 * Math.exp(
                    -0.2 * Math.sqrt(
                            Math.pow(x[i], 2.0) + Math.pow(x[i+1], 2.0)
                    )
            );
        }

        for (int i = 0; i < numberOfVariables; i++) {
            f2 += Math.pow(
                    Math.abs(x[i]), 0.8) +
                            5.0*Math.sin(Math.pow(x[i], 3.0)
            );
        }

        solution.setObjective(0, f1);
        solution.setObjective(1, f2);
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(numberOfVariables, numberOfObjectives);

        for (int i = 0; i< numberOfVariables; i++) {
            solution.setVariable(i, new RealVariable(-5.0, 5.0));
        }
        return solution;
    }
}
