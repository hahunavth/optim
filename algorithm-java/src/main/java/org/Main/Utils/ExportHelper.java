package org.Main.Utils;

import java.util.Arrays;
import java.util.List;

public class ExportHelper {
    private String key;
    private String value;

    private void setValue(String s) {
        this.value = s.replace("[", "").replace("]", "").replace(",", "");
    }

    public void setAttribute (String key, String value) {
        this.key = key;
        setValue(value);
    }

    public <N, M extends Number> void setAttribute(N key, M value) {
        setAttribute(String.valueOf(key), String.valueOf(value));
    }
    public <N, M extends Number> void setAttribute(N key, M[] values) {
        setAttribute(String.valueOf(key), Arrays.toString(values));
    }

    public <N extends Number, M extends List<?>> void setAttribute(N key, M value) {
        setAttribute(String.valueOf(key), value.toString());
    }


    public void addValue (String value) {
        this.key = null;
        setValue(String.valueOf(value));
    }
    public <M extends Number> void addValue (M value) {
        this.key = null;
        setValue(String.valueOf(value));
    }

    public <M extends Number> void addValue (M[] values) {
        this.key = null;
        setValue(Arrays.toString(values));
    }

    public <M extends List<?>> void addValue (M values) {
        this.key = null;
        setValue(value.toString());
    }

    protected String getLine(String k, String v) {
        if (k == null) {
            return v + "\n";
        } else {
            return k + " " + v + "\n";
        }
    }

    public String getLine() {
        return getLine(key, value);
    }

    protected String getKey() {
        return key;
    }

    protected String getValue() {
        return value;
    }
}
