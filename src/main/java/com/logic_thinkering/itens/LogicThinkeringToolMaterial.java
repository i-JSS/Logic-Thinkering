package com.logic_thinkering.itens;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.Item;

public class LogicThinkeringToolMaterial implements Material {

    private ToolMaterial material;
    private String name;

    public LogicThinkeringToolMaterial(
            String name,
            TagKey<Block> incorrectBlocksForDrops,
            int durability,
            float speed,
            float attackDamageBonus,
            int enchantmentValue,
            TagKey<Item> repairItems
    ) {
        this.name = name;
        this.material = new ToolMaterial(
                incorrectBlocksForDrops,
                durability,
                speed,
                attackDamageBonus,
                enchantmentValue,
                repairItems
        );
    }

    public ToolMaterial getMaterial() {
        return material;
    }

    @Override
    public void updateSettings(int i, int j, float k, float l) {
        //i = durability ; j = speed; k = atckdmg; l = enchantmentValue
        this.material = new ToolMaterial(
                material.incorrectBlocksForDrops(),
                i, l, k, j,
                material.repairItems()
        );
    }

    @Override
    public void updateItemTag(TagKey<Item> repairItems) {
        this.material = new ToolMaterial(
                material.incorrectBlocksForDrops(),
                material.durability(),
                material.speed(),
                material.attackDamageBonus(),
                material.enchantmentValue(),
                repairItems
        );
    }

    @Override
    public String getName() {
        return name;
    }

}