package com.logicthinkering

import com.mojang.authlib.properties.Property
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractBlock.createCodec
import net.minecraft.block.AbstractRedstoneGateBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

private val GATE_CODEC : MapCodec<AndGateBlock> = createCodec(::AndGateBlock)
class AndGateBlock(settings: Settings) : AbstractRedstoneGateBlock(settings) {
    override fun getCodec(): MapCodec<AndGateBlock> = GATE_CODEC

    override fun getUpdateDelayInternal(state: BlockState?) = 2

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING, POWERED)
    }

    init {
       defaultState = stateManager.defaultState
           .with(FACING, Direction.NORTH)
           .with(POWERED, false)
    }

    override fun hasPower(world: World, pos: BlockPos, state: BlockState): Boolean {
        val input1 = hasPowerFromNeighbor(world, pos, Direction.EAST)
        val input2 = hasPowerFromNeighbor(world, pos, Direction.WEST)
        return input1 && input2
    }

    private fun hasPowerFromNeighbor(world: World, pos: BlockPos, direction: Direction): Boolean {
        val neighborPos = pos.offset(direction)
        val power = world.getEmittedRedstonePower(neighborPos, direction)
        return power > 0
    }
}