package de.pqtriick.advancedreport.commands.team;

import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.listener.inventory.MainGUIListener;
import de.pqtriick.advancedreport.util.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.Map;

/**
 * @author pqtriick_
 * @created 21:39, 07.12.2023
 */

public class Viewreports implements CommandExecutor {

    public static Inventory Viewinventory;
    private static String GUIITEMTITLE = MessageConfig.MessageConfig.getString("messages.GUIItemTitle");
    private static String GUIItemlore3 = MessageConfig.MessageConfig.getString("messages.GUIItemlore3");

    public Viewreports() {
        Viewinventory = Bukkit.createInventory(null, 9*5, ChatColor.of(new Color(78, 133, 234, 255)) + "ᴏᴘᴇɴ ʀᴇᴘᴏʀᴛꜱ");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GUIITEMTITLE = GUIITEMTITLE.replace("&", "§");
        GUIItemlore3 = GUIItemlore3.replace("&", "§");
        Viewinventory.clear();
        int i = 0;
        Player p = (Player) sender;
        if (p.hasPermission("AR.seereport")) {
            for (Map.Entry<Player, String> map : MainGUIListener.ReportChoice.entrySet()) {
                Viewinventory.setItem(i, new ItemBuilder(Material.BAMBOO).setName(ChatColor.of(new Color(76, 199, 112)) + "Open Report").setLore(ChatColor.of(new Color(34, 196, 217)) + "Name: " +
                        ChatColor.of(new Color(34, 255, 255)) + map.getKey().getName(), ChatColor.of(new Color(34, 196, 217)) + "Report type: " +
                        ChatColor.of(new Color(34, 255, 255)) + map.getValue(), "", GUIItemlore3, "§7§l>> §cRightclick to delete").build());
                i++;
            }
            p.openInventory(Viewinventory);

        }
        return false;
    }
}
