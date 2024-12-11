// 敵管理クラス
package test.Shooting;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import java.awt.event.KeyEvent;

public class EnemyController {

    
    ArrayList<Enemy> enemies = new ArrayList<>();// 敵管理配列

    private Random random = new Random();// ランダムクラスインスタンス化

    // 初期化
    void initialize() {
        enemies.clear();
    }

    // 敵の生成（管理配列へ追加）
    void generate(int level) {
        if (random.nextInt(level < 50 ? 80 - level : 30) == 1) {
            enemies.add(new Enemy(random.nextInt(470), 0));
        }

    }

    // 更新
    void update() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);

            enemy.update();

            if (enemy.y > 500) {
                enemies.remove(i);
            }
        }
    }

    // 描画
    void draw(Graphics gra) {
        gra.setColor(Color.RED);
        for (Enemy e : enemies) {
            e.draw(gra);
            
        }
    }

    // 要素の取得
    Enemy get(int index) {
        return enemies.get(index);
    }

    // 管理配列のサイズゲッター
    int getSize() {
        return enemies.size();
    }

}
