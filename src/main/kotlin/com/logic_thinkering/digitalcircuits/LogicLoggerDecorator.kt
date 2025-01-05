package com.logic_thinkering.digitalcircuits

import com.logic_thinkering.logger
import com.mojang.serialization.MapCodec
import net.minecraft.block.AbstractRedstoneGateBlock
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class LogicLoggerDecorator(gate: AbstractLogicGate) : AbstractLogicGate(gate.settings, gate.logicStrategy) {
    override fun getCodec(): MapCodec<AbstractRedstoneGateBlock>? = null


    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hit: BlockHitResult?
    ): ActionResult {
        val powered = state!![POWERED]
        val input = getInputPower(world!!, pos!!, state)
        logger.info("Logic gate state at {}. Powered {}, East {}, West {}, South {}",
            pos, powered, input.east, input.west, input.south)
        return super.onUse(state, world, pos, player, hit)
    }

    override fun onPlaced(
        world: World?,
        pos: BlockPos?,
        state: BlockState?,
        placer: LivingEntity?,
        itemStack: ItemStack?
    ) {
        logger.info("Placing logic gate at {}", pos!!)
        super.onPlaced(world, pos, state, placer, itemStack)
    }
}