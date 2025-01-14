package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower

class NANDStrategy : LogicStrategy {
    override fun getOutput(inputPower: InputPower) = !(inputPower.east && inputPower.west)
}