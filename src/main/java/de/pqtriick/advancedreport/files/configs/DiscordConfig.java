package de.pqtriick.advancedreport.files.configs;

import de.pqtriick.advancedreport.AdvancedReport;
import de.pqtriick.advancedreport.files.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author pqtriick_
 * @created 15:45, 09.12.2023
 */

public class DiscordConfig {

    public static File DiscordFile = new File(AdvancedReport.getInstance().getDataFolder().getPath(), "discordbotconfig.yml");
    public static FileConfiguration DiscordConfiguration = YamlConfiguration.loadConfiguration(DiscordFile);

    public DiscordConfig() {
        if (!DiscordFile.exists()) {
            Config.createFile(DiscordFile);
            Config.set(DiscordConfiguration, DiscordFile, "options.enabled", "false");
            Config.set(DiscordConfiguration, DiscordFile, "options.bottoken", "");
            Config.set(DiscordConfiguration, DiscordFile, "options.channelid", "");
            Config.saveFile(DiscordConfiguration, DiscordFile);
        }
    }
}
