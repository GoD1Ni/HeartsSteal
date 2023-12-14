package lorens.dx_heartssteal;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;


public class Events implements Listener {
    @EventHandler
    public void onDeath(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (event.getDamager() instanceof Player) {
                Player playerKill = (Player) event.getDamager();

                if (player.getHealth() <= event.getFinalDamage()) {

                    if (player.getInventory().getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING || player.getInventory().getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
                        player.spawnParticle(Particle.HEART, player.getLocation(), 1);

                    } else if (player.getMaxHealth() == 6) {

                        playerKill.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cУ этого игрока не осталось сердец!"));

                    } else if (playerKill.getMaxHealth() < 40) {

                        player.setMaxHealth(player.getMaxHealth() - 2);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cТы потерял сердце!"));

                        Location initLocation = player.getLocation().clone();
                        heartParticle(playerKill, initLocation);

                        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', player.getName() + " &6" + player.getMaxHealth() + "&c❤"));
                        playerKill.setPlayerListName(ChatColor.translateAlternateColorCodes('&', playerKill.getName() + " &6" + playerKill.getMaxHealth() + "&c❤"));
                    } else {
                        playerKill.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cУ тебя максимальное количество сердец!"));
                    }

                }
            }
        }
    }

    public void heartParticle(Player playerDeath, Location initLocation) {
        Random random=new Random();
        new BukkitRunnable() {
            int ctr = 0;

            @Override
            public void run() {
                playerDeath.getWorld().spawnParticle(Particle.HEART, initLocation.clone().add(new Vector(0, 0.5 * ctr, 0)), 1);

                ctr = ctr + 1;

                if (ctr > 5) {

                    ctr = 0;
                    playerDeath.spawnParticle(Particle.TOTEM, initLocation, 1);
                    playerDeath.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Ты украл сердце!"));
                    playerDeath.setMaxHealth(playerDeath.getMaxHealth() + 2);
                    cancel();
                }
            }
        }.runTaskTimer(Dx_HeartsSteal.getInstance(),0L,10L);
    }
}
