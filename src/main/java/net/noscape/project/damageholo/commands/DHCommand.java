package net.noscape.project.damageholo.commands;

import net.noscape.project.damageholo.*;
import net.noscape.project.damageholo.utils.*;
import org.bukkit.command.*;
import org.bukkit.configuration.*;
import org.bukkit.entity.*;

import java.io.*;

public class DHCommand implements CommandExecutor {

    private final DamageHolo holo = DamageHolo.getPlugin(DamageHolo.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("damageholo")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        try {
                            holo.getConfig().load(holo.getDataFolder() + "/config.yml");
                            sender.sendMessage(Utils.applyFormat("&b&lDamageHolo &7Reloaded!"));
                        } catch (IOException | InvalidConfigurationException e) {
                            e.printStackTrace();
                        }
                    } else if (args[0].equalsIgnoreCase("version")) {
                        sender.sendMessage(Utils.applyFormat("&b&lDamageHolo &7You are using &b" + holo.getDescription().getVersion() + " &7of &b" + holo.getDescription().getName()));
                    }
                } else {
                    sender.sendMessage(Utils.applyFormat("&bDamageHolo Commands:"));
                    sender.sendMessage(Utils.applyFormat(""));
                    sender.sendMessage(Utils.applyFormat("&b/dh &7reload - reload config file."));
                    sender.sendMessage(Utils.applyFormat("&b/dh &7version - check what version the plugin is on"));
                    sender.sendMessage(Utils.applyFormat(""));
                    sender.sendMessage(Utils.applyFormat("&b<> &7Required. &b[] &7Option."));
                }
            }
            return true;
        } else {

            Player player = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("damageholo")) {
                if (player.hasPermission("dh.command")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            try {
                                holo.getConfig().load(holo.getDataFolder() + "/config.yml");
                                player.sendMessage(Utils.applyFormat("&b&lDamageHolo &7Reloaded!"));
                            } catch (IOException | InvalidConfigurationException e) {
                                e.printStackTrace();
                            }
                        } else if (args[0].equalsIgnoreCase("version")) {
                            player.sendMessage(Utils.applyFormat("&b&lDamageHolo &7You are using &b" + holo.getDescription().getVersion() + " &7of &b" + holo.getDescription().getName()));
                        }
                    } else {
                        player.sendMessage(Utils.applyFormat("&bDamageHolo Commands:"));
                        player.sendMessage(Utils.applyFormat(""));
                        player.sendMessage(Utils.applyFormat("&b/dh &7reload - reload config file."));
                        player.sendMessage(Utils.applyFormat("&b/dh &7version - check what version the plugin is on"));
                        player.sendMessage(Utils.applyFormat(""));
                        player.sendMessage(Utils.applyFormat("&b<> &7Required. &b[] &7Option."));
                    }
                }
            }
        }
        return false;
    }
}
