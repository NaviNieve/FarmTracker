package com.farmtracker;

import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.PluginErrorPanel;
import net.runelite.client.util.AsyncBufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * To-Fix:
 * Quicker Switches >> Dynamic Loading?
 */

public class FarmTrackerPanel extends PluginPanel
{
    // When there is no data
    private final PluginErrorPanel errorPanel = new PluginErrorPanel();
    //
    private final JPanel layoutPanel;
    private JPanel overallButtons;
    private JPanel overallPanel;
    private JPanel overallGraph;
    private JPanel TableLabelPanel;
    private JLabel TableLabel;
    private JTable Table;
    //
    private final ItemManager itemManager;
    private final FarmTrackerPlugin plugin;
    private final FarmTrackerConfig config;
    //
    private int PanelNumber = 0;

    FarmTrackerPanel(final FarmTrackerPlugin plugin, final ItemManager itemManager, final FarmTrackerConfig config)
    {
        this.itemManager = itemManager;
        this.plugin = plugin;
        this.config = config;

        setBorder(new EmptyBorder(6, 6, 6, 6));
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setLayout(new BorderLayout());

        layoutPanel = new JPanel();
        layoutPanel.setLayout(new BoxLayout(layoutPanel, BoxLayout.Y_AXIS));
        add(layoutPanel, BorderLayout.NORTH);

        TableLabelPanel = buildMainLabel();
        overallButtons = buildPanelButtons();
        overallPanel = buildOverallTable();
        overallGraph = buildOverallGraph();

        //layoutPanel.add(actionsPanel);
        layoutPanel.add(TableLabelPanel);
        layoutPanel.add(overallButtons);
        layoutPanel.add(overallPanel);
        layoutPanel.add(overallGraph);

        // Add error pane
        //errorPanel.setContent("Farm Tracker", "You have not farmed yet.");
        //add(errorPanel);
    }

    private JPanel buildOverallTable() {
        final JPanel overallPanel = new JPanel();
        overallPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 0, 0, 0, ColorScheme.DARK_GRAY_COLOR),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        overallPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        overallPanel.setLayout(new BorderLayout());
        overallPanel.setVisible(true);

        String[] Columns = {"Run #", "Item", "Quantity", "Price", "Total", "Diseased", "Dead", "Time", "Date", "Used", "Seed #"};

        DefaultTableModel model = new DefaultTableModel(Columns, 0);
        Table = new JTable(model);
        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane TableScrollPane = new JScrollPane(Table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        Table.getColumnModel().getColumn(0).setPreferredWidth(45);
        Table.getColumnModel().getColumn(1).setPreferredWidth(45);
        Table.getColumnModel().getColumn(2).setPreferredWidth(55);
        Table.getColumnModel().getColumn(3).setPreferredWidth(45);
        Table.getColumnModel().getColumn(4).setPreferredWidth(45);
        Table.getColumnModel().getColumn(5).setPreferredWidth(55);
        Table.getColumnModel().getColumn(6).setPreferredWidth(45);
        Table.getColumnModel().getColumn(7).setPreferredWidth(45);
        Table.getColumnModel().getColumn(8).setPreferredWidth(45);
        Table.getColumnModel().getColumn(9).setPreferredWidth(45);
        Table.getColumnModel().getColumn(10).setPreferredWidth(50);

        overallPanel.add(TableScrollPane, BorderLayout.CENTER);

        return overallPanel;
    }

    private JPanel buildOverallGraph() {
        final JPanel overallPanel = new JPanel();
        overallPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 0, 0, 0, ColorScheme.DARK_GRAY_COLOR),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        overallPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        overallPanel.setLayout(new BorderLayout());
        overallPanel.setVisible(true);

        //Line chart

        return overallPanel;
    }

    private JPanel buildMainLabel() {
        final JPanel overallInfo = new JPanel();
        overallInfo.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        overallInfo.setBorder(new EmptyBorder(2, 10, 2, 0));
        final JPanel topTitlePanel = new JPanel(new BorderLayout());
        topTitlePanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        TableLabel = new JLabel();
        switch (PanelNumber) {
            case 0: {
                TableLabel.setText("Overall Table");
                break;
            }
            case 1: {
                TableLabel.setText("Allotments Table");
                break;
            }
            case 2: {
                TableLabel.setText("Flowers Table");
                break;
            }
            case 3: {
                TableLabel.setText("Herbs Table");
                break;
            }
            case 4: {
                TableLabel.setText("Trees Table");
                break;
            }
            case 5: {
                TableLabel.setText("Fruit Trees Table");
                break;
            }
            case 6: {
                TableLabel.setText("Hops Table");
                break;
            }
            case 7: {
                TableLabel.setText("Bushes Table");
                break;
            }
            case 8: {
                TableLabel.setText("Grape Table");
                break;
            }
            case 9: {
                TableLabel.setText("Special Table");
                break;
            }
            default: {
                TableLabel.setText("UNKNOWN ):");
                break;
            }
        }
        TableLabel.setFont(FontManager.getRunescapeBoldFont());
        TableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TableLabel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        topTitlePanel.add(TableLabel, BorderLayout.CENTER);
        overallInfo.add(topTitlePanel);
        return overallInfo;
    }
    private JPanel buildPanelButtons() {
        JPanel MainHolder = new JPanel(new BorderLayout());

        JPanel ButtonsUpper = new JPanel();
        ButtonsUpper.setLayout(new BoxLayout(ButtonsUpper, BoxLayout.X_AXIS));
        ButtonsUpper.add(Box.createHorizontalGlue());
        JPanel ButtonsLower = new JPanel();
        ButtonsLower.setLayout(new BoxLayout(ButtonsLower, BoxLayout.X_AXIS));
        ButtonsLower.add(Box.createHorizontalGlue());

        int cur = 0;
        int max = 5;

        for (Tab value : Tab.values()) {
            JPanel holder = new JPanel();
            holder.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            holder.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, ColorScheme.DARK_GRAY_COLOR));

            int finalCur = cur;
            holder.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ChangeData(finalCur);
                }
                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {
                    holder.setBackground(ColorScheme.DARK_GRAY_COLOR);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    holder.setBackground(ColorScheme.DARKER_GRAY_COLOR);
                }
            });
            int item = value.itemID;
            JLabel iconLabel = new JLabel();

            AsyncBufferedImage icon = itemManager.getImage(item);
            icon.onLoaded(() ->
            {
                BufferedImage subIcon = icon.getSubimage(0, 0, 32, 32);
                iconLabel.setIcon(new ImageIcon(subIcon.getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
            });
            if(cur == PanelNumber) {
                iconLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorScheme.BRAND_ORANGE));
            } else {
                iconLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorScheme.DARK_GRAY_COLOR));
            }

            holder.add(iconLabel);

            if(cur < max) ButtonsUpper.add(holder);
            else ButtonsLower.add(holder);
            cur++;
        }

        ButtonsUpper.add(Box.createHorizontalGlue());
        ButtonsLower.add(Box.createHorizontalGlue());

        MainHolder.add(ButtonsUpper, BorderLayout.NORTH);
        MainHolder.add(ButtonsLower, BorderLayout.SOUTH);

        return MainHolder;
    }

    private void ChangeData(int tab) {
        //Label
        PanelNumber = tab;
        //Remove Last
        layoutPanel.remove(TableLabelPanel);
        layoutPanel.remove(overallButtons);
        layoutPanel.remove(overallPanel);
        layoutPanel.remove(overallGraph);
        //Refresh/Build Data
        TableLabelPanel = buildMainLabel();
        overallButtons = buildPanelButtons();
        overallPanel = buildOverallTable();
        overallGraph = buildOverallGraph();
        //Add New
        layoutPanel.add(TableLabelPanel);
        layoutPanel.add(overallButtons);;
        layoutPanel.add(overallPanel);
        layoutPanel.add(overallGraph);
    }
}
