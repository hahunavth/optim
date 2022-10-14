package org.Main.Parser;

import org.Main.Model.GraphData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class TSPParser implements IParser{

    private boolean isParseDataStart = false;

    public GraphData Parse(String fName) throws FileNotFoundException {
        File file = new File(fName);
        Scanner myReader = new Scanner(file);

        GraphData result = new GraphData();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();

            if (line.trim().equals("NODE_COORD_SECTION")) {
                isParseDataStart = true;
                continue;
            } else if(line.trim().equals("EOF")) {
                break;
            }

            if (!isParseDataStart) {
                parseAttribute(line, result);
            } else {
                parseData(line, result);
            }

        }

        myReader.close();

        return result;
    }

    private void parseAttribute(String line, GraphData target) {
        String[] list = line.split(":");
        String key = list[0].trim();
        String value = list[1].trim();
        switch(key) {
            case "NAME":
                target.setName(value);
                break;
            case "COMMENT":
                target.setComment(value);
                break;
            case "TYPE":
                target.setType(value);
                break;
            case "DIMENSION":
                target.setDimension(Integer.parseInt(value));
                break;
            case "EDGE_WEIGHT_TYPE":
                target.setEdge_weight_type(value);
                break;
            case "NODE_COORD_SECTION":
                break;
        }
    }

    private void parseData(String line, GraphData target) {
        double[] items = Arrays.stream(line.trim().split(" ")).flatMapToDouble(
                (s) -> {return DoubleStream.of(Double.parseDouble(s.trim()));}
        ).toArray();
        int id = (int) items[0];

        target.put(id, new Double[] {items[1], items[2]});
    }
}
