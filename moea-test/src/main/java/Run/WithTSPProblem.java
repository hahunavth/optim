
package org.example;
import org.example.Problems.Kursawe;
import org.example.Problems.MOTSP;
import org.example.Problems.MyCustomProblem;
import org.moeaframework.Analyzer;
import org.moeaframework.Executor;
import org.moeaframework.Instrumenter;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.analysis.collector.Accumulator;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.util.ReferenceSetMerger;


public class WithTSPProblem {

    public static void main(String[] args) {

        Instrumenter instrumenter = new Instrumenter()
                .withProblem("UF1")
                .withFrequency(100)
                .attachAll();

        Analyzer analyzer = new Analyzer()
                .withProblemClass(MOTSP.class)
//                .includeAllMetrics()
                .includeHypervolume()
                .includeGenerationalDistance()
                .showStatisticalSignificance();

        Executor executor = new Executor()
//                .withProblem("UF1")
//                .withProblemClass(Kursawe.class)
                .withProblemClass(MOTSP.class)
                .withAlgorithm("NSGAII")
                .withMaxEvaluations(100000)
                .withInstrumenter(instrumenter)
                .distributeOnAllCores();

        NondominatedPopulation result = executor.run();

        Accumulator accumulator = instrumenter.getLastAccumulator();
        System.out.println("Result: ");
        for(Solution solution : result) {
            System.out.println("r = " + solution.getVariable(0)
            );
        }
        System.out.println("Result (objective function): ");
        for (Solution solution : result) {
            System.out.println(solution.getObjective(0) + " " + solution.getObjective(1));
        }
        System.out.println("Instrucmentation: ");
        for (int i=0; i<accumulator.size("NFE"); i++) {
            System.out.println(accumulator.get("NFE", i) + "\t" +
                    accumulator.get("GenerationalDistance", i));
        }
//        System.out.println("Analytic:");
//        analyzer.printAnalysis();

        new Plot()
                //   .add("GDE3", GDE3result)
                .add("NSGA-II", result)
                .setXLabel("S = pi*r*s")
                .setYLabel("T = pi*r*(r+s)")
                .show();

    }
}