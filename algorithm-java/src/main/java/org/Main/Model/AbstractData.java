package org.Main.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Main.Utils.KeyIndexer;

import java.util.*;

public abstract class AbstractData<K, V> {
    private final HashMap<K, V> data;
    private final KeyIndexer<K> keyIndexer;
    ObjectMapper mapper = new ObjectMapper();

    public AbstractData(HashMap<K, V> data) {
        this.data = data;
        keyIndexer = new KeyIndexer<>(data);
    }

    public V getDataByKey(K key) {
        return data.get(key);
    }

    public V getDataById(int id) {
        return data.get(keyIndexer.getKey(id));
    }

    public void put(K k, V v) {
        this.data.put(k, v);
        keyIndexer.put(k);
    }

    public KeyIndexer<K> getKeyIndexer() {
        return keyIndexer;
    }

    public HashMap<K, V> getData() {
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