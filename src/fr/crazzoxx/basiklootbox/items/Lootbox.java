package fr.crazzoxx.basiklootbox.items;

import fr.crazzoxx.basiklootbox.BasikLootbox;
import fr.crazzoxx.basiklootbox.items.configuration.ConfigurationParser;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Lootbox {

    private BasikLootbox instance;
    public Lootbox(){
        instance = BasikLootbox.getInstance();
    }

    private ConfigurationParser getConfig;
    private ItemStack Lootbox(){
        getConfig = new ConfigurationParser(instance.getConfig());
        ItemStack lootbox = new ItemStack(Material.getMaterial(getConfig.lootbox_item()));
        ItemMeta lootbox_meta = getConfig.ApplyMeta(lootbox.getItemMeta());
        lootbox.setItemMeta(lootbox_meta);

        return lootbox;
    }
    public ItemStack getLootbox() {
        return Lootbox();
    }
}
