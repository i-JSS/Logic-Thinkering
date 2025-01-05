package com.logic_thinkering.itens;

import net.minecraft.item.Item;

public class ConcreteTool extends PrototypeItem {

    public static Item ITEM;
    private String id;
    private ToolType type;

    public ConcreteTool(String id, ToolType type, Material material) {
        if (material instanceof LogicThinkeringToolMaterial materialtool) {
            strategy = new ConcreteRegisterTool();
            this.type = type;
            this.material = materialtool;
            if(id != null) setId(id);
        }
    }

    @Override
    public ConcreteTool clone() {
        return new ConcreteTool(null, this.type, this.material);
    }

    @Override
    public void register(String id) {
        ITEM = strategy.register(id, this.material, this.type.name());
    }

    public void setType(String type) {
        this.type = ToolType.valueOf(type.toUpperCase());
    }

    public void updateMaterial(Material material) {
        if (material instanceof LogicThinkeringToolMaterial materialtool) {
            this.material = materialtool;
        }
    }

    public void setId(String id) {
        this.id = id;
        register(id);
    }

}