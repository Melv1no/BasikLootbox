package fr.crazzoxx.basiklootbox;

import fr.crazzoxx.basiklootbox.command.CBasikLootbox;
import fr.crazzoxx.basiklootbox.listener.PlayerInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class BasikLootbox extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if(!getConfig().getBoolean("plugin.enable")){
            log.warning("config.yml - plugin.enable = false");
            getPluginLoader().disablePlugin(this);
        }
        instance = this;
        getCommand("basiklootbox").setExecutor(new CBasikLootbox());
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEvent(), this);
    }
    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Logger log = Logger.getLogger("Minecraft");

    private static BasikLootbox instance;
    public static BasikLootbox getInstance(){
        return instance;
    }

    public String getConfStr(String confPath){
        return this.getConfig().getString(confPath).replace("{prefix}", getConfig().getString("plugin.prefix")).replace("&","§");
    }

}
