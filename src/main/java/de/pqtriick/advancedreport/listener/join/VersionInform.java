package de.pqtriick.advancedreport.listener.join;

import de.pqtriick.advancedreport.AdvancedReport;
import de.pqtriick.advancedreport.files.configs.MessageConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author pqtriick_
 * @created 17:15, 24.12.2023
 */

public class VersionInform implements Listener {

    private static String PREFIX = MessageConfig.MessageConfig.getString("messages.prefix");

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        PREFIX = PREFIX.replace("&", "§");
        Player p = event.getPlayer();
        if (p.hasPermission("AR.admin")) {
            if (AdvancedReport.hasUpdate) {
                p.sendMessage(PREFIX + "§aThere is a new Update available for §3AdvancedReport §a!");
                p.sendMessage(PREFIX + "§ehttps://www.spigotmc.org/resources/advancedreport.114085/");
            }
        }
    }

}
