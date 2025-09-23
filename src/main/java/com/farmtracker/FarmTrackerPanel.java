package com.farmtracker;

import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FarmTrackerPanel extends PluginPanel
{
    private final ItemManager itemManager;
    private final FarmTrackerPlugin plugin;
    private final FarmTrackerConfig config;

    FarmTrackerPanel(final FarmTrackerPlugin plugin, final ItemManager itemManager, final FarmTrackerConfig config)
    {
        this.itemManager = itemManager;
        this.plugin = plugin;
        this.config = config;

        setBorder(new EmptyBorder(6, 6, 6, 6));
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setLayout(new BorderLayout());


    }
}
