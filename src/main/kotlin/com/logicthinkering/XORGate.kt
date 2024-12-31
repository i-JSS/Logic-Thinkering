package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val XOR_CODEC = createCodec(::NANDGate)

class XORGate(settings: Settings) : AbstractLogicGate(settings, { it.west.xor(it.east) }) {
    override fun getCodec(): MapCodec<NANDGate> = XOR_CODEC
}
