package org.example.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class GraphData extends AbstractData<HashMap<Integer, Double[]>> {
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

}
