package com.logic_thinkering.itens;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public interface Material {
    void updateSettings(int i, int j, float k, float l);
    void updateItemTag(TagKey<Item> repairIngredient);
    String getName();
}
