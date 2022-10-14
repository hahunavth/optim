package Run;

import org.MOEABased.Problems.MyCustomProblem;
import org.moeaframework.Analyzer;
import org.moeaframework.Executor;
import org.moeaframework.Instrumenter;
import org.moeaframework.analysis.collector.Accumulator;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;


public class WithCustomProblem {

    public static void main(String[] args) {

        Instrumenter instrumenter = new Instrumenter()
                .withProblem("UF1")
                .withFrequency(100)
                .attachAll();

        Analyzer analyzer = new Analyzer()
                .withProblemClass(MyCustomProblem.class)
//                .includeAllMetrics()
                .includeHypervolume()
                .includeGenerationalDistance()
                .showStatisticalSignificance();

        Executor executor = new Executor()
//                .withProblem("UF1")
//                .withProblemClass(Kursawe.class)
                .withProblemClass(MyCustomProblem.class)
                .withAlgorithm("NSGAII")
                .withMaxEvaluations(10000)
                .withInstrumenter(instrumenter)
                .distributeOnAllCores();

//        analyzer.addAll("NSGAII",
//                executor.withAlgorithm("NSGAII").runSeeds(50));
//        analyzer.addAll("GDE3",
//                executor.withAlgorithm("GDE3").runSeeds(50));

        NondominatedPopulation result = executor.run();
        NondominatedPopulation GDE3result = executor.withAlgorithm("GDE3").run();

        Accumulator accumulator = instrumenter.getLastAccumulator();
        System.out.println("Result: ");
        for(Solution solution : result) {
            System.out.println("r = " + solution.getVariable(0) + "; s = " + solution.getVariable(1));
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
                .add("GDE3", GDE3result)
                .add("NSGA-II", result)
                .setXLabel("S = pi*r*s")
                .setYLabel("T = pi*r*(r+s)")
                .show();

    }
}