package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val AND_CODEC = createCodec(::ANDGate)

class ANDGate(settings: Settings) : AbstractLogicGate(settings, { it.west && it.east }) {
    override fun getCodec(): MapCodec<ANDGate> = AND_CODEC
}