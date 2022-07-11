package net.noscape.project.damageholo.utils;

import net.noscape.project.damageholo.*;
import org.bukkit.*;

public class Version {

    private final boolean mc1_8;
    private final boolean mc1_9;
    private final boolean mc1_10;
    private final boolean mc1_16;
    private final boolean mc1_17;
    private final boolean mc1_18;
    private final boolean mc1_19;

    private DamageHolo holo;

    public Version(DamageHolo holo) {
        this.holo = holo;

        this.mc1_8 = Bukkit.getServer().getClass().getPackage().getName().contains("1.8");
        this.mc1_9 = Bukkit.getServer().getClass().getPackage().getName().contains("1.9");
        this.mc1_10 = Bukkit.getServer().getClass().getPackage().getName().contains("1.10");
        this.mc1_16 = Bukkit.getServer().getClass().getPackage().getName().contains("1.16");
        this.mc1_17 = Bukkit.getServer().getClass().getPackage().getName().contains("1.17");
        this.mc1_18 = Bukkit.getServer().getClass().getPackage().getName().contains("1.18");
        this.mc1_19 = Bukkit.getServer().getClass().getPackage().getName().contains("1.19");
    }

    public boolean isMc1_8() {
        return Bukkit.getServer().getVersion().contains("1.8");
    }

    public boolean isMc1_9() {
        return Bukkit.getServer().getVersion().contains("1.9");
    }

    public boolean isMc1_16() {
        return mc1_16;
    }

    public boolean isMc1_17() {
        return mc1_17;
    }

    public boolean isMc1_18() {
        return mc1_18;
    }

    public boolean isMc1_19() {
        return mc1_19;
    }

    public boolean isMc1_10() {
        return Bukkit.getServer().getVersion().contains("1.10");
    }
}
