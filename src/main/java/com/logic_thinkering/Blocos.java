package com.logic_thinkering;

import com.logic_thinkering.block.clock.RandomClockEnergy;
import com.logic_thinkering.block.clock.RegularClockEnergy;
import com.logic_thinkering.block.miningmachine.MiningMachineBlock;
import com.logic_thinkering.block.clock.ClockEnergy;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class Blocos {

    public static final Block REGULAR_CLOCK = RegisterBlock.registrarBloco(
            "regular_clock",
            RegularClockEnergy::new,
            AbstractBlock.Settings.create().strength(4f).requiresTool()
    );

    public static final Block RANDOM_CLOCK = RegisterBlock.registrarBloco(
            "random_clock",
            RandomClockEnergy::new,
            AbstractBlock.Settings.create().strength(4f).requiresTool()
    );

    public static final Block BLOCO_MINERACAO = RegisterBlock.registrarBloco(
            "bloco_mineracao",
            MiningMachineBlock::new,
            AbstractBlock.Settings.create().strength(4f).requiresTool()
    );

    public static final Block MATRIZ_FIOS = RegisterBlock.registrarBloco(
            "matriz_fios",
            Block::new,
            AbstractBlock.Settings.create().strength(4f).requiresTool()
    );

    public static void inicializa() {
        ItemGroupEvents.modifyEntriesEvent(LogicThinkeringItemGroup.LOGICTHINKERING_GROUP).register(entries -> {
            entries.add(Blocos.REGULAR_CLOCK);
            entries.add(Blocos.RANDOM_CLOCK);
            entries.add(Blocos.BLOCO_MINERACAO);
            entries.add(Blocos.MATRIZ_FIOS);
        });
    }
}