package com.logicthinkering

import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec

private val NAND_CODEC = createCodec(::NandGate)

class NandGate(settings: Settings) : AbstractLogicGate(settings, { !(it.west && it.east) }) {
    override fun getCodec(): MapCodec<NandGate> = NAND_CODEC
}
