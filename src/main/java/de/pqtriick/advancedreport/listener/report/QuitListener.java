package de.pqtriick.advancedreport.listener.report;

import de.pqtriick.advancedreport.commands.player.ReportChat;
import de.pqtriick.advancedreport.listener.inventory.AcceptReportListener;
import de.pqtriick.advancedreport.listener.inventory.MainGUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static de.pqtriick.advancedreport.commands.team.Closereport.*;

import java.util.Map;

/**
 * @author pqtriick_
 * @created 12:22, 09.12.2023
 */

public class QuitListener implements Listener {

    private static Player t;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player p = event.getPlayer();
        MainGUIListener.ReportChoice.remove(p);
        AcceptReportListener.inReport.remove(p);
        for (Map.Entry<Player, Player> map : AcceptReportListener.inReport.entrySet()) {
            if (map.getValue().equals(p)) {
                t = map.getKey();
            }

        }

    }

}
