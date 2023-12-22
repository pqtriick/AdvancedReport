package de.pqtriick.advancedreport.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * @author pqtriick_
 * @created 17:38, 04.12.2023
 */

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        itemStack = new ItemStack(material, 1);
        itemMeta = itemStack.getItemMeta();
    }
    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(name);
        return this;

    }
    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;

    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
