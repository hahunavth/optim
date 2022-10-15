package org.Main.Algorithm;

import org.Main.Algorithm.Interfaces.AbstractGA;
import org.testng.annotations.Test;

public class GATest {
    @Test
    public void simpleGATest () {
        AbstractGA ga = new AbstractGA();
        ga.getParameters()
                .setTournamentSize(3)
//                .setUniformRate(0.2)
//                .setElitism(false)
//                .setMutationRate(0.005)
        ;
        ga.runAlgorithm(10, "1011000100000100010000100000100111001000000100000100000000001111");
    }
}
