package org.PGAdmin.savedDataManager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class savedDataManager {
    private final JavaPlugin plugin;
    private File savedDataFile;
    private YamlConfiguration savedData;

    public savedDataManager(JavaPlugin plugin) {
        String savedDataFileName = "PGAdminSavedData.yml";
        this.plugin = plugin;
        this.savedDataFile = new File(plugin.getDataFolder(), savedDataFileName);
        if (!savedDataFile.exists()) {
            try {
                plugin.saveResource(savedDataFileName, false);
            } catch (Exception e) {
                plugin.getServer().getConsoleSender().sendMessage("Произошла непредвиденная ошибка при ");
            }
        }

        this.savedData = YamlConfiguration.loadConfiguration(savedDataFile);
    }

    public void saveStringData(String key, String value) {
        savedData.set(key, value);
        try {
            savedData.save(savedDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringData(String key) {
        return savedData.getString(key, "EMPTY");
    }

    public int getIntData(String key) {
        return savedData.getInt(key, 0);
    }

    public long getLongData(String key) {
        return savedData.getLong(key, 0);
    }

    public void saveLongData(String key, long value) {
        savedData.set(key, value);
        try {
            savedData.save(savedDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getListData(String key) {
        return (ArrayList<String>) savedData.getList(key, new ArrayList<String>());
    }

    public void saveListData(String key, ArrayList<String> list) {
        savedData.set(key, list);
        try {
            savedData.save(savedDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDataExist(String key) {
        return savedData.contains(key);
    }

}

