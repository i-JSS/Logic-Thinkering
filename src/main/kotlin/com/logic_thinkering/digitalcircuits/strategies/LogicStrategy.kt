package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower
import net.minecraft.util.math.Direction

interface LogicStrategy {
    fun getOutput(inputPower: InputPower): Boolean

    fun getRequiredInputs(): Set<Direction>
}