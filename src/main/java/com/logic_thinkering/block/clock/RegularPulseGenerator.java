package com.logic_thinkering.block.clock;

public class RegularPulseGenerator implements PulseGenerator {
    private final int interval;

    public RegularPulseGenerator(int interval) {
        this.interval = interval;
    }

    @Override
    public int generatePulse() {
        return interval;
    }
}
