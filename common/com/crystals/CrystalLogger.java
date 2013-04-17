package com.crystals;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class CrystalLogger {

    private static Logger caLogger = Logger.getLogger("Crystal Alchemy");

    public static void init() {

        caLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {

        caLogger.log(logLevel, message);
    }

}