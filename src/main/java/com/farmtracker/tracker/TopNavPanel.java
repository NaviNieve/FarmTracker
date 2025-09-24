package com.farmtracker.tracker;

import com.farmtracker.TrackerPanel;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.ImageUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * To-Do:
 * Tooltips on buttons
 * Popup Class / Opening.
 * Testing.
 */

public class TopNavPanel extends JPanel {
    // Static Data
    private final String POPUP_ICON = "fullscreen.png";
    private final String GE_ICON = "ge_icon.png";
    private final String TRACKER_ICON = "tracking.png";
    // Dynamic Data
    private int CurrentMainPanel;
    // Javax Swing Data
    private JLabel SwitchIcon;
    private JLabel Title;
    // Parents Data
    private final FarmTrackerPanel FarmTrackerPanel;

    TopNavPanel(FarmTrackerPanel Plugin, int CurrentMainPanel) {
        this.FarmTrackerPanel = Plugin;
        this.CurrentMainPanel = CurrentMainPanel;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 6, 6, 6));
        build();
    }

    private void build() {
        JPanel PopupPanel = new JPanel();
        PopupPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        PopupPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, ColorScheme.DARK_GRAY_COLOR));
        JLabel PopupIcon = new JLabel();
        BufferedImage popupIcon = ImageUtil.loadImageResource(TrackerPanel.class, POPUP_ICON);
        PopupIcon.setIcon(new ImageIcon(popupIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));

        PopupPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onPopup();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                PopupPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                PopupPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            }
        });

        PopupPanel.add(PopupIcon);
        this.add(PopupPanel, BorderLayout.WEST);

        JPanel SwitchPanel = new JPanel();
        SwitchPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        SwitchPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, ColorScheme.DARK_GRAY_COLOR));
        SwitchIcon = new JLabel();

        if(CurrentMainPanel == 0) {
            BufferedImage geIcon = ImageUtil.loadImageResource(TrackerPanel.class, GE_ICON);
            SwitchIcon.setIcon(new ImageIcon(geIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        } else {
            BufferedImage trackerIcon = ImageUtil.loadImageResource(TrackerPanel.class, TRACKER_ICON);
            SwitchIcon.setIcon(new ImageIcon(trackerIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
        }

        SwitchPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onSwitch();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                SwitchPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                SwitchPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
            }
        });

        SwitchPanel.add(SwitchIcon);
        this.add(SwitchPanel, BorderLayout.EAST);

        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        TitlePanel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, ColorScheme.DARK_GRAY_COLOR));
        Title = new JLabel("Farm Tracker");
        Title.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setVerticalAlignment(SwingConstants.TOP);
        Title.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        TitlePanel.add(Title);
        this.add(TitlePanel, BorderLayout.CENTER);

    }
    private void onPopup() {
        //TO-DO
    }
    private void onSwitch() {
        if(CurrentMainPanel == 0) CurrentMainPanel = 1;
        else if(CurrentMainPanel == 1) CurrentMainPanel = 0;

        if(CurrentMainPanel == 0) {
            Title.setText("Farm Tracker");
            BufferedImage geIcon = ImageUtil.loadImageResource(TrackerPanel.class, GE_ICON);
            SwitchIcon.setIcon(new ImageIcon(geIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            FarmTrackerPanel.ChangeMain(0);
        }
        else {
            Title.setText("GE Tracker");
            BufferedImage trackerIcon = ImageUtil.loadImageResource(TrackerPanel.class, TRACKER_ICON);
            SwitchIcon.setIcon(new ImageIcon(trackerIcon.getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
            FarmTrackerPanel.ChangeMain(1);
        }
    }
}
