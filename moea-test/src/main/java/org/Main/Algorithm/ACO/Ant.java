package org.Main.Algorithm.ACO;

import org.Main.Model.IGraphData;

class Ant {
    public int[] trail;
    private boolean[] visited;
    private int trailSize;

    public Ant(int dataSize) {
        this.trail = new int[dataSize];
        this.visited = new boolean[dataSize];
        this.trailSize = dataSize;
    }

    public void visitCity(int currentIndex, int city) {
        trail[currentIndex + 1] = city;
        visited[city] = true;
    }

    public boolean visited(int i) {
        return visited[i];
    }

    public double trailLength(IGraphData graph) {
        double length = graph.getDistance(trail[trailSize - 1], trail[0]); // graph[trail[trailSize - 1]][trail[0]];
        for (int i = 0; i < trailSize - 1; i++) {
            length += graph.getDistance(trail[i], trail[i+1]); // graph[trail[i]][trail[i + 1]];
        }
        return length;
    }

    protected void clear() {
        for (int i = 0; i < trailSize; i++)
            visited[i] = false;
    }
}