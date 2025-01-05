package com.logic_thinkering;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class RegisterBlock {

    public static Block registrarBloco(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Main.MOD_ID, id));

        Block bloco = factory.apply(settings.registryKey(key));

        Registry.register(Registries.BLOCK, key, bloco);

        registrarItemBloco(key, bloco);

        return bloco;
    }

    private static void registrarItemBloco(RegistryKey<Block> key, Block bloco) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, key.getValue());

        Item item = new BlockItem(bloco, new Item.Settings().registryKey(itemKey));
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        Registry.register(Registries.ITEM, itemKey, item);
    }

}