package de.pqtriick.advancedreport.commands.player;

import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.listener.inventory.AcceptReportListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.time.LocalTime;
import java.util.*;

/**
 * @author pqtriick_
 * @created 12:22, 09.12.2023
 */

public class ReportChat implements CommandExecutor {

    public static HashMap<String, UUID> messages = new HashMap<>();
    private static String PREFIX = MessageConfig.MessageConfig.getString("messages.prefix");
    private static String NOTINREPORT = MessageConfig.MessageConfig.getString("messages.notinreport");
    private static String message;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PREFIX = PREFIX.replace("&", "§");
        NOTINREPORT = NOTINREPORT.replace("&", "§");
        Player p = (Player) sender;
        if (AcceptReportListener.inReport.containsKey(p)) {
            Player t = AcceptReportListener.inReport.get(p);
            message = "";
            for (int i = 0; i < args.length; i++) {
                message = message + " " + args[i];
            }
            t.sendMessage("§9RC §7| §3" + p.getName() + "§7: " + message);
            p.sendMessage("§9RC §7| §3" + p.getName() + "§7: " + message);

            message = p.getName() + ": " + message + " | " + LocalTime.now().withNano(0);
            messages.put(message, p.getUniqueId());
        } else {
            p.sendMessage(PREFIX + NOTINREPORT);
        }
        return false;
    }
}
