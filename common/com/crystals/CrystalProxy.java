package com.crystals;

import com.crystals.update.VersionCheckTickHandler;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CrystalProxy {

    public void registerRenderThings() {
    };
    
    public void registerServerTickHandler(){
        TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);
    }
}
