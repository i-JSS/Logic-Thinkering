package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower

class ANDStrategy : TwoInputGateStrategy() {
    override fun getOutput(inputPower: InputPower) = inputPower.east && inputPower.west
}