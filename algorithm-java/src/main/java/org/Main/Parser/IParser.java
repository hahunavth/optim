package org.Main.Parser;

import org.Main.Model.AbstractData;

import java.io.FileNotFoundException;
public interface IParser {
    AbstractData Parse(String fName) throws FileNotFoundException;
}
