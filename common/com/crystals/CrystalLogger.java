package com.crystals;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

/**
 * Equivalent-Exchange-3
 * 
 * LogHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CrystalLogger {

    private static Logger caLogger = Logger.getLogger("Crystal Alchemy");

    public static void init() {

        caLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {

        caLogger.log(logLevel, message);
    }

}