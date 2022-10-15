package org.Main.Algorithm.Interfaces;

class GAParameters {
    private double uniformRate = 0.5;
    private double mutationRate = 0.025;
    private int tournamentSize = 5;
    private boolean elitism = true;

    public double getUniformRate() {
        return uniformRate;
    }

    public GAParameters setUniformRate(double uniformRate) {
        this.uniformRate = uniformRate;
        return this;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public GAParameters setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
        return this;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public GAParameters setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
        return this;
    }

    public boolean isElitism() {
        return elitism;
    }

    public GAParameters setElitism(boolean elitism) {
        this.elitism = elitism;
        return this;
    }
}
