package net.noscape.project.damageholo.listener;

import net.noscape.project.damageholo.*;
import net.noscape.project.damageholo.managers.*;
import net.noscape.project.damageholo.utils.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;

import java.text.*;
import java.util.*;

public class Damage implements Listener {

    private final DamageHolo holo = DamageHolo.getPlugin(DamageHolo.class);

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (holo.getConfig().getBoolean("dh.damage.enable")) {
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (e.getEntity() instanceof Player) {
                    if (holo.getConfig().getBoolean("dh.damage.players")) {
                        Player player = (Player) e.getEntity();
                        double damage = e.getFinalDamage();

                        if (damage < 1)
                            return;
                        if (!(e.getEntity() instanceof LivingEntity))
                            return;

                        String format = holo.getConfig().getString("dh.damage.hologram");
                        assert format != null;
                        format = format.replaceAll("%value%", getRoundOffValue(damage));
                        format = format.replaceAll("%entity%", player.getName());
                        format = format.replaceAll("%damager%", damager.getName());

                        new HologramManager(holo, player.getLocation(), Utils.applyFormat(format), player);
                    }
                } else {
                    if (holo.getConfig().getBoolean("dh.damage.mobs")) {
                        Entity entity = e.getEntity();

                        double damage = e.getFinalDamage();

                        if (damage < 1)
                            return;
                        if (!(e.getEntity() instanceof LivingEntity))
                            return;

                        String format = holo.getConfig().getString("dh.damage.hologram");
                        assert format != null;
                        format = format.replaceAll("%value%", getRoundOffValue(damage));
                        format = format.replaceAll("%entity%", entity.getName());

                        new HologramManager(holo, entity.getLocation(), Utils.applyFormat(format), entity);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDmgOther(ProjectileHitEvent e) {
        if (e.getHitEntity() instanceof Player) {
            if (e.getEntity().getShooter() instanceof Player) {
                Player player = (Player) e.getEntity().getShooter();
                if (holo.getConfig().getBoolean("dh.damage.players")) {

                    double damage = Objects.requireNonNull(e.getHitEntity().getLastDamageCause()).getFinalDamage();

                    if (damage < 1)
                        return;
                    if (!(e.getEntity() instanceof LivingEntity))
                        return;

                    String format = holo.getConfig().getString("dh.damage.hologram");
                    assert format != null;
                    format = format.replaceAll("%value%", getRoundOffValue(damage));
                    format = format.replaceAll("%entity%", e.getHitEntity().getName());
                    format = format.replaceAll("%damager%", player.getName());

                    new HologramManager(holo, e.getHitEntity().getLocation(), Utils.applyFormat(format), e.getHitEntity());
                }
            }
        } else {
            if (e.getEntity().getShooter() instanceof Player) {
                Player player = (Player) e.getEntity().getShooter();
                if (holo.getConfig().getBoolean("dh.damage.players")) {

                    double damage = Objects.requireNonNull(Objects.requireNonNull(e.getHitEntity()).getLastDamageCause()).getFinalDamage();

                    if (damage < 1)
                        return;
                    if (!(e.getEntity() instanceof LivingEntity))
                        return;

                    String format = holo.getConfig().getString("dh.damage.hologram");
                    assert format != null;
                    format = format.replaceAll("%value%", getRoundOffValue(damage));
                    format = format.replaceAll("%entity%", e.getHitEntity().getName());
                    format = format.replaceAll("%damager%", player.getName());

                    new HologramManager(holo, e.getHitEntity().getLocation(), Utils.applyFormat(format), e.getHitEntity());
                }
            }
        }
    }

    public static String getRoundOffValue(double value){
        DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
        return df.format(value);
    }
}