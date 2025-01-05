package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower

class NOTStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = !inputPower.south
}