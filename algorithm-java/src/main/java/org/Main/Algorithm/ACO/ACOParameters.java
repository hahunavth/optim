package org.Main.Algorithm.ACO;


public class ACOParameters {
    private double c = 1.0;
    private double alpha = 1;
    private double beta = 5;
    private double evaporation = 0.5;
    private double Q = 500;
    private double antFactor = 0.8;
    private double randomFactor = 0.01;

    public double getC() {
        return c;
    }

    public ACOParameters setC(double c) {
        this.c = c;
        return this;
    }

    public double getAlpha() {
        return alpha;
    }

    public ACOParameters setAlpha(double alpha) {
        this.alpha = alpha;
        return this;
    }

    public double getBeta() {
        return beta;
    }

    public ACOParameters setBeta(double beta) {
        this.beta = beta;
        return this;
    }

    public double getEvaporation() {
        return evaporation;
    }

    public ACOParameters setEvaporation(double evaporation) {
        this.evaporation = evaporation;
        return this;
    }

    public double getQ() {
        return Q;
    }

    public ACOParameters setQ(double q) {
        Q = q;
        return this;
    }

    public double getAntFactor() {
        return antFactor;
    }

    public ACOParameters setAntFactor(double antFactor) {
        this.antFactor = antFactor;
        return this;
    }

    public double getRandomFactor() {
        return randomFactor;
    }

    public ACOParameters setRandomFactor(double randomFactor) {
        this.randomFactor = randomFactor;
        return this;
    }
}
