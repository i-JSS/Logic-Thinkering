package com.logic_thinkering.digitalcircuits

import com.logic_thinkering.digitalcircuits.strategies.LogicStrategy
import net.minecraft.block.AbstractRedstoneGateBlock
import net.minecraft.block.AbstractRedstoneGateBlock.createCuboidShape
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldView


/**
 * Data class representing the input power from neighbouring blocks.
 *
 * @property east Boolean indicating if there is power coming from the east side.
 * @property west Boolean indicating if there is power coming from the west side.
 * @property south Boolean indicating if there is power coming from the south side.
 *
 */

private val BLOCK_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
/**
 * Abstract base class for logic digital gates that extends the behaviour of redstone gates in Minecraft.
 * this class takes a logic function that determines whether the gate is powered based on the input power from
 * neighboring blocks.
 *
 * @param settings The settings from the block, passed to the superclass 'AbstractRedstoneGateBlock'.
 * @param logicStrategy a strategy for the respective logic gate
 * indicating whether the gate should be powered
 */
abstract class AbstractLogicGate(settings: Settings, val logicStrategy: LogicStrategy) :
    AbstractRedstoneGateBlock(settings) {
    init {
        defaultState = stateManager.defaultState
            .with(FACING, Direction.NORTH)
            .with(POWERED, false)
    }

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ) = BLOCK_SHAPE

    /**
     * Delay before the gate's state is updated. hardcoded to two ticks.
     *
     * @param state the current block state
     * @return The delay time in ticks
     */
    override fun getUpdateDelayInternal(state: BlockState?) = 2

    /**
     * Checks if the gate is powered based on the input from its neighbors. The logic function
     * passed to the gate is used to determine if the gate should be powered or not. Defaults to false if state is undefined.
     *
     * @param world The current world instance.
     * @param pos The position of the block in the world.
     * @param state The current block state.
     * @return Boolean indicating whether the gate is powered.
     */
    override fun hasPower(world: World, pos: BlockPos, state: BlockState): Boolean {
        return try {
            logicStrategy.getOutput(getInputPower(world, pos, state))
        } catch (e: Exception) {
            false
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING, POWERED)
    }


    /**
     * Retrieves the input power from neighboring blocks. This function checks the redstone power from the blocks
     * to the east, west, and south relative to the gate.
     *
     * @param world The world instance in which the gate exists.
     * @param pos The position of the gate block.
     * @param state The current state of the block.
     * @return An `InputPower` object containing the power status from the east, west, and south neighbors.
     */
    fun getInputPower(world: World, pos: BlockPos, state: BlockState) : InputPower{
        val facing = state[FACING]
         return InputPower(
             hasPowerFromNeighbor(world, pos, facing.rotateYClockwise()),
             hasPowerFromNeighbor(world, pos, facing.rotateYCounterclockwise()),
             hasPowerFromNeighbor(world, pos, facing),
         )
    }

    /**
     * Checks if there is power coming from a neighboring block in a specific direction. This function checks the
     * emitted redstone power from a neighboring block.
     *
     * @param world The world instance.
     * @param pos The position of the current block.
     * @param direction The direction to check for power (east, west, south, etc.).
     * @return Boolean indicating if the neighbor block is emitting redstone power. Defaults to false if state is undefined.
     */
    private fun hasPowerFromNeighbor(world: World, pos: BlockPos, direction: Direction): Boolean {
        val neighborPos = pos.offset(direction)
        return try {
            val power = world.getEmittedRedstonePower(neighborPos, direction)
            power > 0
        } catch (e: Exception) {
            false
        }
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val belowBlockPos = pos.down()
        val blockBelow = world.getBlockState(belowBlockPos)
        return blockBelow.isSolidBlock(world, belowBlockPos)
    }

}