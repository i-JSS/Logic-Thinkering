package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val NOR_CODEC = createCodec(::NorGate)

class NorGate(settings: Settings) : AbstractLogicGate(settings, { !(it.west || it.east) }) {
    override fun getCodec(): MapCodec<NorGate> = NOR_CODEC
}
