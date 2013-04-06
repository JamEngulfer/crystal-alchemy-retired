package com.crystals;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientTextures extends CrystalProxy {

    @Override
    public void registerRenderThings() {
        MinecraftForgeClient.preloadTexture("/crystals/CrystalBlocks.png");
        MinecraftForgeClient.preloadTexture("/crystals/CrystalItems.png");
    }

}
