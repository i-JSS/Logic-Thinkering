package com.logic_thinkering.itens;

import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;

public class ConcreteItem extends PrototypeItem {
    private String id;
    public static Item ITEM;

    public ConcreteItem(String id) {
        this.material = null;
        strategy = new ConcreteRegisterItem();
        if(id != null) setId(id);
    }

    @Override
    public ConcreteItem clone() {
        return new ConcreteItem(null);
    }

    @Override
    public void register(String id) {
        ITEM = strategy.register(id);
    }

    @Override
    public void updateMaterial(Material material) {
        throw new IllegalArgumentException("This class does not support updateMaterial");
    }

    public void setId(String id) {
        this.id = id;
        register(id);
    }

}
