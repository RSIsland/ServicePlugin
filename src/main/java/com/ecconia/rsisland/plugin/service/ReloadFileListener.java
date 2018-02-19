package com.ecconia.rsisland.plugin.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;

public class ReloadFileListener
{
	public ReloadFileListener(ServicePlugin plugin)
	{
		Thread t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					Path path = Paths.get("");
					
					WatchService watcher = path.getFileSystem().newWatchService();
					
					path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
					
					boolean captured = true;
					
					while (captured)
					{
						WatchKey key = watcher.take();
						
						for (WatchEvent<?> event : key.pollEvents())
						{
							String filename = event.context().toString();
							if("cmd-insert".equals(filename))
							{
								//Wrapper to get back to main Thread.
								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin.getServer().getPluginManager().getPlugin("GCC"), new Runnable()
								{
									public void run()
									{
										final Server server = plugin.getServer();
										
										plugin.getServer().broadcastMessage(ServicePlugin.prefix + ChatColor.YELLOW + "Started plugin reload. (External)");
										Bukkit.reload();
										
										//Delayed execution, else it won't show ingame.
										server.getScheduler().scheduleSyncDelayedTask(server.getPluginManager().getPlugin("GCC"), new Runnable()
										{
											public void run()
											{
												server.broadcastMessage(ServicePlugin.prefix + ChatColor.YELLOW + "Reload done.");
											}
										}, 1);
									}
								}, 1);
								captured = false;
								break;
							}
						}
						
						key.reset();
					}
					watcher.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
}
