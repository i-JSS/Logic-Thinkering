package com.logicthinkering.digitalcircuits.strategies

import com.logicthinkering.digitalcircuits.InputPower

class XNORStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = !(inputPower.west xor inputPower.east)
}