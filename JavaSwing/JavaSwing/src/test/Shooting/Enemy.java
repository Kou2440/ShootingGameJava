// 敵クラス
package test.Shooting;

import java.awt.*;
import java.util.Random;

public class Enemy extends PlayerAndEnemyBase {

    // private final int SIZE_X = 30;
    // private final int SIZE_Y = 20;
    
    
    private Random random = new Random();// ランダムクラスのインスタンス化

    // コンストラクタ
    public Enemy(int x, int y) {
        super(x, y, ObjectTag.ENEMY);
        CollisionUpdate();
    }

    // 更新
    void update() {
        if (random.nextInt(50) == 1) { // 射撃
            BulletController.generate(x + SHOOTING_POSITION_CORRECTION, y, tag);
        }
        y += 3;
        CollisionUpdate();
    }

    // 描画
    void draw(Graphics gra) {
        gra.setColor(Color.RED);
        gra.fillRect(x, y, BODY_WIDTH, BODY_LENGTH);// 胴体部
        gra.fillRect(x + FRONT_PARTS_POS_X, y + FRONT_PARTS_POS_Y, FRONT_PARTS_WIDTH, FRONT_PARTS_LENGTH);// 先頭部

    }
}
