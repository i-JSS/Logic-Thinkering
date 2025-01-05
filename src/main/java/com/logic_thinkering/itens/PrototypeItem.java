package com.logic_thinkering.itens;

public abstract class PrototypeItem {
    protected StrategyRegister strategy;
    protected Material material;

    public abstract PrototypeItem clone();
    public abstract void register(String id);
    public abstract void updateMaterial(Material material);
}
