package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val NOT_CODEC = createCodec(::NandGate)

class NotGate(settings: Settings) : AbstractLogicGate(settings, { !it.south }) {
    override fun getCodec(): MapCodec<NandGate> = NOT_CODEC
}
