package lorens.dx_heartssteal;

import org.bukkit.plugin.java.JavaPlugin;

public final class Dx_HeartsSteal extends JavaPlugin {
    private static Dx_HeartsSteal instance;

    public static Dx_HeartsSteal getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        instance=this;
        getServer().getPluginManager().registerEvents(new Events(),this);
        getCommand("Hearts").setExecutor(new CommandCMD());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
