package com.johnymuffin.leaveandjoin;

import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

import java.io.File;

public class JLJConfig extends Configuration {


    public JLJConfig(Plugin plugin) {
        super(new File(plugin.getDataFolder(), "config.yml"));
        this.reload();
    }

    private void write() {
        //Main
        generateConfigOption("config-version", 1);
        //Setting
        generateConfigOption("join-message", "%prefix% &c%player% &bJoined the game.");
        generateConfigOption("leave-message", "%prefix% &c%player% &bLeft the game.");
        generateConfigOption("kick-message", "%prefix% &c%player% &bWas kicked from the game.");

    }

    private void generateConfigOption(String key, Object defaultValue) {
        if (this.getProperty(key) == null) {
            this.setProperty(key, defaultValue);
        }
        final Object value = this.getProperty(key);
        this.removeProperty(key);
        this.setProperty(key, value);
    }

    //Getters Start
    public Object getConfigOption(String key) {
        return this.getProperty(key);
    }

    public String getConfigString(String key) {
        return String.valueOf(getConfigOption(key));
    }

    public Integer getConfigInteger(String key) {
        return Integer.valueOf(getConfigString(key));
    }

    public Long getConfigLong(String key) {
        return Long.valueOf(getConfigString(key));
    }

    public Double getConfigDouble(String key) {
        return Double.valueOf(getConfigString(key));
    }

    public Boolean getConfigBoolean(String key) {
        return Boolean.valueOf(getConfigString(key));
    }


    //Getters End


    private void reload() {
        this.load();
        this.write();
        this.save();
    }
}
