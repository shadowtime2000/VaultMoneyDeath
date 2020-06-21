package en.shadowtime2000.vaultmoneydeath.listeners;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private double multiplier;
    private Economy economy;

    public PlayerDeathListener(FileConfiguration config, Economy economy) {
        this.multiplier = config.getDouble("multiplier");
        this.economy = economy;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        double currentMoney = this.economy.getBalance(event.getEntity());
        double newMoney = currentMoney*this.multiplier;
        this.economy.withdrawPlayer(event.getEntity(), currentMoney);
        this.economy.depositPlayer(event.getEntity(), newMoney);
    }
}
