package com.ecconia.rsisland.plugin.service.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.ecconia.rsisland.plugin.service.AbstractCommand;
import com.ecconia.rsisland.plugin.service.ServicePlugin;

public class CommandRestart extends AbstractCommand
{
	private final JavaPlugin plugin;
	
	public CommandRestart(JavaPlugin plugin)
	{
		super(plugin, "restart");
		this.plugin = plugin;
	}

	@Override
	protected void exec(CommandSender sender, String[] args)
	{
		sender.getServer().broadcastMessage(ServicePlugin.prefix + ChatColor.RED + sender.getName() + " triggered a restart!");
		sender.getServer().broadcastMessage(ServicePlugin.prefix + ChatColor.RED + "Restart in 5 seconds.");
		
		sender.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
		{
			public void run()
			{
				sender.getServer().shutdown();
			}
		}, 100);
	}
}
