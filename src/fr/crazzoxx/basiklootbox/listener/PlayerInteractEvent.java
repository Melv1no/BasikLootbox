package fr.crazzoxx.basiklootbox.listener;

import fr.crazzoxx.basiklootbox.BasikLootbox;
import fr.crazzoxx.basiklootbox.items.Lootbox;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class PlayerInteractEvent implements Listener {
    private BasikLootbox instance;
    public PlayerInteractEvent()
    {
        instance = BasikLootbox.getInstance();
    }

    @EventHandler
    public void onPlayerInteractEvent(org.bukkit.event.player.PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand().getType() != Material.AIR){
            Player player = e.getPlayer();
            if(player.getItemInHand().getItemMeta().equals(new Lootbox().getLootbox().getItemMeta())){
                if(e.getBlockFace() == BlockFace.UP){
                Location block = e.getClickedBlock().getLocation();
                block.setY(block.getBlockY() + 1);
                if(e.useInteractedBlock() == Event.Result.ALLOW){
                    player.sendMessage(instance.getConfStr("message.player-open-box"));
                    OpenLootbox(block, player);
                    e.setCancelled(true);
                    player.getInventory().remove(new Lootbox().getLootbox(1));


                }
            }else{
                    e.setCancelled(true);
                }

            }
        }
    }

    public void OpenLootbox(Location location, Player player){
        location.setY(location.getBlockY() + 3);
        Random rand = new Random();
        HashMap<Double, ItemStack> loots = new HashMap<>();





        for(String lootSection : instance.getConfig().getConfigurationSection("lootbox.rewards").getKeys(false)){
            try{
                ItemStack loot = new ItemStack(Material.getMaterial(instance.getConfig().getString("lootbox.rewards." + lootSection + ".item-name")));
                ItemMeta lootMeta = loot.getItemMeta();
                List<String> lore = new ArrayList<>();
                for(String lores : instance.getConfig().getStringList("lootbox.rewards."+lootSection+".lore")){
                    lore.add(lores.replace("&","§"));
                }
                lootMeta.setLore(lore);
                lootMeta.setDisplayName(instance.getConfStr("lootbox.rewards."+lootSection+".name"));
                loot.setItemMeta(lootMeta);

                loots.put(Double.valueOf(instance.getConfStr("lootbox.rewards."+lootSection+".percent")), loot);

            }catch (Exception e){
                    player.sendMessage(e.getStackTrace().toString());
            }
        }
            for(Map.Entry<Double, ItemStack> entry : loots.entrySet()){
                        player.getWorld().dropItem(location, entry.getValue());

            }

        }









    }


