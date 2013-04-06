package com.crystals.config;

import static net.minecraftforge.common.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {

    public static Configuration configuration;

    public static void init(File configFile) {

        configuration = new Configuration(configFile);
        try {
            configuration.load();

            ConfigurationSettings.DISPLAY_VERSION_RESULT = configuration
                    .get(CATEGORY_GENERAL,
                            ConfigurationSettings.DISPLAY_VERSION_RESULT_CONFIGNAME,
                            ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT)
                    .getBoolean(
                            ConfigurationSettings.DISPLAY_VERSION_RESULT_DEFAULT);
            ConfigurationSettings.LAST_DISCOVERED_VERSION = configuration.get(
                    CATEGORY_GENERAL,
                    ConfigurationSettings.LAST_DISCOVERED_VERSION_CONFIGNAME,
                    ConfigurationSettings.LAST_DISCOVERED_VERSION_DEFAULT)
                    .getString();
        } catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, "Crystal Alchemy"
                    + " has had a problem loading its config file");
        } finally {
            configuration.save();
        }
    }

    public static void set(String categoryName, String propertyName,
            String newValue) {

        configuration.load();
        if (configuration.getCategoryNames().contains(categoryName)) {
            if (configuration.getCategory(categoryName).containsKey(
                    propertyName)) {
                configuration.getCategory(categoryName).get(propertyName)
                        .set(newValue);
            }
        }
        configuration.save();
    }
}
