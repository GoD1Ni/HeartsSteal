package lorens.dx_heartssteal;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class CommandCMD implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cВведите имя игрока!"));
            return true;
        } else {
            Player player1 = Bukkit.getPlayer(strings[1]);
            if (player1 == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cИгрок не в сети"));
                return true;
            } else if(strings[0].equalsIgnoreCase("addHeart")){
                if (!commandSender.isOp()) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cНедостаточно прав"));
                    return true;
                }else {
                    player1.setMaxHealth(player.getMaxHealth() + 2);
                    player1.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Вы чувствуете прилив сил!"));
                    player1.setPlayerListName(ChatColor.translateAlternateColorCodes('&',player1.getName()+" &6"+player1.getMaxHealth()+"&c❤"));
                    return true;
                }
            }else if (strings[0].equalsIgnoreCase("removeHeart")){
                if (!commandSender.isOp()) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cНедостаточно прав"));
                    return true;
                }else {
                    player1.setMaxHealth(player.getMaxHealth() - 2);
                    player1.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Вы чувствуете истощение!"));
                    player1.setPlayerListName(ChatColor.translateAlternateColorCodes('&',player1.getName()+" &6"+player1.getMaxHealth()+"&c❤"));
                    return true;
                }
            }else if(strings[0].equalsIgnoreCase("resetHearts")){
                if (!commandSender.isOp()) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cНедостаточно прав"));
                    return true;
                }else {
                    player1.setMaxHealth(20);
                    player1.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Вы чувствуете востановление!"));
                    player1.setPlayerListName(ChatColor.translateAlternateColorCodes('&',player1.getName()+" &6"+player1.getMaxHealth()+"&c❤"));
                    return true;
                }
            }else if (strings[0].equalsIgnoreCase("getHeartsPlayer")){
                if (!commandSender.isOp()) {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cНедостаточно прав"));
                    return true;
                }else{
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6У "+player1.getName() +" "+ player1.getMaxHealth()+" хп!"));
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length==1) {
            if (commandSender.isOp()) {
                return Arrays.asList("addHeart", "removeHeart","resetHearts","getHeartsPlayer");
            }
        }
        return null;
    }

}
