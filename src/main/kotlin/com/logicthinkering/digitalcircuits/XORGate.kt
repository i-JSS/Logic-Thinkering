package com.logicthinkering.digitalcircuits

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val XOR_CODEC = AbstractBlock.createCodec(::NANDGate)

class XORGate(settings: Settings) : AbstractLogicGate(settings, { it.west xor it.east }) {
    override fun getCodec(): MapCodec<NANDGate> = XOR_CODEC
}