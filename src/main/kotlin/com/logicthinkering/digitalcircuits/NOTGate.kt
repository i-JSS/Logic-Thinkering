package com.logicthinkering.digitalcircuits

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val NOT_CODEC = AbstractBlock.createCodec(::NANDGate)

class NOTGate(settings: Settings) : AbstractLogicGate(settings, { !it.south }) {
    override fun getCodec(): MapCodec<NANDGate> = NOT_CODEC
}