package com.logic_thinkering.digitalcircuits

import com.logic_thinkering.digitalcircuits.strategies.XORStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val XOR_CODEC = AbstractBlock.createCodec(::NANDGate)

class XORGate(settings: Settings) : AbstractLogicGate(settings, XORStrategy()) {
    override fun getCodec(): MapCodec<NANDGate> = XOR_CODEC
}