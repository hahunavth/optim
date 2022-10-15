package org.Main.Algorithm.Interfaces;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AbstractGA {
    private List<Individual> population;
    private GAParameters parameters;

    private static byte[] solution = new byte[64];

    public AbstractGA (GAParameters parameters) {
        this.parameters = parameters;
    }

    public AbstractGA () {
        this.parameters = new GAParameters();
    }

    public GAParameters getParameters() {
        return parameters;
    }

    public boolean runAlgorithm(int populationSize, String solution) {
        if (solution.length() != AbstractGA.solution.length) {
            throw new RuntimeException("The solution needs to have " + AbstractGA.solution.length + " bytes");
        }
        setSolution(solution);
        Population myPop = new Population(populationSize, true, 64, this);

        int generationCount = 1;
        while (myPop.getFittest().getFitness() < getMaxFitness()) {
            System.out.println("Generation: " + generationCount + " Correct genes found: " + myPop.getFittest().getFitness());
            myPop = evolvePopulation(myPop);
            myPop.calculatePopulationFitness();
            generationCount++;
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes: ");
        System.out.println(myPop.getFittest());
        return true;
    }

    public Population evolvePopulation(Population pop) {
        int elitismOffset;
        Population newPopulation = new Population(pop.getIndividuals().size(), false, 64, this);

        if (parameters.isElitism()) {
            newPopulation.getIndividuals().add(0, pop.getFittest());
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < pop.getIndividuals().size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.getIndividuals().add(i, newIndiv);
        }

        for (int i = elitismOffset; i < newPopulation.getIndividuals().size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private Individual crossover(Individual i1, Individual i2) {
        Individual newSol = new Individual(64);
        IntStream.range(0, newSol.getDefaultGeneLength())
                .forEach(i -> {
                    if(Math.random() < parameters.getUniformRate()) {
                        newSol.setSingleGene(i, i1.getSingleGene(i));
                    } else {
                        newSol.setSingleGene(i, i2.getSingleGene(i));
                    }
                });
        return newSol;
    }

    private void mutate(Individual individual) {
        IntStream.range(0, individual.getDefaultGeneLength())
                .forEach(i -> {
                    if (Math.random() <= parameters.getMutationRate()) {
                        byte gene = (byte) Math.round(Math.random());
                        individual.setSingleGene(i, gene);
                    }
                });
    }

    private Individual tournamentSelection(Population pop) {
        Population tournament = new Population(parameters.getTournamentSize(), false, 64, this);
        IntStream.range(0, parameters.getTournamentSize())
                .forEach(i -> {
                    int randomId = (int) (Math.random() * pop.getIndividuals().size());
                    tournament.getIndividuals().add(i, pop.getIndividual(randomId));
                });
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    protected static int getFitness(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.getDefaultGeneLength() && i < solution.length; i++) {
            if (individual.getSingleGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    protected int getMaxFitness() {
        return solution.length;
    }

    protected void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }
}

@Data
class Population {
    private List<Individual> individuals;
    private int geneLength;
    private AbstractGA ga;
    public Population(int size, boolean createNew, int geneLength, AbstractGA ga) {
        individuals = new ArrayList<>();
        this.geneLength = geneLength;

        if (createNew) {
            createNewPopulation(size);
        }
        this.ga = ga;
        calculatePopulationFitness();
    }

    protected void calculatePopulationFitness () {
        IntStream.range(0, individuals.size())
                .forEach(i -> {
                    this.individuals.get(i).setFitness(
                            this.individuals.get(i).getFitness(this.ga)
                    );
                });
    }

    protected Individual getIndividual(int index) {
        return individuals.get(index);
    }

    protected Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (int i = 0; i < individuals.size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    private void createNewPopulation(int size) {
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual(geneLength);
            individuals.add(i, newIndividual);
        }
    }

}

@Data
class Individual {
    private double fitness = 0;
    private byte[] genes;

    public Individual(int chromosomeLength) {
        genes = new byte[chromosomeLength];
//        for (int i = 0; i < genes.length; i++) {
//            byte gene = (byte) Math.round(Math.random());
//            genes[i] = gene;
//        }
    }

    public int getDefaultGeneLength() {
        return genes.length;
    }

    protected byte getSingleGene(int index) {
        return genes[index];
    }

    protected void setSingleGene(int index, byte value) {
        genes[index] = value;
        fitness = 0;
    }

    public double getFitness(AbstractGA algorithm) {
        if (fitness == 0) {
            fitness = AbstractGA.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < genes.length; i++) {
            geneString += getSingleGene(i);
        }
        return geneString;
    }

//    abstract Number encode(P phenotype);
//
//    abstract P decode(Number[] genotype);
//
//    abstract void mutation ();
}