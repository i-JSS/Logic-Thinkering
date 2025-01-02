package com.logicthinkering.digitalcircuits.strategies

import com.logicthinkering.digitalcircuits.InputPower
import com.logicthinkering.digitalcircuits.strategies.LogicStrategy

class ANDStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = inputPower.east && inputPower.west
}