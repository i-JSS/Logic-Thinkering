package com.logicthinkering.digitalcircuits

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val NAND_CODEC = AbstractBlock.createCodec(::NANDGate)

class NANDGate(settings: Settings) : AbstractLogicGate(settings, { !(it.west && it.east) }) {
    override fun getCodec(): MapCodec<NANDGate> = NAND_CODEC
}