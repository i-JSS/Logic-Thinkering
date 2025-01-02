package com.logicthinkering.digitalcircuits.strategies

import com.logicthinkering.digitalcircuits.InputPower

interface LogicStrategy {
    fun getOutput(inputPower: InputPower): Boolean
}