package com.logicthinkering.digitalcircuits

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val NOR_CODEC = AbstractBlock.createCodec(::NORGate)

class NORGate(settings: Settings) : AbstractLogicGate(settings, { !(it.west || it.east) }) {
    override fun getCodec(): MapCodec<NORGate> = NOR_CODEC
}