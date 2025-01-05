package com.logic_thinkering.digitalcircuits

import com.logic_thinkering.digitalcircuits.strategies.ANDStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val AND_CODEC = AbstractBlock.createCodec(::ANDGate)

class ANDGate(settings: Settings) : AbstractLogicGate(settings, ANDStrategy()) {
    override fun getCodec(): MapCodec<ANDGate> = AND_CODEC
}