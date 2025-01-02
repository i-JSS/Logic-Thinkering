package com.logicthinkering.digitalcircuits.strategies

import com.logicthinkering.digitalcircuits.InputPower

class XORStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = inputPower.east xor inputPower.west
}