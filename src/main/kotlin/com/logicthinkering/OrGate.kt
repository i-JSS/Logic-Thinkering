package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val OR_CODEC = createCodec(::OrGate)

class OrGate(settings: Settings) : AbstractLogicGate(settings, { it.west || it.east }) {
    override fun getCodec(): MapCodec<OrGate> = OR_CODEC
}