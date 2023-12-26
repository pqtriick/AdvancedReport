package de.pqtriick.advancedreport.listener.inventory;

import de.pqtriick.advancedreport.files.configs.MessageConfig;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author pqtriick_
 * @created 00:49, 09.12.2023
 */

public class ReportClickListener implements Listener {

    public static HashMap<Player, Player> inReport = new HashMap<>();
    //Reporter, Team

    private static String PREFIX = MessageConfig.MessageConfig.getString("messages.prefix");
    private static String REPORTACCEPTED = MessageConfig.MessageConfig.getString("messages.reportaccepted");
    private static String REPORTACCEPTMSG1 = MessageConfig.MessageConfig.getString("messages.reportacceptmsg1");
    private static String REPORTACCEPTMSG2 = MessageConfig.MessageConfig.getString("messages.reportacceptmsg2");
    private static String REPORTACCEPTMSG3 = MessageConfig.MessageConfig.getString("messages.reportacceptmsg3");
    private static String ALREADYINREPORT = MessageConfig.MessageConfig.getString("messages.alreadyinreport");


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        PREFIX = PREFIX.replace("&", "§");
        if (event.getView().getTitle().equals(ChatColor.of(new Color(78, 133, 234, 255)) + "ᴏᴘᴇɴ ʀᴇᴘᴏʀᴛꜱ")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.getClick() == ClickType.LEFT) {
                    if (!inReport.containsValue((Player) event.getWhoClicked())) {
                        List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                        String t = ChatColor.stripColor(lore.get(0));
                        String[] name = t.split(" ");
                        Player target = Bukkit.getPlayer(name[1]);
                        inReport.put((Player) event.getWhoClicked(), target);
                        Player p = (Player) event.getWhoClicked();
                        p.closeInventory();
                        REPORTACCEPTED = REPORTACCEPTED.replace("&", "§");
                        REPORTACCEPTED = REPORTACCEPTED.replace("%name%", target.getName());
                        REPORTACCEPTED = REPORTACCEPTED.replace("%reason%", MainGUIListener.ReportChoice.get(target));
                        REPORTACCEPTMSG1 = REPORTACCEPTMSG1.replace("&", "§");
                        REPORTACCEPTMSG2 = REPORTACCEPTMSG2.replace("&", "§");
                        REPORTACCEPTMSG2 = REPORTACCEPTMSG2.replace("%name%", p.getName());
                        REPORTACCEPTMSG3 = REPORTACCEPTMSG3.replace("&", "§");
                        p.sendMessage(PREFIX + REPORTACCEPTED);
                        target.sendMessage(PREFIX + REPORTACCEPTMSG1);
                        target.sendMessage("");
                        target.sendMessage(PREFIX + REPORTACCEPTMSG2);
                        target.sendMessage(PREFIX + REPORTACCEPTMSG3);
                        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                        MainGUIListener.ReportChoice.remove(target);
                    } else {
                        ALREADYINREPORT = ALREADYINREPORT.replace("&", "§");
                        event.getWhoClicked().sendMessage(PREFIX + ALREADYINREPORT);
                    }
                } else if (event.getClick() == ClickType.RIGHT) {
                    List<String> lore = event.getCurrentItem().getItemMeta().getLore();
                    String t = ChatColor.stripColor(lore.get(0));
                    String[] name = t.split(" ");
                    Player target = Bukkit.getPlayer(name[1]);
                    MainGUIListener.ReportChoice.remove(target);
                    target.sendMessage(PREFIX + "§6Your report got deleted!");
                    Player p = (Player) event.getWhoClicked();
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 2);
                }
            }
        }
    }
}
