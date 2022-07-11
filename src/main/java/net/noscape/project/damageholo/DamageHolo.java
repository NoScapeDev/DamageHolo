package net.noscape.project.damageholo;

import net.noscape.project.damageholo.commands.*;
import net.noscape.project.damageholo.listener.*;
import net.noscape.project.damageholo.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class DamageHolo extends JavaPlugin {

    private DamageHolo instance;
    private static Version version;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        saveDefaultConfig();

        version = new Version(this);

        getServer().getPluginManager().registerEvents(new HoloListener(), this);
        getServer().getPluginManager().registerEvents(new Damage(), this);
        getServer().getPluginManager().registerEvents(new Heal(), this);

        Objects.requireNonNull(getCommand("damageholo")).setExecutor(new DHCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DamageHolo getInstance() {
        return instance;
    }

    public static Version getVersion() {
        return version;
    }

}
