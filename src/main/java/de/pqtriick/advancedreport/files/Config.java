package de.pqtriick.advancedreport.files;

import de.pqtriick.advancedreport.AdvancedReport;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author pqtriick_
 * @created 19:01, 04.12.2023
 */

public class Config {

    public static File directory = new File(AdvancedReport.getInstance().getDataFolder().getPath());

    public Config() {
        createDir();
    }

    public static void createDir() {
        if (!directory.exists()) {
            directory.mkdir();
        }

    }

    public static void createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exe) {
                exe.printStackTrace();
            }
        }

    }

    public static void set(FileConfiguration config, File file, String path, String value) {
        config.set(path,value);
        saveFile(config, file);

    }

    public static FileConfiguration getConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveFile(FileConfiguration configuration, File file) {
        try {
            configuration.save(file);
        } catch (IOException exe) {
            exe.printStackTrace();
        }

    }

}
