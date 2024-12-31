package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val NOT_CODEC = createCodec(::NANDGate)

class NOTGate(settings: Settings) : AbstractLogicGate(settings, { !it.south }) {
    override fun getCodec(): MapCodec<NANDGate> = NOT_CODEC
}
