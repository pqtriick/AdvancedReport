package de.pqtriick.advancedreport.listener.inventory;

import de.pqtriick.advancedreport.files.Config;
import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.files.configs.ReportConfig;
import de.pqtriick.advancedreport.util.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.awt.*;
import java.util.HashMap;

/**
 * @author pqtriick_
 * @created 18:29, 07.12.2023
 */

public class MainGUIListener implements Listener {

    private int slot;
    private String name;

    public static HashMap<Player, String> ReportChoice = new HashMap<>();
    private static String SENTREPORT1 = MessageConfig.MessageConfig.getString("messages.sentreport1");
    private static String SENTREPORT2 = MessageConfig.MessageConfig.getString("messages.sentreport2");
    private static String NEWREPORT = MessageConfig.MessageConfig.getString("messages.newreport");
    private static String ALREADYSENT = MessageConfig.MessageConfig.getString("messages.alreadysentreport");
    private static String PREFIX = MessageConfig.MessageConfig.getString("messages.prefix");

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        PREFIX = PREFIX.replace("&", "§");
        SENTREPORT1 = SENTREPORT1.replace("&", "§");
        SENTREPORT2 = SENTREPORT2.replace("&", "§");
        NEWREPORT = NEWREPORT.replace("&", "§");
        ALREADYSENT = ALREADYSENT.replace("&", "§");

        Player p = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(ChatColor.of(new Color(227, 94, 94, 255)) + "ᴄʀᴇᴀᴛᴇ ʀᴇᴘᴏʀᴛ")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getType() != Material.BLACK_STAINED_GLASS_PANE) {
                if (!ReportChoice.containsKey(p)) {
                    if (event.getCurrentItem() != null) {
                        slot = event.getSlot();
                        name = Config.getConfiguration(ReportConfig.ReportFile).getString("UIID." + slot + ".name");
                        ReportChoice.put(p, name);
                        p.closeInventory();
                        p.sendMessage(PREFIX + SENTREPORT1);
                        p.sendMessage(PREFIX + SENTREPORT2);
                        NEWREPORT = NEWREPORT.replace("%name%", p.getName());
                        NEWREPORT = NEWREPORT.replace("%reporttype%", name);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("AR.seereport")) {
                                all.sendMessage(PREFIX + NEWREPORT);
                                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0);
                            }
                        }
                    }
                } else {
                    p.closeInventory();
                    p.sendMessage(PREFIX + ALREADYSENT);
                    p.sendMessage(PREFIX + SENTREPORT2);
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 2);
                }
            }
        }
    }
}


