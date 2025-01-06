package com.logic_thinkering.block.clock;

public class RandomClockEnergy extends ClockEnergy {

    public RandomClockEnergy(Settings settings) {
        super(settings);
    }

    @Override
    protected PulseGenerator createPulseGenerator() {
        return new RandomPulseGenerator();
    }
}
