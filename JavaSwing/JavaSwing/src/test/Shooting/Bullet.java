// 弾クラス

package test.Shooting;

import java.awt.*;

class Bullet {
    
    int x, y;// 座標
    int speed = 10;// 弾速
    ObjectTag tag;// 使用者タグ
    CollisionStruct col;// 当たり判定座標

    private final int SIZE_X = 5;// 弾サイズ幅
    private final int SIZE_Y = 5;// 弾サイズ長さ
    
    // コンストラクタ
    public Bullet(int x, int y, ObjectTag tag) {
        this.x = x;
        this.y = y;
        this.tag = tag;
        col = new CollisionStruct();
        col.lx = x;
        col.ly = y;
        col.rx = x + SIZE_X;
        col.ry = y + SIZE_Y;
    }

    // 当たり判定座標の更新
    private void colUpdate() {
        col.ly = y;
        col.ry = y + SIZE_Y;
    }

    // 更新
    void update() {
        if(tag == ObjectTag.PLAYER) {
            y -= speed;
        }else if (tag == ObjectTag.ENEMY) {
            y += speed;
        }
        colUpdate();
    }

    // 描画
    void draw(Graphics gra){
        if(tag == ObjectTag.PLAYER){
            gra.setColor(Color.BLUE);
        }else if (tag == ObjectTag.ENEMY) {
            gra.setColor(Color.RED);
        }
        gra.fillRect(x, y, SIZE_X, SIZE_Y);

    }
}
