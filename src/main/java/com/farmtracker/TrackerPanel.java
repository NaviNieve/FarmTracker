package com.farmtracker;

import com.farmtracker.tracker.FarmTrackerPanel;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.PluginErrorPanel;

/**
 * To-Fix:
 * Quicker Switches >> Dynamic Loading?
 * To-Do:
 * Add Filter Option on each Type Table (Herb -> Filter: Ranarr, Elc..)
 * Add JTable Button to Add Row (Customize), Make Sure Table is Editable
 *
 * Plan:
 * //Create JPanel Class For TopNavButtons
 * //Create JPanel Class For MainButtonPanel
 * //Create JPanel Class For Main Panel -> Holds TopNavButtons, Label, Button Panel
 * //Create JTable Model/Data Class -> Columns, Filters, Elc.
 * //Create JPanel Class For JTable -> Holds Data, Easy Change Read/Write/Add/Remove Data, Save/Load
 * Create JPanel Class For LineGraph -> Holds Data, Easy Change Read/Write/Add/Remove Data
 *
 * Create JPanel Class For GEButtonPanel
 * Create JPanel Class For GE Panel -> Holds TopNavButtons, Label, Button Panel
 * (Reuse JTable Class, LineGraph Class, TopNavButton Class in GEPanel)
 */

public class TrackerPanel extends PluginPanel
{
    // When there is no data
    private final PluginErrorPanel errorPanel = new PluginErrorPanel();
    //
    private final ItemManager itemManager;
    private final FarmTrackerPlugin plugin;
    private final FarmTrackerConfig config;
    //
    private FarmTrackerPanel FarmPanel;
    //
    private int CurrentMainPanel = 0;

    TrackerPanel(final FarmTrackerPlugin plugin, final ItemManager itemManager, final FarmTrackerConfig config)
    {
        this.itemManager = itemManager;
        this.plugin = plugin;
        this.config = config;

        FarmPanel = new FarmTrackerPanel(plugin, this, itemManager, config, CurrentMainPanel);

        this.add(FarmPanel);
    }
}
