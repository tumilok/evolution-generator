package com.tumilok.test;

import com.tumilok.main.ReadParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadParametersTest {

    @Test
    void testReadParameters() {
        ReadParameters read = new ReadParameters();

        Assertions.assertEquals(20, read.getWidth());
        Assertions.assertEquals(20, read.getHeight());
        Assertions.assertEquals(500, read.getStartEnergy());
        Assertions.assertEquals(2, read.getMoveEnergy());
        Assertions.assertEquals(10, read.getPlantEnergy());
        Assertions.assertEquals(0.3, read.getJungleRatio());
    }
}
