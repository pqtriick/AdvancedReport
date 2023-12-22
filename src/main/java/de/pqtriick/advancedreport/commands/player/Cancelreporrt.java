package de.pqtriick.advancedreport.commands.player;

import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.listener.inventory.MainGUIListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author pqtriick_
 * @created 17:57, 08.12.2023
 */

public class Cancelreporrt implements CommandExecutor {

    private static String PREFIX = MessageConfig.MessageConfig.getString("messages.prefix");
    private static String CANCELREPORT = MessageConfig.MessageConfig.getString("messages.cancelreport");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PREFIX = PREFIX.replace("&", "§");
        CANCELREPORT = CANCELREPORT.replace("&", "§");
        Player p = (Player) sender;
        if (MainGUIListener.ReportChoice.containsKey(p)) {
            MainGUIListener.ReportChoice.remove(p);
            p.sendMessage(PREFIX + CANCELREPORT);

        }
        return false;
    }
}
