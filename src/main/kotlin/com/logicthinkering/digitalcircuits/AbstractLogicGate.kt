package com.logicthinkering.digitalcircuits

import net.minecraft.block.AbstractRedstoneGateBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

data class InputPower(
    val east: Boolean,
    val west: Boolean,
    val south: Boolean,
)

abstract class AbstractLogicGate(settings: Settings, val logicFunction: (InputPower) -> Boolean) :
    AbstractRedstoneGateBlock(settings) {
    init {
        defaultState = stateManager.defaultState
            .with(FACING, Direction.NORTH)
            .with(POWERED, false)
    }

    override fun getUpdateDelayInternal(state: BlockState?) = 2
    override fun hasPower(world: World, pos: BlockPos, state: BlockState) = logicFunction(getInputPower(world, pos))

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING, POWERED)
    }

    fun getInputPower(world: World, pos: BlockPos) = InputPower(
        hasPowerFromNeighbor(world, pos, Direction.EAST),
        hasPowerFromNeighbor(world, pos, Direction.WEST),
        hasPowerFromNeighbor(world, pos, Direction.SOUTH),
    )

    protected fun hasPowerFromNeighbor(world: World, pos: BlockPos, direction: Direction): Boolean {
        val neighborPos = pos.offset(direction)
        val power = world.getEmittedRedstonePower(neighborPos, direction)
        return power > 0
    }
}