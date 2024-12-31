package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val AND_CODEC = createCodec(::AndGate)

class AndGate(settings: Settings) : AbstractLogicGate(settings, { it.west && it.east }) {
    override fun getCodec(): MapCodec<AndGate> = AND_CODEC
}