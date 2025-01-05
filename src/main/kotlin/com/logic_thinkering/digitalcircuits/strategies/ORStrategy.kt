package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower

class ORStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = inputPower.east || inputPower.west
}