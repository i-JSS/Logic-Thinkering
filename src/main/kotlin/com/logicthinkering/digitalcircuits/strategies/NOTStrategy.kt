package com.logicthinkering.digitalcircuits.strategies

import com.logicthinkering.digitalcircuits.InputPower

class NOTStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = !inputPower.south
}