package net.noscape.project.damageholo.managers;

import net.noscape.project.damageholo.*;
import net.noscape.project.damageholo.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.*;
import org.bukkit.scoreboard.*;

import java.util.*;

public class HologramManager {

    private final JavaPlugin plugin;

    private final Location location;
    private final String text;
    private final int seconds;
    private final int min;
    private final int max;
    private final boolean particlesEnabled;
    private final String particle;

    private Scoreboard board;
    private Objective obj;

    public HologramManager(JavaPlugin plugin, Location location, String text, Entity en) {
        this.plugin = plugin;
        this.location = location;
        this.text = text;
        this.seconds = plugin.getConfig().getInt("dh.interval");
        this.min = plugin.getConfig().getInt("dh.minHeight");
        this.max = plugin.getConfig().getInt("dh.maxHeight");
        this.particlesEnabled = plugin.getConfig().getBoolean("dh.particle.enable");
        this.particle = plugin.getConfig().getString("dh.particle.value");

        if (!DamageHolo.getVersion().isMc1_8() ||!DamageHolo.getVersion().isMc1_9() ||!DamageHolo.getVersion().isMc1_10()) {
            createHolo(en);
        } else {
            this.board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
            this.obj = board.getObjective("di-health");

            updateHealth((LivingEntity) en);
        }
    }

    public void createHolo(Entity en) {
        if (!DamageHolo.getVersion().isMc1_8() ||!DamageHolo.getVersion().isMc1_9() ||!DamageHolo.getVersion().isMc1_10()) {
            AreaEffectCloud di = (AreaEffectCloud) Objects.requireNonNull(en.getLocation().getWorld()).spawnEntity(en.getLocation().add(0.0D, getRandomDouble(min, max), 0.0D), EntityType.AREA_EFFECT_CLOUD);

            if (particlesEnabled) {
                di.setParticle(Particle.valueOf(particle.toUpperCase()));
            }

            di.setRadius(0.0F);
            di.setCustomName(Utils.applyFormat(text));
            di.setCustomNameVisible(true);
            di.setWaitTime(0);
            di.setDuration(20 * seconds);
        }
    }

    public void updateHealth(LivingEntity en) {
        if (obj != null)
            obj.getScore(en.getName()).setScore((int) en.getHealth());
    }

    public void updateOldHolo() {
        if (obj == null) {
            //Init if not exists
            try {
                obj = board.registerNewObjective("di-health", "health");
            } catch (NullPointerException exc) {
                //Depreciation
                obj = board.registerNewObjective("di-health", "health", Utils.applyFormat(text));
            }
            obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }

        obj.setDisplayName(ChatColor.translateAlternateColorCodes('&', Utils.applyFormat(text)));
        Bukkit.getOnlinePlayers().forEach(this::updateHealth);
    }

    public static double getRandomDouble(double min, double max) {
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        return randomValue;
    }

}
