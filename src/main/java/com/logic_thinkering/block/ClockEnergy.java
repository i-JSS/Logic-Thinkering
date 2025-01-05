package com.logic_thinkering.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ClockEnergy extends Block {
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    public ClockEnergy(AbstractBlock.Settings settings) {
        super(settings);
        // Define o estado inicial do bloco como "inativo"
        this.setDefaultState(this.stateManager.getDefaultState().with(ACTIVE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE); // Adiciona a propriedade "active" ao bloco
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return state.get(ACTIVE); // Só emite sinal se estiver "ativo"
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(ACTIVE) ? 15 : 0; // Emite potência máxima quando "ativo", senão 0
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Alterna o estado do bloco (ativo/inativo)
        boolean isActive = state.get(ACTIVE);
        world.setBlockState(pos, state.with(ACTIVE, !isActive), Block.NOTIFY_ALL);

        // Solicita uma nova atualização (20 ticks = 1 segundo)
        world.scheduleBlockTick(pos, this, 100);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        // Quando o bloco é colocado, inicia o ciclo de atualizações
        if (!world.isClient) {
            world.scheduleBlockTick(pos, this, 100); // Primeira atualização 20 ticks = 1 segundo
        }
    }
}