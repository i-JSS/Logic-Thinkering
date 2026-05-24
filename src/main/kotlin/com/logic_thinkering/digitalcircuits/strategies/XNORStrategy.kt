package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower

class XNORStrategy : TwoInputGateStrategy() {
    override fun getOutput(inputPower: InputPower) = !(inputPower.west xor inputPower.east)
}