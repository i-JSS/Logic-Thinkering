package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower
import net.minecraft.util.math.Direction

class NOTStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = !inputPower.south
    override fun getRequiredInputs(): Set<Direction> = setOf(Direction.SOUTH)
}