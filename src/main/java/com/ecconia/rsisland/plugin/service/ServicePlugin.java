package com.ecconia.rsisland.plugin.service;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.ecconia.rsisland.plugin.service.commands.CommandReload;
import com.ecconia.rsisland.plugin.service.commands.CommandRestart;
import com.ecconia.rsisland.plugin.service.commands.CommandStop;

public class ServicePlugin extends JavaPlugin
{
	public static final String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Service" + ChatColor.WHITE + "] ";

	@Override
	public void onEnable()
	{
		new CommandStop(this);
		new CommandRestart(this);
		new CommandReload(this);
		
		new ReloadFileListener(this);
	}
}
