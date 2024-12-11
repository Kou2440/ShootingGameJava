// Swingウィンドウ画面の初期設定（？）
package test.Shooting;


import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShootingFrame extends JFrame{
    public ShootingPanel panel;

    public ShootingFrame() {

        panel = new ShootingPanel();

        this.add(panel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                Shooting.loop = true;
            }
        });

        this.addKeyListener(new Keyboard());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Shooting");
        this.setSize(Shooting.WINDOW_WIDTH, Shooting.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
