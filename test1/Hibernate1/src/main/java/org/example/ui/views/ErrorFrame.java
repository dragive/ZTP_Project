package org.example.ui.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ErrorFrame extends JFrame implements KeyListener {
    public ErrorFrame(String error) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400,100));
        this.setMinimumSize(new Dimension(400,100));
        this.setTitle("Wystąpił błąd");
        this.getContentPane().setBackground(Color.lightGray);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        JPanel panel = new JPanel();
        JPanel wrapper = new JPanel();
        JButton button = new JButton("Wstecz");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        panel.setLayout(new GridLayout(0,1));
        panel.add(new JLabel(error));
        panel.add(button);
        wrapper.add(panel);
        this.add(wrapper, BorderLayout.CENTER);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 27:
                this.dispose();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
