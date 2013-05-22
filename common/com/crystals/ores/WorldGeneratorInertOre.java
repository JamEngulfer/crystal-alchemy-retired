package com.crystals.ores;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.crystals.CrystalMod;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorInertOre implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        switch (world.provider.dimensionId) {
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
        }

    }

    private void generateSurface(World world, Random random, int i, int j) {

        for (int z = 0; z < 10; z++) {

            int Xcoord = i + random.nextInt(16);
            int Zcoord = j + random.nextInt(16);
            int Ycoord = 20 + random.nextInt(45);
            
            new WorldGenMinable(CrystalMod.InertOre.blockID, 2, Block.stone.blockID).generate(world,
                    random, Xcoord, Ycoord, Zcoord);
        }
    }
}
