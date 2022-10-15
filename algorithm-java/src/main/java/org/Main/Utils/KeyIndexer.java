package org.Main.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class KeyIndexer<K> {
    private List<K> id2Key;
    private HashMap<K, Integer> key2Id;

    public KeyIndexer(HashMap<K, ?> data) {
        this.id2Key = new ArrayList<>(data.keySet());
        this.key2Id = new HashMap<>();

        IntStream.range(0, data.size())
                .forEach(i -> {
                    this.key2Id.put(id2Key.get(i), i);
                });
    }

    public KeyIndexer() {
        this.id2Key = new ArrayList<>();
        this.key2Id = new HashMap<>();
    }

    public int getIndex(K key) {
        return key2Id.get(key);
    }

    public K getKey(int id) {
        return id2Key.get(id);
    }

    public int[] getIndexes (K[] keys) {
        int[] result = new int[keys.length];
        IntStream.range(0, keys.length)
                .forEach(i -> {
                    result[i] = key2Id.get(keys[i]);
                });
        return result;
    }

    public K[] getKeys (int[] ids) {
        List<K> keys = new ArrayList<>();
        IntStream.range(0, ids.length)
                .forEach(i -> {
                    keys.add(id2Key.get(ids[i]));
                });
        K[] ks = (K[]) keys.toArray();
        return ks;
    }

    public void put(K k) {
        this.id2Key.add(k);
        if(!this.key2Id.containsKey(k))
            this.key2Id.put(k, this.id2Key.size() - 1);
        else throw new RuntimeException("Duplicate key!");
    }

}
