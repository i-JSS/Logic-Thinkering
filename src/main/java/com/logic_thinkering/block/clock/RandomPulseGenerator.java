package com.logic_thinkering.block.clock;

import java.util.Random;

public class RandomPulseGenerator implements PulseGenerator{
    private final Random random = new Random();

    @Override
    public int generatePulse() {
        return 20 + random.nextInt(100);
    }
}
