package fr.crazzoxx.basiklootbox.items.configuration;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationParser {

    private org.bukkit.configuration.file.FileConfiguration config;
    public ConfigurationParser(org.bukkit.configuration.file.FileConfiguration  configurationFile){
    this.config = configurationFile;
    }
    public String getConfig(String confPath){
        return config.getString(confPath).replace("&","§");
    }

    public String lootbox_item(){return getConfig("lootbox.item-name");}


    public ItemMeta ApplyMeta(ItemMeta itemMeta){

        List<String> lore = new ArrayList<>();
        for(String lores : config.getStringList("lootbox.lores")){
            lore.add(lores.replace("&","§"));
        }
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(getConfig("lootbox.name"));
        if(config.getBoolean("lootbox.glow")){
            itemMeta.addEnchant(Enchantment.DURABILITY,1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return itemMeta;
    }
}
