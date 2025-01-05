package com.logic_thinkering.digitalcircuits.strategies

import com.logic_thinkering.digitalcircuits.InputPower

interface LogicStrategy {
    fun getOutput(inputPower: InputPower): Boolean
}