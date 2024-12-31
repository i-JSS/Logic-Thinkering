package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val NOR_CODEC = createCodec(::NORGate)

class NORGate(settings: Settings) : AbstractLogicGate(settings, { !(it.west || it.east) }) {
    override fun getCodec(): MapCodec<NORGate> = NOR_CODEC
}
