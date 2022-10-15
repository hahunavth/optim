package org.Main.Utils;

import java.util.*;
import java.util.stream.IntStream;

public class LogExportHelper extends ExportHelper {
    private final List<String> keys;
    private final List<String> values;

    public LogExportHelper() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public void update() {
        keys.add(super.getKey());
        values.add(super.getValue());
    }

    @Override
    public void setAttribute(String key, String value) {
        super.setAttribute(key, value);
        update();
    }

    @Override
    public <N, M extends Number> void setAttribute(N key, M value) {
        super.setAttribute(key, value);
    }

    @Override
    public <N, M extends Number> void setAttribute(N key, M[] values) {
        super.setAttribute(key, values);
    }

    @Override
    public <N extends Number, M extends List<?>> void setAttribute(N key, M value) {
        super.setAttribute(key, value);
    }

    @Override
    public void addValue(String value) {
        super.addValue(value);
        update();
    }

    @Override
    public <M extends Number> void addValue(M value) {
        super.addValue(value);
        update();
    }

    @Override
    public <M extends Number> void addValue(M[] values) {
        super.addValue(values);
        update();
    }

    @Override
    public <M extends List<?>> void addValue(M values) {
        super.addValue(values);
        update();
    }

    protected String getLine(int i) {
        return super.getLine(keys.get(i), values.get(i));
    }

    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        IntStream.range(0, keys.size())
                .forEach(i -> {
                    out.append(getLine(i));
                });
        return out.toString();
    }
}
