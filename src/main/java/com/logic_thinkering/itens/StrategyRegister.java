package com.logic_thinkering.itens;

import com.logic_thinkering.LogicThinkeringItemGroup;
import net.minecraft.item.Item;

public interface StrategyRegister {
    default void insertOnGroup(Item item) {
        LogicThinkeringItemGroup.addItem(item);
    }
    Item register(String id);
    Item register(String id, Material material, String type);
}
