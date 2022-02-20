package fr.crazzoxx.basiklootbox.command;

import fr.crazzoxx.basiklootbox.BasikLootbox;
import fr.crazzoxx.basiklootbox.items.Lootbox;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CBasikLootbox implements CommandExecutor {

    private BasikLootbox instance;
    public CBasikLootbox(){
        this.instance = BasikLootbox.getInstance();
    }

    public static boolean isNumeric(String strNum) {
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String msg, String[] args) {
        if(args.length == 1 && args[0].toLowerCase() == "reload"){
            //reload
            return true;
        }else if(args.length == 2){
            Player target = Bukkit.getPlayer(args[0]);

            String box_count = args[1];
            if(!isNumeric(box_count)){
                return false;}
            int box_counts = Integer.parseInt(box_count);

            List<Player> playerList = new ArrayList<Player>();
            for (Player player : instance.getServer().getOnlinePlayers()){
                playerList.add(player);
            }
            if(!playerList.contains(target)) {
                commandSender.sendMessage(instance.getConfStr("message.admin-give-offline-player").replace("{player}", args[0]));
                return false;
            }

            GiveLootbox(commandSender, target, box_counts);
            return true;
        }else{
            commandSender.sendMessage(instance.getConfStr("plugin.usage"));
            return false;
        }
    }

    public void GiveLootbox(CommandSender sender, Player player, int box_count){
            sender.sendMessage(instance.getConfStr("message.admin-give-online-player").replace("{player}", player.getDisplayName()).replace("{box_count}", String.valueOf(box_count)));
            player.sendMessage(instance.getConfStr("message.player-receive-box").replace("{box_count}", String.valueOf(box_count)));
            Lootbox lootbox = new Lootbox();
            player.getInventory().addItem(lootbox.getLootbox());
    }
}
