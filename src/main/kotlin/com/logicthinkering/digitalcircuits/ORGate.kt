package com.logicthinkering.digitalcircuits

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val OR_CODEC = AbstractBlock.createCodec(::NORGate)

class ORGate(settings: Settings) : AbstractLogicGate(settings, { it.west || it.east }) {
    override fun getCodec(): MapCodec<NORGate> = OR_CODEC
}