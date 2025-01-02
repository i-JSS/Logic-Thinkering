package com.logicthinkering.digitalcircuits

import com.logicthinkering.digitalcircuits.strategies.ORStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val OR_CODEC = AbstractBlock.createCodec(::NORGate)

class ORGate(settings: Settings) : AbstractLogicGate(settings, ORStrategy()) {
    override fun getCodec(): MapCodec<NORGate> = OR_CODEC
}