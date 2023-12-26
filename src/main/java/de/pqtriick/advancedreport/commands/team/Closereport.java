package de.pqtriick.advancedreport.commands.team;


import de.pqtriick.advancedreport.commands.player.ReportChat;
import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.listener.inventory.ReportClickListener;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Map;

import static de.pqtriick.advancedreport.files.configs.ReportlogConfig.Reportdir;
import static de.pqtriick.advancedreport.files.configs.ReportlogConfig.saveLog;

/**
 * @author pqtriick_
 * @created 12:43, 09.12.2023
 */

public class Closereport implements CommandExecutor {
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
            logfile = new File(Reportdir, "log_" + p.getName() + "_" + System.currentTimeMillis() + ".yml");
            if (ReportClickListener.inReport.containsValue(p)) {
                for (Map.Entry<Player, Player> map : ReportClickListener.inReport.entrySet()) {
                    if (map.getValue().equals(p)) {
                        t = map.getKey();
                        CLOSEDBY = CLOSEDBY.replace("%name%", p.getName());
                        CLOSEDBY =CLOSEDBY.replace("&", "§");
                        CLOSED = CLOSED.replace("&", "§");
                        CLOSED = CLOSED.replace("%name%", t.getName());
                        p.sendMessage(PREFIX + CLOSED);
                        t.sendMessage(PREFIX + CLOSEDBY);
                        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
                        t.playSound(t.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
                        ReportClickListener.inReport.remove(t);
                    }
                }
                var iterator = ReportChat.messages.entrySet().iterator();
                while(iterator.hasNext()) {
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

            } else {
                p.sendMessage(PREFIX + NOTINREPORT);

            }
        }
        return true;
    }

}
