package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val XNOR_CODEC = createCodec(::NANDGate)

class XNORGate(settings: Settings) : AbstractLogicGate(settings, { !it.west.xor(it.east) }) {
    override fun getCodec(): MapCodec<NANDGate> = XNOR_CODEC
}
