package com.logic_thinkering.digitalcircuits

import com.logic_thinkering.digitalcircuits.strategies.NOTStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val NOT_CODEC = AbstractBlock.createCodec(::NOTGate)
class NOTGate(settings: Settings) : AbstractLogicGate(settings, NOTStrategy()) {
    override fun getCodec(): MapCodec<NOTGate> = NOT_CODEC
}