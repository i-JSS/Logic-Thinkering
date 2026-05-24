package com.logic_thinkering.block.clock;

public class RegularClockEnergy extends ClockEnergy {

    public RegularClockEnergy(Settings settings) {
        super(settings);
    }

    @Override
    protected PulseGenerator createPulseGenerator() {
        return new RegularPulseGenerator(20);
    }
}
