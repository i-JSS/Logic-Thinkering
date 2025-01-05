package com.logic_thinkering.digitalcircuits

import com.logic_thinkering.digitalcircuits.strategies.NANDStrategy
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock

private val NAND_CODEC = AbstractBlock.createCodec(::NANDGate)

class NANDGate(settings: Settings) : AbstractLogicGate(settings, NANDStrategy()) {
    override fun getCodec(): MapCodec<NANDGate> = NAND_CODEC
}