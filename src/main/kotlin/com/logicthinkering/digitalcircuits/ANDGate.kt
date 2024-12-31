package com.logicthinkering.digitalcircuits

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val AND_CODEC = AbstractBlock.createCodec(::ANDGate)

class ANDGate(settings: Settings) : AbstractLogicGate(settings, { it.west && it.east }) {
    override fun getCodec(): MapCodec<ANDGate> = AND_CODEC
}