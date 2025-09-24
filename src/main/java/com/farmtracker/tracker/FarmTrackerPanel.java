package com.farmtracker.tracker;

import com.farmtracker.FarmTrackerConfig;
import com.farmtracker.FarmTrackerPlugin;
import com.farmtracker.TrackerPanel;
import net.runelite.api.ItemID;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.components.PluginErrorPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FarmTrackerPanel extends JPanel {
    // When there is no data
    private final PluginErrorPanel errorPanel = new PluginErrorPanel();
    //
    private final JPanel layoutPanel;
    private TopNavPanel trackerNav;
    private FarmTrackerButtonPanel trackerButtons;
    private FarmTrackerTablePanel trackerTable;
    private JPanel trackerGraph;
    //
    private final FarmTrackerPlugin plugin;
    private final ItemManager itemManager;
    private final TrackerPanel parent;
    private final FarmTrackerConfig config;
    //
    private int CurrentMainPanel;
    private int CurrentPanel = 0;

    public FarmTrackerPanel(final FarmTrackerPlugin plugin, final TrackerPanel parent, final ItemManager itemManager, final FarmTrackerConfig config, int CurrentMainPanel)
    {
        this.itemManager = itemManager;
        this.plugin = plugin;
        this.parent = parent;
        this.config = config;
        this.CurrentMainPanel = CurrentMainPanel;

        setBorder(new EmptyBorder(6, 6, 6, 6));
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setLayout(new BorderLayout());

        layoutPanel = new JPanel();
        layoutPanel.setLayout(new BoxLayout(layoutPanel, BoxLayout.Y_AXIS));
        add(layoutPanel, BorderLayout.NORTH);

        trackerNav = new TopNavPanel(this, CurrentMainPanel);
        trackerButtons = new FarmTrackerButtonPanel(this, this.itemManager, CurrentPanel);
        trackerTable = new FarmTrackerTablePanel(CurrentPanel);
        //trackerGraph = buildOverallGraph();

        layoutPanel.add(trackerNav);
        layoutPanel.add(trackerButtons);
        layoutPanel.add(trackerTable);
        //layoutPanel.add(overallGraph);

        // Add error pane
        //errorPanel.setContent("Farm Tracker", "You have not farmed yet.");
        //add(errorPanel);

        //Testing (TO DELETE)
        //FarmTrackerData dat1 = new FarmTrackerData(1, "Onion", ItemID.ONION, 5, 87, 0, 0, "9:39 PM", "9/23/2025", 9, 1, plugin, itemManager, config);
        //FarmTrackerData dat2 = new FarmTrackerData(3, "Grimy ranarr weed", ItemID.GRIMY_RANARR_WEED, 45, plugin, itemManager, config);
        //trackerTable.AddData(dat1);
        //trackerTable.AddData(dat2);
    }
    public void ChangeTab(int UID) {
        CurrentPanel = UID;
        //Change Data
        trackerTable.ChangeData(UID);
    }
    public void ChangeMain(int UID) {
        CurrentMainPanel = UID;
        //Tell Parent to Change Data
    }

}
