package com.crystals.essence;

import net.minecraft.util.WeightedRandomItem;

public class EssenceData extends WeightedRandomItem {

    /** Enchantment object associated with this EnchantmentData */
    public final Essence essenceobj;

    /** Enchantment level associated with this EnchantmentData */
    public final int essenceLevel;

    public EssenceData(Essence essence, int par2) {
        super(essence.getWeight());
        essenceobj = essence;
        essenceLevel = par2;
    }

    public EssenceData(int id, int weight) {
        this(Essence.essenceList[id], weight);
    }
}
