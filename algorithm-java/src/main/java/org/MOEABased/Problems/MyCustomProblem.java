package org.MOEABased.Problems;
import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;
public class MyCustomProblem extends AbstractProblem  {
    public MyCustomProblem () {
        super(2, 2, 1);
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(numberOfVariables, numberOfObjectives, numberOfConstraints);
        // decision variables:
        // - r = [0, 10]
        // - h = [0, 20]
        solution.setVariable(0, new RealVariable(0, 10.0));
        solution.setVariable(1, new RealVariable(0, 20.0));

        return solution;
    }

    @Override
    public int getNumberOfVariables() {
        return 1;
    }

    @Override
    public void evaluate(Solution solution) {
        double[] x = EncodingUtils.getReal(solution);
        double r = x[0], h = x[1];

        double s = Math.sqrt(r*r + h*h);
        double B = Math.PI * r * r;
        double V = Math.PI * r * r * h / 3;

        double S = Math.PI * r * s;
        double T = B + S;
        double constraintV = V - 200; // v <= 200

        solution.setObjective(0, S);
        solution.setObjective(1, T);
        solution.setConstraint(0, (double) Math.round(constraintV));
    }
}
