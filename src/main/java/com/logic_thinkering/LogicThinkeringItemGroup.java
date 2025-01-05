package com.logic_thinkering;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class LogicThinkeringItemGroup {
    public static final RegistryKey<ItemGroup> LOGICTHINKERING_GROUP;
    public static final ItemGroup LOGICTHINKERING_ITEM_GROUP;

    static {
        LOGICTHINKERING_ITEM_GROUP = FabricItemGroup.builder()
                .icon(() -> new ItemStack(Items.CRAFTER))
                .displayName(Text.translatable("Logic Thinkering"))
                .build();
        LOGICTHINKERING_GROUP = RegistryKey.of(
                Registries.ITEM_GROUP.getKey(),
                Identifier.of(Main.MOD_ID, "logic-thinkering")
        );
        Registry.register(Registries.ITEM_GROUP, LOGICTHINKERING_GROUP, LOGICTHINKERING_ITEM_GROUP);
    }

    private LogicThinkeringItemGroup() {
        throw new UnsupportedOperationException("Esta é uma classe utilitária e não pode ser instanciada.");
    }

    public static void addItem(Item item) {
        ItemGroupEvents.modifyEntriesEvent(LOGICTHINKERING_GROUP).register(itemGroup -> itemGroup.add(item));
    }
}
