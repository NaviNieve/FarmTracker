package com.farmtracker.tracker;

import com.farmtracker.util.Tab;
import net.runelite.client.game.ItemManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/*
To-Do:
Add Tooltips on buttons
 */

public class FarmTrackerButtonPanel extends JPanel {
    // Dynamic Data
    private int CurrentPanel;
    //
    ArrayList<FarmTrackerButton> buttons = new ArrayList<>();
    // Parents Data
    private final FarmTrackerPanel FarmTrackerPanel;
    private final ItemManager itemManager;

    FarmTrackerButtonPanel(FarmTrackerPanel Plugin, final ItemManager itemManager, int CurrentPanel) {
        this.FarmTrackerPanel = Plugin;
        this.itemManager = itemManager;
        this.CurrentPanel = CurrentPanel;
        this.setLayout(new BorderLayout());
        build();
    }

    private void build() {
        Tab[] Tabs = Tab.values();
        Tab[] TopTabs = Arrays.copyOfRange(Tabs, 0, 5);
        Tab[] BottomTabs = Arrays.copyOfRange(Tabs, 6, 11);

        JPanel ButtonsUpper = new JPanel();
        ButtonsUpper.setLayout(new BoxLayout(ButtonsUpper, BoxLayout.X_AXIS));
        ButtonsUpper.add(Box.createHorizontalGlue());
        JPanel ButtonsLower = new JPanel();
        ButtonsLower.setLayout(new BoxLayout(ButtonsLower, BoxLayout.X_AXIS));
        ButtonsLower.add(Box.createHorizontalGlue());

        for (Tab tab : TopTabs) {
            FarmTrackerButton btn = new FarmTrackerButton(this, itemManager, tab, CurrentPanel);
            ButtonsUpper.add(btn);
            buttons.add(btn);
        }
        for (Tab tab : BottomTabs) {
            FarmTrackerButton btn = new FarmTrackerButton(this, itemManager, tab, CurrentPanel);
            ButtonsLower.add(btn);
            buttons.add(btn);
        }

        ButtonsUpper.add(Box.createHorizontalGlue());
        ButtonsLower.add(Box.createHorizontalGlue());
        this.add(ButtonsUpper, BorderLayout.NORTH);
        this.add(ButtonsLower, BorderLayout.SOUTH);
    }
    public void ChangeTab(int tab) {
        CurrentPanel = tab;
        for (FarmTrackerButton button : buttons) {
            button.ChangeView(tab);
        }
        FarmTrackerPanel.ChangeTab(tab);
    }
}
