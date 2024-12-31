package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val OR_CODEC = createCodec(::NORGate)

class ORGate(settings: Settings) : AbstractLogicGate(settings, { it.west || it.east }) {
    override fun getCodec(): MapCodec<NORGate> = OR_CODEC
}