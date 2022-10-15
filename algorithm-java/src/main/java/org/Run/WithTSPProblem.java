package org.Run;
import org.MOEABased.Problems.MOTSP;
import org.moeaframework.Executor;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;

public class WithTSPProblem {

    public static void main(String[] args) {

//        Instrumenter instrumenter = new Instrumenter()
//                .withProblem("UF1")
//                .withFrequency(100)
//                .attachHypervolumeCollector()
//
//        Analyzer analyzer = new Analyzer()
//                .withProblemClass(MOTSP.class)
////                .includeAllMetrics()
//                .includeHypervolume()
//                .includeGenerationalDistance()
//                .showStatisticalSignificance();

        Executor executor = new Executor()
                .withProblemClass(MOTSP.class)
                .withAlgorithm("NSGAII")
//                .withProperty("populationSize", 500)
                .withMaxEvaluations(1000000)
//                .withInstrumenter(instrumenter)
                .distributeOnAllCores()
                ;

        NondominatedPopulation result = executor.run();

//        Accumulator accumulator = instrumenter.getLastAccumulator();
        System.out.println("Result: ");
        for(Solution solution : result) {
            System.out.println("r = " + solution.getVariable(0)
            );
        }
        System.out.println("Result (objective function): ");
        for (Solution solution : result) {
            System.out.println(solution.getObjective(0) + " "
//                    + solution.getObjective(1)
            );
        }
//        System.out.println("Instrucmentation: ");
//        for (int i=0; i<accumulator.size("NFE"); i++) {
//            System.out.println(accumulator.get("NFE", i) + "\t" +
//                    accumulator.get("Hypervolume", i));
//        }

        new Plot()
                .add("NSGA-II", result)
//                .setXLabel("S = pi*r*s")
//                .setYLabel("T = pi*r*(r+s)")
                .show();

    }
}