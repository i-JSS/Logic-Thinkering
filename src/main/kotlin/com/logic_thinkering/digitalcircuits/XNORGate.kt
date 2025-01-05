package com.logic_thinkering.digitalcircuits

import com.logic_thinkering.digitalcircuits.strategies.XNORStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val XNOR_CODEC = AbstractBlock.createCodec(::NANDGate)

class XNORGate(settings: Settings) : AbstractLogicGate(settings, XNORStrategy()) {
    override fun getCodec(): MapCodec<NANDGate> = XNOR_CODEC
}