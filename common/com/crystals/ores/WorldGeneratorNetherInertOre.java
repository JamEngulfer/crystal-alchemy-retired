package com.crystals.ores;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import com.crystals.CrystalMod;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorNetherInertOre implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.dimensionId) {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
        }

    }

    private void generateNether(World world, Random random, int i, int j) {

        for (int y = 0; y < 20; y++) {

            int Xcoord = i + random.nextInt(16);
            int Zcoord = j + random.nextInt(16);
            int Ycoord = random.nextInt(96);

            new CrystalNetherGenerator(CrystalMod.InertOreNether.blockID, 5)
                    .generate(world, random, Xcoord, Ycoord, Zcoord);
        }
    }

}
