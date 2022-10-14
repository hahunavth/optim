package org.Main.Algorithm.ACO;

import org.Main.Model.IGraphData;
import org.Main.Utils.MatrixUtils;

import java.util.*;
import java.util.stream.IntStream;

public class ACO {

    private final ACOParameters param;

    private int maxIterations = 1000;

    private int numberOfCities;
    private int numberOfAnts;
//    private double[][] graph;
    private IGraphData graph;
    private double[][] trails;
    private final List<Ant> ants = new ArrayList<>();
    private final Random random = new Random();

    private double[] probabilities;
    private int currentIndex;
    private int[] bestTourOrder;
    private double bestTourLength;

    public ACO(IGraphData data) {
        this.param = new ACOParameters();
        this.graph = data;
        numberOfCities = graph.getSize();

        numberOfAnts = (int) (numberOfCities * param.getAntFactor());

        trails = new double[numberOfCities][numberOfCities];
        probabilities = new double[numberOfCities];
        IntStream.range(0, numberOfAnts)
                .forEach(i -> ants.add(new Ant(numberOfCities)));
    }

    public void run() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    solve();
                });
    }

    public void run(boolean showProcess) {
        if(showProcess) {
            IntStream.rangeClosed(1, 300)
                    .forEach(i -> {
                        System.out.println("Attempt #" + i);
                        solve();
                        System.out.println("Best tour length: " + (bestTourLength));
                        System.out.println("Best tour order: " + Arrays.toString(bestTourOrder));
                    });
        } else {
            run();
        }
    }

    public int[] getBestTourOrder() {
        return bestTourOrder;
    }

    public double getBestTourLength() {
        return bestTourLength;
    }

    public int[] solve() {
        setupAnts();
        clearTrails();
        IntStream.range(0, maxIterations)
                .forEach(i -> {
                    moveAnts();
                    updateTrails();
                    updateBest();
                });
        return bestTourOrder.clone();
    }

    private void setupAnts () {
        IntStream.range(0, numberOfAnts).forEach(
                i -> {
                    ants.forEach(
                            ant -> {
                                ant.clear();
                                ant.visitCity(-1, MatrixUtils.getInstance().randInt(numberOfCities));
                            }
                    );
                }
        );
        currentIndex = 0;
    }

    private void moveAnts() {
        IntStream.range(currentIndex, numberOfCities - 1)
                .forEach(i -> {
                    ants.forEach(ant-> ant.visitCity(currentIndex, selectNextCity(ant)));
                    currentIndex++;
                });
    }

    private int selectNextCity (Ant ant) {
        int t = random.nextInt(numberOfCities - currentIndex);
        if (random.nextDouble() < param.getRandomFactor()) {
            OptionalInt cityIndex = IntStream.range(0, numberOfCities)
                    .filter(i -> i == t && !ant.visited(i))
                    .findFirst();
            if (cityIndex.isPresent()) {
                return cityIndex.getAsInt();
            }
        }
        calculateProbabilities(ant);
        double r = random.nextDouble();
        double total = 0;
        for (int i = 0; i < numberOfCities; i++) {
            total += probabilities[i];
            if (total >= r) {
                return i;
            }
        }

        throw new RuntimeException("There are no other city!" + " t " + t + " r " + r + " total " + total);
    }

    public void calculateProbabilities (Ant ant) {
        int i = ant.trail[currentIndex];
        double pheromone = 0.0;
        for (int k = 0; k < numberOfCities; k++) {
            if (!ant.visited(k)) {
                // i -> k
                // trail: di qua
                // graph: distance
                pheromone += calculateProbability(i, k);
            }
        }
        for (int j = 0; j < numberOfCities; j++) {
            if(ant.visited(j)) {
                probabilities[j] = 0;
            } else {
                double numerator = calculateProbability(i, j);
                probabilities[j] = numerator / pheromone;
            }
        }
    }

    public double calculateProbability(int from, int to) {
        return Math.pow(trails[from][to], param.getAlpha()) * Math.pow(1.0 / graph.getDistance(from, to), param.getBeta());
    }

    private void updateTrails() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                trails[i][j] *= param.getEvaporation();
            }
        }
        for(Ant a : ants) {
            double contribution = param.getQ() / a.trailLength(graph);
            for(int i = 0; i < numberOfCities - 1; i++) {
                trails[a.trail[i]][a.trail[i+1]] = contribution;
            }
            trails[a.trail[numberOfCities - 1]][a.trail[0]] += contribution;
        }
    }

    private void updateBest() {
        int antId = 0;
        if (bestTourOrder == null) {
            bestTourLength = ants.get(0).trailLength(graph);
        }
        for (int i = 1; i < ants.size(); i++) {
            Ant a = ants.get(i);
            if (a.trailLength(graph) < bestTourLength) {
                bestTourLength = a.trailLength(graph);
                antId = i;
            }
        }
        bestTourOrder = ants.get(antId).trail.clone();
    }

    private void clearTrails() {
        IntStream.range(0, numberOfCities)
                .forEach(i -> {
                    IntStream.range(0, numberOfCities)
                            .forEach(j -> trails[i][j] = param.getC());
                });
    }

    public ACOParameters getParam() {
        return param;
    }
}
