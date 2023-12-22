package de.pqtriick.advancedreport.files.configs;

import de.pqtriick.advancedreport.AdvancedReport;
import de.pqtriick.advancedreport.files.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author pqtriick_
 * @created 16:43, 06.12.2023
 */

public class ReportConfig {

    public static File ReportFile = new File(AdvancedReport.getInstance().getDataFolder().getPath(), "reportconfig.yml");
    public static FileConfiguration ReportConfiguration = YamlConfiguration.loadConfiguration(ReportFile);

    public ReportConfig() {
        if (!ReportFile.exists()) {
            Config.createFile(ReportFile);
            Config.set(ReportConfiguration, ReportFile, "UIID.12.name", "§cʀᴇᴘᴏʀᴛ ᴘʟᴀʏᴇʀ");
            Config.set(ReportConfiguration, ReportFile, "UIID.12.lore", "§7» §cClick to open report");
            Config.set(ReportConfiguration, ReportFile, "UIID.12.material", "RED_CONCRETE");
            Config.set(ReportConfiguration, ReportFile, "UIID.12.type", "Player Report");
            Config.set(ReportConfiguration, ReportFile, "UIID.13.name", "§9ʀᴇᴘᴏʀᴛ ʙᴜɢ");
            Config.set(ReportConfiguration, ReportFile, "UIID.13.lore", "§7» §9Click to open report");
            Config.set(ReportConfiguration, ReportFile, "UIID.13.material", "BLUE_CONCRETE");
            Config.set(ReportConfiguration, ReportFile, "UIID.13.type", "Bug Report");
            Config.set(ReportConfiguration, ReportFile, "UIID.14.name", "§dʀᴇᴘᴏʀᴛ ᴛᴇᴀᴍ");
            Config.set(ReportConfiguration, ReportFile, "UIID.14.lore", "§7» §dClick to open report");
            Config.set(ReportConfiguration, ReportFile, "UIID.14.material", "PINK_CONCRETE");
            Config.set(ReportConfiguration, ReportFile, "UIID.14.type", "Team Report");
            Config.set(ReportConfiguration, ReportFile, "UIID.21.name", "§eɢᴀᴍᴇ Qᴜᴇꜱᴛɪᴏɴ");
            Config.set(ReportConfiguration, ReportFile, "UIID.21.lore", "§7» §eClick to open ticket");
            Config.set(ReportConfiguration, ReportFile, "UIID.21.material", "YELLOW_CONCRETE");
            Config.set(ReportConfiguration, ReportFile, "UIID.21.type", "Game Question");
            Config.set(ReportConfiguration, ReportFile, "UIID.22.name", "§6ꜱᴇʀᴠᴇʀ Qᴜᴇꜱᴛɪᴏɴ");
            Config.set(ReportConfiguration, ReportFile, "UIID.22.lore", "§7» §6Click to open ticket");
            Config.set(ReportConfiguration, ReportFile, "UIID.22.material", "ORANGE_CONCRETE");
            Config.set(ReportConfiguration, ReportFile, "UIID.22.type", "Server Question");
            Config.set(ReportConfiguration, ReportFile, "UIID.23.name", "§aꜱʜᴏᴘ Qᴜᴇꜱᴛɪᴏɴ");
            Config.set(ReportConfiguration, ReportFile, "UIID.23.lore", "§7» §aClick to open ticket");
            Config.set(ReportConfiguration, ReportFile, "UIID.23.material", "LIME_CONCRETE");
            Config.set(ReportConfiguration, ReportFile, "UIID.23.type", "Shop Question");
            Config.saveFile(ReportConfiguration, ReportFile);

        }
    }
}
