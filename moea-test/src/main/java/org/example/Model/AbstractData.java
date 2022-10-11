package org.example.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.moeaframework.util.tree.Abs;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class AbstractData<T> {
    protected T data;
    ObjectMapper mapper = new ObjectMapper();

    public AbstractData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString () {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
