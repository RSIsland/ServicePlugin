package com.ecconia.rsisland.plugin.service;

import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCommand implements CommandExecutor, TabCompleter
{
	protected final String permission;
	
	public AbstractCommand(JavaPlugin plugin, String name)
	{
		permission = "service." + name;
		plugin.getCommand(name).setPermission(permission);
		plugin.getCommand(name).setPermissionMessage(ServicePlugin.prefix + ChatColor.RED + "Yep, git gud and get perms.");
		plugin.getCommand(name).setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		exec(sender, args);

		return true;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		return Collections.emptyList();
	}
	
	protected abstract void exec(CommandSender sender, String[] args);
}
