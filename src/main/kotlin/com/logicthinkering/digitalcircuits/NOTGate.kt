package com.logicthinkering.digitalcircuits

import com.logicthinkering.digitalcircuits.strategies.NOTStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val NOT_CODEC = AbstractBlock.createCodec(::NANDGate)

class NOTGate(settings: Settings) : AbstractLogicGate(settings, NOTStrategy()) {
    override fun getCodec(): MapCodec<NANDGate> = NOT_CODEC
}