package com.logic_thinkering.itens;

import net.minecraft.item.Item;

public class ConcreteArmor extends PrototypeItem {

    public static Item ITEM;
    private String id;
    private ArmorType type;

    public ConcreteArmor(String id, ArmorType type, Material material) {
        if (material instanceof LogicThinkeringArmorMaterial materialArmadura) {
            strategy = new ConcreteRegisterArmor();
            this.type = type;
            this.material = materialArmadura;
            if(id != null) setId(id);
        }
    }

    @Override
    public ConcreteArmor clone() {
        return new ConcreteArmor(null, this.type, this.material);
    }

    @Override
    public void register(String id) {
        ITEM = strategy.register(id, this.material, this.type.name());
    }

    public void setId(String id) {
        this.id = id;
        register(this.id);
    }

    public void setType(String type){
        this.type = ArmorType.valueOf(type.toUpperCase());
    }

    public void updateMaterial(Material material){
        if (material instanceof LogicThinkeringArmorMaterial materialArmadura) {
            this.material = materialArmadura;
        }
    }

}

