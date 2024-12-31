package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val OR_CODEC = createCodec(::NorGate)

class OrGate(settings: Settings) : AbstractLogicGate(settings, { it.west || it.east }) {
    override fun getCodec(): MapCodec<NorGate> = OR_CODEC
}