package de.pqtriick.advancedreport.files.configs;

import de.pqtriick.advancedreport.AdvancedReport;
import de.pqtriick.advancedreport.files.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author pqtriick_
 * @created 19:03, 04.12.2023
 */

public class MessageConfig {

    public static File MessageFile = new File(AdvancedReport.getInstance().getDataFolder().getPath(), "messages.yml");
    public static FileConfiguration MessageConfig = YamlConfiguration.loadConfiguration(MessageFile);

    public MessageConfig() {
        if (!MessageFile.exists()) {
            Config.createFile(MessageFile);
            Config.set(MessageConfig, MessageFile, "messages.prefix", "&6ʀᴇᴘᴏʀᴛ &7| ");
            Config.set(MessageConfig, MessageFile, "messages.cancelreport", "&aSucessfully canceled report!");
            Config.set(MessageConfig, MessageFile, "messages.notinreport", "&cYou are not in a report!");
            Config.set(MessageConfig, MessageFile, "messages.closedby", "&eYour report was closed by &6%name% !");
            Config.set(MessageConfig, MessageFile, "messages.closed", "&eYou closed the report of &6%name% !");
            Config.set(MessageConfig, MessageFile, "messages.logsaved", "&aReportlog was saved sucessfully!");
            Config.set(MessageConfig, MessageFile, "messages.logerror", "&cSomething went wrong while savin the reportlog!");
            Config.set(MessageConfig, MessageFile, "messages.GUIItemTitle", "Open report");
            Config.set(MessageConfig, MessageFile, "messages.GUIItemlore3", "&7&l>> &2Leftclick to accept");
            Config.set(MessageConfig, MessageFile, "messages.reportaccepted", "&7Accepted Report of &9%name% &7[%reason%]");
            Config.set(MessageConfig, MessageFile, "messages.reportacceptmsg1", "&aYour report got accepted!");
            Config.set(MessageConfig, MessageFile, "messages.reportacceptmsg2", "&6Talking with: &e%name%");
            Config.set(MessageConfig, MessageFile, "messages.reportacceptmsg3", "&6Write &e/rc <Message> &6to communicate!");
            Config.set(MessageConfig, MessageFile, "messages.alreadyinreport", "&cYou are already in a report!");
            Config.set(MessageConfig, MessageFile, "messages.sentreport1", "&6You have sucessfully sent your request.");
            Config.set(MessageConfig, MessageFile, "messages.sentreport2", "&6If you want to cancel your request simply type &c/cancelreport");
            Config.set(MessageConfig, MessageFile, "messages.newreport", "&3A new report was requested. &7[&9%name% &7| &b%reporttype%&7]");
            Config.set(MessageConfig, MessageFile, "messages.alreadysentreport", "&cYou already have an active request!");
            Config.saveFile(MessageConfig, MessageFile);
        }
    }


}
