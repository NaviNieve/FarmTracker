package com.farmtracker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FarmTrackerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FarmTrackerPlugin.class);
		RuneLite.main(args);
	}
}