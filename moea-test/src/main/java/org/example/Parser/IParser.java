package org.example.Parser;

import org.example.Model.AbstractData;
import org.example.Model.GraphData;

import java.io.FileNotFoundException;

public interface IParser {
    AbstractData Parse(String fName) throws FileNotFoundException;
}
