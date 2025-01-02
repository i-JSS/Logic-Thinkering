package com.logicthinkering.digitalcircuits

import com.logicthinkering.digitalcircuits.strategies.XNORStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val XNOR_CODEC = AbstractBlock.createCodec(::NANDGate)

class XNORGate(settings: Settings) : AbstractLogicGate(settings, XNORStrategy()) {
    override fun getCodec(): MapCodec<NANDGate> = XNOR_CODEC
}