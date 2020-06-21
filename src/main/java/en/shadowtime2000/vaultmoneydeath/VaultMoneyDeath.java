package en.shadowtime2000.vaultmoneydeath;

import en.shadowtime2000.vaultmoneydeath.listeners.PlayerDeathListener;
import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VaultMoneyDeath extends JavaPlugin {

    private Economy economy;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        if (!setupEconomy()) {
            getLogger().severe("Vault plugin is not installed or Vault supported economy is not installed!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(getConfig(), this.economy), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        this.economy = rsp.getProvider();
        return true;
    }
}
