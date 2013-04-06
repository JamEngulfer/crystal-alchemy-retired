package com.crystals.essence;


public abstract class Essence {

    public static final Essence[] essenceList = new Essence[256];

    public static final Essence explosive = new EssenceLightning(0, 10);

    public static final Essence fire = new EssenceLightning(1, 10);

    public final int essenceID;
    private final int weight;
    protected String name;

    protected Essence(int id, int weight) {
        essenceID = id;
        this.weight = weight;

        if (essenceList[id] != null)
            throw new IllegalArgumentException("Duplicate enchantment id!");
        else {
            essenceList[id] = this;
        }
    }

    public int getWeight() {
        return weight;
    }

    public Essence setName(String par1Str) {
        name = par1Str;
        return this;
    }

}
