package de.pqtriick.advancedreport.commands.player;

import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.listener.inventory.ReportClickListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

/**
 * @author pqtriick_
 * @created 12:22, 09.12.2023
 */

public class ReportChat implements CommandExecutor {

    public static HashMap<String, UUID> messages = new HashMap<>();
    private static DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static String PREFIX = MessageConfig.MessageConfig.getString("messages.prefix");
    private static String NOTINREPORT = MessageConfig.MessageConfig.getString("messages.notinreport");
    private static String message;
    private static Player target;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PREFIX = PREFIX.replace("&", "§");
        NOTINREPORT = NOTINREPORT.replace("&", "§");
        Player player = (Player) sender;
        message = "";
        for (int i = 0; i < args.length; i++) {
            message = message + " " + args[i];
        }
        if (ReportClickListener.inReport.containsKey(player)) {
            target = ReportClickListener.inReport.get(player);
            target.sendMessage("§9RC §7| §3" + player.getName() + "§7: " + message);
            player.sendMessage("§9RC §7| §3" + player.getName() + "§7: " + message);
            messages.put(message + " (" + player.getName() + ", " + format.format(new Date()), player.getUniqueId());
        } else if (ReportClickListener.inReport.containsValue(player)) {
            for (Map.Entry<Player, Player> entry : ReportClickListener.inReport.entrySet()) {
                if (entry.getValue() == player) {
                    target = entry.getKey();
                }
            }
            target.sendMessage("§9RC §7| §3" + player.getName() + "§7: " + message);
            player.sendMessage("§9RC §7| §3" + player.getName() + "§7: " + message);
            messages.put(message + " (" + player.getName() + ", " + format.format(new Date()), player.getUniqueId());
        } else return false;
        return false;
    }

}
