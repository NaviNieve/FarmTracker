package com.farmtracker.tracker;

import com.farmtracker.util.Tab;
import com.farmtracker.util.TableModel;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Implement/Tell Table to FILTER
 * Save/Load DATA
 */

public class FarmTrackerTablePanel extends JPanel {
    //Static Data
    private int CurrentPanel;
    //Javax Swing
    private JLabel Title;
    private JTable Table;
    private JTextField Filter;
    private JComboBox<String> FilterCombo;

    FarmTrackerTablePanel(int CurrentPanel) {
        this.CurrentPanel = CurrentPanel;

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(6, 6, 6, 6));

        build();
    }

    private void build() {
        JPanel top = new JPanel(new BorderLayout());
        top.add(buildTitle(), BorderLayout.NORTH);
        top.add(buildFilter(), BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH);

        String[] Columns = {"Run #", "Item", "Quantity", "Price", "Total", "Diseased", "Dead", "Time", "Date", "Used", "Seed #"};

        TableModel model = new TableModel(Columns);
        Table = new JTable(model);
        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(Table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(ColorScheme.DARKER_GRAY_COLOR);

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

        this.add(scrollPane, BorderLayout.CENTER);
    }
    private JPanel buildTitle() {
        String title = Tab.values()[CurrentPanel].name;

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        Title = new JLabel(title);
        Title.setFont(FontManager.getRunescapeBoldFont());
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        titlePanel.add(Title, BorderLayout.CENTER);

        return titlePanel;
    }
    private JPanel buildFilter() {
        JPanel filterPanel = new JPanel(new BorderLayout());
        filterPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);

        Filter = new JTextField();
        Filter.setFont(FontManager.getRunescapeFont());
        Filter.setHorizontalAlignment(SwingConstants.CENTER);
        Filter.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        filterPanel.add(Filter, BorderLayout.NORTH);

        String[] filters = Tab.values()[CurrentPanel].filters;
        FilterCombo = new JComboBox<>(filters);
        FilterCombo.setFont(FontManager.getRunescapeFont());
        FilterCombo.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        filterPanel.add(FilterCombo, BorderLayout.SOUTH);

        if(CurrentPanel == 0) {
            FilterCombo.setVisible(false);
        } else {
            Filter.setVisible(false);
        }

        return filterPanel;
    }
    public void AddData(FarmTrackerData data) {
        TableModel model = (TableModel) Table.getModel();
        model.addRow(data);
    }
    public void ChangeData(int CurrentPanel) {
        this.CurrentPanel = CurrentPanel;
        String title = Tab.values()[CurrentPanel].name;
        Title.setText(title);

        if(CurrentPanel == 0) {
            Filter.setVisible(true);
            FilterCombo.setVisible(false);
        } else {
            String[] filters = Tab.values()[CurrentPanel].filters;
            FilterCombo.removeAllItems();
            for (String filter : filters) {
                FilterCombo.addItem(filter);
            }
            FilterCombo.setVisible(true);
            Filter.setVisible(false);
        }

    }
}
