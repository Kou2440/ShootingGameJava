// プレイヤークラス
package test.Shooting;

import java.awt.event.KeyEvent;
import java.awt.*;

public class Player extends PlayerAndEnemyBase {
    
    int bulletInterval = 0;// 射撃クールタイム変数
    private final int BULLET_COOLTIME = 5;
    

    private final int INITIALIZE_POSITION_X = 235;// 初期位置X
    private final int INITIALIZE_POSITION_Y = 430;// 初期位置Y
    //private final int SIZE_X = 30;
    //private final int SIZE_Y = 20;

    // コンストラクタ
    Player(int x, int y) {
        super(x, y, ObjectTag.PLAYER);
    }

    // 初期化
    void initialize() {
        x = INITIALIZE_POSITION_X;
        y = INITIALIZE_POSITION_Y;
        CollisionUpdate();
    }

    // 更新
    void update() {

        if (Keyboard.isKeyPressed(KeyEvent.VK_LEFT) && x > 0) {// 左キー
            x -= 5;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_RIGHT) && x < 470) {// 右キー
            x += 5;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_UP) && colFront.ly > 0) {// 上キー
            y -= 5;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_DOWN) && y < 450) {// 下キー
            y += 5;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE) && bulletInterval == 0) {// スペースキー
            BulletController.generate(x + SHOOTING_POSITION_CORRECTION, y, tag);
            bulletInterval = BULLET_COOLTIME;
        }
        if (bulletInterval > 0) {
            bulletInterval--;
        }
        
        CollisionUpdate();
    }

    // 描画
    void draw(Graphics gra) {
        gra.setColor(Color.BLUE);
        gra.fillRect(x, y, BODY_WIDTH, BODY_LENGTH);// 胴体部
        gra.fillRect(x + FRONT_PARTS_POS_X, y - FRONT_PARTS_POS_Y, FRONT_PARTS_WIDTH, FRONT_PARTS_LENGTH);// 先頭部
        
    }
}
