package me.cageydinosaur.disableplugins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		this.saveDefaultConfig();

	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("disableplugins")) {
			if (!sender.hasPermission("disableplugins")) {
				sender.sendMessage(ChatColor.RED + "You cannot use this plugin!");

			} else if (sender.hasPermission("disableplugins")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Usage:");
					sender.sendMessage(ChatColor.RED + "/diableplugins all");
					sender.sendMessage(ChatColor.RED + "/disableplugins reload");
				}

				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("all")) {
						sender.sendMessage(ChatColor.GREEN + "Disabling Plugins");
						for (String i : this.getConfig().getStringList("disabledplugins")) {
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
									"plugman disable " + i);
							sender.sendMessage(ChatColor.GREEN + "Disabled " + i);
						}
						return true;

					} else if (args[0].equalsIgnoreCase("reload")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
								this.getConfig().getString("reload_message")));
						this.reloadConfig();
						return true;
					}
				}
			}
		}
		return false;
	}
}