package de.pqtriick.advancedreport.commands.team;


import de.pqtriick.advancedreport.AdvancedReport;
import de.pqtriick.advancedreport.commands.player.ReportChat;
import de.pqtriick.advancedreport.files.configs.DiscordConfig;
import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.listener.inventory.ReportClickListener;
import de.pqtriick.advancedreport.util.BotInitializer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.util.Map;

import static de.pqtriick.advancedreport.files.configs.ReportlogConfig.Reportdir;
import static de.pqtriick.advancedreport.files.configs.ReportlogConfig.saveLog;

/**
 * @author pqtriick_
 * @created 12:43, 09.12.2023
 */

public class Closereport implements CommandExecutor {
    private JDA bot;
    private EmbedBuilder eb = new EmbedBuilder();
    private String channel;
    public static File logfile;
    private static Player t;

    private static String PREFIX = MessageConfig.MessageConfig.getString("messages.prefix");
    private static String CLOSEDBY = MessageConfig.MessageConfig.getString("messages.closedby");
    private static String CLOSED = MessageConfig.MessageConfig.getString("messages.closed");
    private static String LOGSAVED = MessageConfig.MessageConfig.getString("messages.logsaved");
    private static String LOGERROR = MessageConfig.MessageConfig.getString("messages.logerror");
    private static String NOTINREPORT = MessageConfig.MessageConfig.getString("messages.notinreport");


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        PREFIX = PREFIX.replace("&", "§");
        LOGSAVED = LOGSAVED.replace("&", "§");
        LOGERROR = LOGERROR.replace("&", "§");
        NOTINREPORT = NOTINREPORT.replace("&", "§");
        if (p.hasPermission("AR.seereport")) {
            if (ReportClickListener.inReport.containsKey(p)) {
                t = ReportClickListener.inReport.get(p);
                logfile = new File(Reportdir, "log_" + p.getName() + "_" + t.getName() + "_" + System.currentTimeMillis() + ".yml");
                CLOSEDBY = CLOSEDBY.replace("%name%", p.getName());
                CLOSEDBY = CLOSEDBY.replace("&", "§");
                CLOSED = CLOSED.replace("&", "§");
                CLOSED = CLOSED.replace("%name%", t.getName());
                p.sendMessage(PREFIX + CLOSED);
                t.sendMessage(PREFIX + CLOSEDBY);
                p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
                t.playSound(t.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
                ReportClickListener.inReport.remove(p);
            }
            var iterator = ReportChat.messages.entrySet().iterator();
            while (iterator.hasNext()) {
                var msg = iterator.next();
                iterator.remove();
                if (msg.getValue() == p.getUniqueId()) {
                    saveLog(logfile, msg.getKey());
                    msg.getKey().replace(msg.getKey(), "");
                }
                if (msg.getValue() == t.getUniqueId()) {
                    saveLog(logfile, msg.getKey());
                    msg.getKey().replace(msg.getKey(), "");

                }
            }
            if (AdvancedReport.hasDCBot) {
                bot = BotInitializer.getJDA();
                channel = DiscordConfig.DiscordConfiguration.getString("options.channelid");
                eb.setTitle("New Reportlog!");
                eb.setAuthor("AdvancedReport", "https://www.spigotmc.org/resources/advancedreport.114085/", "https://mc-heads.net/avatar/" + p.getUniqueId());
                eb.setColor(new Color(67, 255, 74));
                eb.addField("Reporter", t.getName(), false);
                eb.addField("Staff Member", p.getName(), false);
                eb.addField("File in directory", "log_" + p.getName() + "_" + System.currentTimeMillis() + ".yml", false);
                bot.getTextChannelById(channel).sendMessageEmbeds(eb.build()).queue();
            }
        } else {
            p.sendMessage(PREFIX + NOTINREPORT);
        }
        return true;
    }
}
