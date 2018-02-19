package com.ecconia.rsisland.plugin.service.commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.ecconia.rsisland.plugin.service.AbstractCommand;
import com.ecconia.rsisland.plugin.service.ServicePlugin;

public class CommandStop extends AbstractCommand
{
	private final JavaPlugin plugin;
	
	public CommandStop(JavaPlugin plugin)
	{
		super(plugin, "stop");
		this.plugin = plugin;
	}

	@Override
	protected void exec(CommandSender sender, String[] args)
	{
		File reloadFile = new File(".restart");
		reloadFile.delete();
		
		sender.getServer().broadcastMessage(ServicePlugin.prefix + ChatColor.RED + sender.getName() + " triggered a server stop!");
		sender.getServer().broadcastMessage(ServicePlugin.prefix + ChatColor.RED + "Stop in 5 seconds.");
		
		sender.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
		{
			public void run()
			{
				sender.getServer().shutdown();
			}
		}, 100);
	}
}
