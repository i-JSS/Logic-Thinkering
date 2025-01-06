package com.logic_thinkering.digitalcircuits.strategies

import net.minecraft.util.math.Direction

abstract class TwoInputGateStrategy : LogicStrategy {
    override fun getRequiredInputs(): Set<Direction> = setOf(Direction.EAST, Direction.WEST)
}