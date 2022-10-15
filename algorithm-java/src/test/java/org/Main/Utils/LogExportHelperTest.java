package org.Main.Utils;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LogExportHelperTest {
    LogExportHelper helper = new LogExportHelper();

    public LogExportHelperTest() {

    }

    @Test
    public void testAddAttribute () {
        helper.setAttribute(1, 2);
        helper.setAttribute(1, new Double[] {1d, 2d});
        helper.setAttribute("a", "b");
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(1);
        helper.setAttribute(3, l);
        helper.addValue("fadg");
        helper.addValue(3);
        helper.addValue(new Double[] {1d, 5d});
        System.out.println(helper);
    }
}