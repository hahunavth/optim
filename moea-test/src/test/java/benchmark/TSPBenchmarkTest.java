package benchmark;

import org.MOEABased.Problems.MOTSP;
import org.Main.Algorithm.ACO.ACO;
import org.Main.Model.GraphData;
import org.Main.Parser.TSPParser;

import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.IntStream;

public class TSPBenchmarkTest {
    TSPParser parser = new TSPParser();
    GraphData data;

    public TSPBenchmarkTest() throws FileNotFoundException {
        data = parser.Parse("/home/kryo/Desktop/optim/moea-test/src/main/resources/eil51.tsp");
    }

    @Test
    public void testACO () throws FileNotFoundException {
        System.out.println("ACO");

        ACO aco = new ACO(data);
        aco.run();
        int[] path = aco.getBestTourOrder();
        System.out.println(Arrays.toString(path));
        System.out.println(aco.getBestTourLength());
    }

    @Test
    public void testNSGAII() {
        System.out.println("GA");
        MOTSP.data = data;
        Executor executor = new Executor()
                .withProblemClass(MOTSP.class)
                .withAlgorithm("NSGAII")
                .withMaxEvaluations(100000)
                .distributeOnAllCores();
        NondominatedPopulation result = executor.run();
        for(Solution solution : result) {
            System.out.println("r = " + solution.getVariable(0)
            );
        }
        System.out.println("Result (objective function): ");
        for (Solution solution : result) {
            System.out.println(solution.getObjective(0) + " "
            );
        }
    }

}