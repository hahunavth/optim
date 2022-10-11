package Run;

import org.example.Model.GraphData;
import org.example.Parser.TSPParser;
import org.example.Problems.MOTSP;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.Permutation;

import java.util.Set;
import java.util.stream.Collectors;

public class Test {
    public static void main (String[] args) {
        try {
            TSPParser parser = new TSPParser();
            GraphData data = parser.Parse("/home/kryo/Desktop/optim/moea-test/src/main/resources/eil51.tsp");

//            System.out.println();

            MOTSP problem = new MOTSP(data);

            Solution solution = (problem.newSolution());
            problem.evaluate(solution);

            System.out.println(solution.getObjective(0));
            System.out.println(solution.getObjective(1));
//            for (int i = 0; i < solution.getNumberOfVariables(); i++) {
//                System.out.println();
//            }
//            int[] permutation = EncodingUtils.getPermutation(solution.getVariable(0));
//
//            for (int i = 0; i < permutation.length; i++) {
//                System.out.println(permutation[i]);
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
