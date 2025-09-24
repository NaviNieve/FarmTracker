package com.farmtracker.tracker;

import com.farmtracker.util.Tab;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.AsyncBufferedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class FarmTrackerButton extends JPanel {
    private final int UID;
    private final int itemID;

    private final FarmTrackerButtonPanel parent;
    private final ItemManager itemManager;

    private JLabel iconLabel;

    FarmTrackerButton(final FarmTrackerButtonPanel parent, final ItemManager itemManager, final Tab tab, int currentUID) {
        this.parent = parent;
        this.itemManager = itemManager;

        this.UID = tab.uid;
        this.itemID = tab.itemID;

        this.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, ColorScheme.DARK_GRAY_COLOR));

        buildMouseListener();
        build(currentUID);
    }
    private void buildMouseListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.ChangeTab(UID);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(ColorScheme.DARK_GRAY_COLOR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(ColorScheme.DARKER_GRAY_COLOR);
            }
        });
    }
    private void build(int currentUID) {
        iconLabel = new JLabel();

        AsyncBufferedImage icon = itemManager.getImage(itemID);
        icon.onLoaded(() ->
        {
            BufferedImage subIcon = icon.getSubimage(0, 0, 32, 32);
            iconLabel.setIcon(new ImageIcon(subIcon.getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
        });
        if(UID == currentUID) {
            iconLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorScheme.BRAND_ORANGE));
        } else {
            iconLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorScheme.DARK_GRAY_COLOR));
        }
        this.add(iconLabel);
    }
    public void ChangeView(int currentUID) {
        if(UID == currentUID) {
            iconLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorScheme.BRAND_ORANGE));
        } else {
            iconLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ColorScheme.DARK_GRAY_COLOR));
        }
    }
}
