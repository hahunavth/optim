package org.Main.Model;

import java.util.HashMap;

public class GraphData extends AbstractData<Integer, Double[]> implements IGraphData {
    private String name;
    private String comment;
    private String type;
    private int dimension;
    private String edge_weight_type;

    public GraphData() {
        super(new HashMap<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public String getEdge_weight_type() {
        return edge_weight_type;
    }

    public void setEdge_weight_type(String edge_weight_type) {
        this.edge_weight_type = edge_weight_type;
    }

    @Override
    public int getSize() {
        return this.getData().size();
    }

    @Override
    public double getDistance(int fromId, int toId) {
        Double[] a = this.getData().get(this.getKeyIndexer().getKey(fromId));
        Double[] b = this.getData().get(this.getKeyIndexer().getKey(toId));

        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}
