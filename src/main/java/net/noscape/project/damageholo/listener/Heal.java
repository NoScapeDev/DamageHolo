package net.noscape.project.damageholo.listener;

import net.noscape.project.damageholo.*;
import net.noscape.project.damageholo.managers.*;
import net.noscape.project.damageholo.utils.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

import java.text.*;

public class Heal implements Listener {

    private final DamageHolo holo = DamageHolo.getPlugin(DamageHolo.class);

    @EventHandler
    public void onDamage(EntityRegainHealthEvent e) {
        if (e.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED) {
            if (holo.getConfig().getBoolean("dh.heal.enable")) {
                if (e.getEntity() instanceof Player) {
                    if (holo.getConfig().getBoolean("dh.heal.players")) {
                        Player player = (Player) e.getEntity();

                        double value = e.getAmount();
                        String format = holo.getConfig().getString("dh.heal.hologram");
                        assert format != null;
                        format = format.replaceAll("%value%", getRoundOffValue(value));
                        format = format.replaceAll("%entity%", player.getName());

                        new HologramManager(holo, player.getLocation(), Utils.applyFormat(format), player);
                    }
                } else {
                    if (holo.getConfig().getBoolean("dh.heal.mobs")) {
                        Entity entity = e.getEntity();

                        double value = e.getAmount();
                        String format = holo.getConfig().getString("dh.heal.hologram");
                        assert format != null;
                        format = format.replaceAll("%value%", getRoundOffValue(value));
                        format = format.replaceAll("%entity%", entity.getName());

                        new HologramManager(holo, entity.getLocation(), Utils.applyFormat(format), entity);
                    }
                }
            }
        }
    }

    public static String getRoundOffValue(double value){
        DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
        return df.format(value);
    }
}
