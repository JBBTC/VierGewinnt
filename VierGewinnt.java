package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VierGewinnt {
    public static void main(String[] args) {

        JFrame Testing = new JFrame("4 Gewinnt");
        Testing.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Testing.setSize(1920, 1080);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JLabel Reihe1 = new JLabel("Spalte 1");
        JLabel Reihe2 = new JLabel("Spalte 2");
        JLabel Reihe3 = new JLabel("Spalte 3");
        JLabel Reihe4 = new JLabel("Spalte 4");
        JLabel Reihe5 = new JLabel("Spalte 5");
        JLabel Reihe6 = new JLabel("Spalte 6");
        JLabel Reihe7 = new JLabel("Spalte 7");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 113, 10, 113);

        gbc.gridx = 0;
        buttonPanel.add(Reihe1, gbc);
        gbc.gridx = 1;
        buttonPanel.add(Reihe2, gbc);
        gbc.gridx = 2;
        buttonPanel.add(Reihe3, gbc);
        gbc.gridx = 3;
        buttonPanel.add(Reihe4, gbc);
        gbc.gridx = 4;
        buttonPanel.add(Reihe5, gbc);
        gbc.gridx = 5;
        buttonPanel.add(Reihe6, gbc);
        gbc.gridx = 6;
        buttonPanel.add(Reihe7, gbc);

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        buttonPanel.add(new JPanel(), gbc);

        JPanel spielfeldPanel = new JPanel(new GridLayout(7, 7));

        final boolean[] spieler1AmZug = {true}; // Spieler 1 beginnt
        final Color[][] spielfeldZustand = new Color[7][7];

        JLabel spielerAnzeige = new JLabel("Spieler 1 ist an der Reihe");
        spielerAnzeige.setHorizontalAlignment(JLabel.CENTER);
        Testing.add(spielerAnzeige, BorderLayout.SOUTH);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                JButton spielfeldButton = new JButton();
                spielfeldPanel.add(spielfeldButton);

                final int row = i;
                final int col = j;

                spielfeldButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int availableRow = getAvailableRow(spielfeldZustand, col);
                        if (availableRow != -1 && row == availableRow) {
                            if (spieler1AmZug[0]) {
                                spielfeldButton.setBackground(Color.RED); // Spieler 1 (Rot)
                                spielfeldZustand[availableRow][col] = Color.RED;
                            } else {
                                spielfeldButton.setBackground(Color.YELLOW); // Spieler 2 (Gelb)
                                spielfeldZustand[availableRow][col] = Color.YELLOW;
                            }
                            spieler1AmZug[0] = !spieler1AmZug[0]; // Wechsle den Spieler
                            spielerAnzeige.setText(spieler1AmZug[0] ? "Spieler 1 ist an der Reihe" : "Spieler 2 ist an der Reihe");
                        }
                    }
                });
            }
        }

        Testing.add(buttonPanel, BorderLayout.NORTH);
        Testing.add(spielfeldPanel, BorderLayout.CENTER);

        Testing.setVisible(true);
    }

    private static int getAvailableRow(Color[][] spielfeldZustand, int col) {
        for (int row = 6; row >= 0; row--) {
            if (spielfeldZustand[row][col] == null) {
                return row;
            }
        }
        return -1; // Keine verfügbare Zeile in dieser Spalte
    }
}

