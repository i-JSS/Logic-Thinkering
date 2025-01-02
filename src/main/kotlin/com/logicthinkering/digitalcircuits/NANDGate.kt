package com.logicthinkering.digitalcircuits

import com.logicthinkering.digitalcircuits.strategies.NANDStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val NAND_CODEC = AbstractBlock.createCodec(::NANDGate)

class NANDGate(settings: Settings) : AbstractLogicGate(settings, NANDStrategy()) {
    override fun getCodec(): MapCodec<NANDGate> = NAND_CODEC
}