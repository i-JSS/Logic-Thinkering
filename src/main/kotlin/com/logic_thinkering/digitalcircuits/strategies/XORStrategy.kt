package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower

class XORStrategy : TwoInputGateStrategy() {
    override fun getOutput(inputPower: InputPower) = inputPower.east xor inputPower.west
}