package com.logicthinkering.digitalcircuits

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val XNOR_CODEC = AbstractBlock.createCodec(::NANDGate)

class XNORGate(settings: Settings) : AbstractLogicGate(settings, { !(it.west xor it.east) }) {
    override fun getCodec(): MapCodec<NANDGate> = XNOR_CODEC
}