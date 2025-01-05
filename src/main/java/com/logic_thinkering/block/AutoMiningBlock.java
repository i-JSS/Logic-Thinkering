package com.logic_thinkering.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class AutoMiningBlock extends Block {

    private final List<BlockPos> blocksToMine = new ArrayList<>();

    public AutoMiningBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            startMining((ServerWorld) world, pos);
        }
    }

    private void startMining(ServerWorld world, BlockPos pos) {
        // Preencher a lista de blocos a serem minerados
        int radius = 5;
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos targetPos = pos.add(new Vec3i(x, y, z));
                    BlockState targetState = world.getBlockState(targetPos);

                    // Verificar se não é o próprio bloco nem Bedrock
                    if (targetPos.equals(pos) || targetState.getBlock() == Blocks.BEDROCK) {
                        continue;
                    }

                    // Adicionar o bloco à lista de blocos a serem minerados
                    if (!targetState.isAir()) {
                        blocksToMine.add(targetPos);
                    }
                }
            }
        }

        // Agendar o primeiro tick de mineração
        if (!blocksToMine.isEmpty()) {
            world.scheduleBlockTick(pos, this, 20);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!blocksToMine.isEmpty()) {
            // Quebrar o próximo bloco na lista
            BlockPos targetPos = blocksToMine.removeFirst(); // Remove o primeiro bloco da lista
            world.breakBlock(targetPos, true);
        }

        // Agendar o próximo tick de mineração, se houver blocos restantes
        if (!blocksToMine.isEmpty()) {
            world.scheduleBlockTick(pos, this, 20);
        }
    }
}
