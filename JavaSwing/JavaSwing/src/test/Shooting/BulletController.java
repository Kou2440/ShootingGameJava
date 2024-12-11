// BulletControllerクラス
// 弾の管理クラス
package test.Shooting;

import java.util.*;
import java.awt.*;

public class BulletController {

    static ArrayList<Bullet> bullets;// 管理配列

    // コンストラクタ
    BulletController() {
        bullets = new ArrayList<>();
    }

    // 初期化
    void initialize() {
        bullets = new ArrayList<>();
    }

    // 弾の生成（管理配列への追加）
    static void generate(int x, int y, ObjectTag tag) {
        bullets.add(new Bullet(x, y, tag));
        
    }

    // 更新
    void update() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (bullet.y > Shooting.WINDOW_HEIGHT || bullet.y < 0) {
                bullets.remove(i);
                i--;
            }
        }
    }

    // 描画
    void draw(Graphics gra) {
        for (Bullet bullet : bullets) {
            bullet.draw(gra);
        }
    }
}
