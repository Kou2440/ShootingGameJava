// https://youtu.be/ltbWixPqntY?t=1497

// 改良点
// レベルが０からの開始である点
// より細かくファイル分けする

// 当たり判定作り直し
package test.Shooting;

import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;

public class Shooting {
    public static ShootingFrame shootingFrame;
    public static boolean loop;

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;
    public static final int WINDOW_CENTER_X = WINDOW_WIDTH / 2;
    public static final int WINDOW_CENTER_Y = WINDOW_HEIGHT / 2;
    public static final int MS = 1000;

    public static void main(String[] args) {
        shootingFrame = new ShootingFrame();

        loop = true;

        Graphics gra = shootingFrame.panel.image.getGraphics();

        // FPS
        long startTime;
        long fpsTime = 0;
        int fps = 30;
        int FPS = 0;
        int FPSCount = 0;

        // GAME
        Player player = new Player(0, 0);
        EnemyController enemies = new EnemyController();
        BulletController bullets = new BulletController();
        int score = 0;
        int level = 0;
        long levelTimer = 0;
        EnumShootingScreen screen = EnumShootingScreen.START;

        while (loop) {
            if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE) && Keyboard.isKeyPressed((KeyEvent.VK_SHIFT))) {
                System.exit(0);
                break;
            }
            if ((System.currentTimeMillis() - fpsTime) >= MS) {
                fpsTime = System.currentTimeMillis();
                FPS = FPSCount;
                FPSCount = 0;
            }
            FPSCount++;
            startTime = System.currentTimeMillis();

            gra.setColor(Color.WHITE);
            gra.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            switch (screen) {
                case START:
                    gra.setColor(Color.BLACK);
                    Font font = new Font("SansSerif", Font.PLAIN, 50);
                    gra.setFont(font);
                    FontMetrics metrics = gra.getFontMetrics(font);
                    gra.drawString("Shooting", 250 - (metrics.stringWidth("Shooting") / 2), 100);
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Press SPACE to Start",
                            WINDOW_WIDTH / 2 - (metrics.stringWidth("Press SPACE to Start") / 2),
                            160);
                    if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
                        screen = EnumShootingScreen.GAME;
                        player.initialize();
                        enemies.initialize();
                        bullets.initialize();
                        score = 0;
                        level = 0;
                    }

                    break;
                case GAME:
                    if (System.currentTimeMillis() - levelTimer > 10 * MS) {
                        levelTimer = System.currentTimeMillis();
                        level++;
                    }


                    enemies.generate(level);
                    enemies.update();
                    enemies.draw(gra);
                    
                     for (var e : enemies.enemies) {
                     
                        if (HitTest.isHit(e, player)) {
                        screen = EnumShootingScreen.GAME_OVER;
                        score += (level - 1) * 100;
                        }

                     }
                     

                    

                     for (var bullet : BulletController.bullets) {
                        if (HitTest.isHit(bullet, player)) {
                            screen = EnumShootingScreen.GAME_OVER;
                            score += (level - 1) * 100;
                        }
                        for (int i = 0; i < enemies.getSize(); i++) {
                            Enemy e = enemies.get(i);
                            if (HitTest.isHit(bullet, e)) {
                                enemies.enemies.remove(i);
                                score += 10;
                            }
                        }
                     }

                    // プレイヤー
                    player.update();
                    player.draw(gra);
                    
                    // 弾
                    bullets.update();
                    bullets.draw(gra);

                    // UI
                    gra.setColor(Color.BLACK);
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    metrics = gra.getFontMetrics(font);
                    gra.setFont(font);
                    gra.drawString("SCORE:" + score, 470 - metrics.stringWidth("SCORE:" + score), 430);
                    gra.drawString("LEVEL:" + level, 470 - metrics.stringWidth("LEVEL:" + level), 450);
                    break;

                case GAME_OVER:
                    gra.setColor(Color.BLACK);
                    font = new Font("SansSerif", Font.PLAIN, 50);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Game Over", 250 - (metrics.stringWidth("Game Over") / 2), 100);
                    font = new Font("SansSerif", Font.PLAIN, 20);
                    gra.setFont(font);
                    metrics = gra.getFontMetrics(font);
                    gra.drawString("Score:" + score,
                            WINDOW_CENTER_X - (metrics.stringWidth("Score:" + score) / 2),
                            150);
                    gra.drawString("Press ESC to Return Start Screen",
                            WINDOW_CENTER_X
                                    - (metrics.stringWidth("Press ESC to Return Start Screen") / 2),
                            200);
                    if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                        screen = EnumShootingScreen.START;
                    }
                    break;
                default:
                    break;
            }

            gra.setColor(Color.BLACK);

            gra.setFont(new Font("SansSerif", Font.PLAIN, 10));
            gra.drawString(FPS + "FPS", 0, 470);

            shootingFrame.panel.draw();

            try {
                long runTime = System.currentTimeMillis() - startTime;
                if (runTime < MS / fps) {
                    Thread.sleep((MS / fps) - runTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
