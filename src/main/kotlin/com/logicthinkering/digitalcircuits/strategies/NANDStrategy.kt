package com.logicthinkering.digitalcircuits.strategies

import com.logicthinkering.digitalcircuits.InputPower

class NANDStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = !(inputPower.east && inputPower.west)
}