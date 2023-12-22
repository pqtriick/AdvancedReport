package de.pqtriick.advancedreport.commands.player;

import de.pqtriick.advancedreport.files.Config;
import de.pqtriick.advancedreport.files.configs.MessageConfig;
import de.pqtriick.advancedreport.util.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.Objects;

import static de.pqtriick.advancedreport.files.configs.ReportConfig.ReportFile;

/**
 * @author pqtriick_
 * @created 17:13, 05.12.2023
 */

public class Report implements CommandExecutor {


    public static Inventory ReportUI = Bukkit.createInventory(null, 9*4, ChatColor.of(new Color(227, 94, 94, 255)) + "ᴄʀᴇᴀᴛᴇ ʀᴇᴘᴏʀᴛ");
    private int slot;
    private String name;
    private String lore;
    private Material material;

    public Report() {
        for (int i = 0; i < ReportUI.getSize(); i++) {
            ReportUI.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("").setLore("").build());
        }
        for (String values : Config.getConfiguration(ReportFile).getConfigurationSection("UIID").getKeys(false)) {
            slot = Integer.parseInt(values);
            name = Config.getConfiguration(ReportFile).getString("UIID." + slot + ".name");
            lore = Config.getConfiguration(ReportFile).getString("UIID." + slot + ".lore");
            material = Material.matchMaterial(Objects.requireNonNull(Config.getConfiguration(ReportFile).getString("UIID." + slot + ".material")));
            ReportUI.setItem(slot, new ItemBuilder(material).setName(name).setLore(lore).build());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        p.openInventory(ReportUI);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);


        return false;
    }
}
