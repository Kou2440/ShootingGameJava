// プレイヤー及び敵クラスの親クラス
package test.Shooting;

import java.awt.Graphics;



public abstract class PlayerAndEnemyBase{
    final int BODY_WIDTH = 30;
    final int BODY_LENGTH = 10;
    final int FRONT_PARTS_POS_X = 10;
    final int FRONT_PARTS_POS_Y = 10;
    final int FRONT_PARTS_WIDTH = 10;
    final int FRONT_PARTS_LENGTH = 10;
    final int SHOOTING_POSITION_CORRECTION = 12;

    public int x, y;
    CollisionStruct colFront;
    CollisionStruct colBody;
    public BulletController bullets;
    ObjectTag tag;

    // コンストラクタ
    PlayerAndEnemyBase(int x, int y, ObjectTag tag) {
        this.x = x;
        this.y = y;
        this.tag = tag;
        colFront = new CollisionStruct();
        colBody = new CollisionStruct();

    }

    // 当たり判定更新
    void CollisionUpdate() {
        if (this.tag == ObjectTag.PLAYER) {
            // 当たり判定 上部
            colFront.lx = this.x + FRONT_PARTS_POS_X;
            colFront.ly = this.y - FRONT_PARTS_POS_Y;
            colFront.rx = colFront.lx + FRONT_PARTS_WIDTH;
            colFront.ry = colFront.ly + FRONT_PARTS_LENGTH;
            // 当たり判定 下部
            colBody.lx = this.x;
            colBody.ly = this.y;
            colBody.rx = colBody.lx + BODY_WIDTH;
            colBody.ry = colBody.ly + BODY_LENGTH;
        } else {
            // 当たり判定 上部
            colFront.lx = this.x + FRONT_PARTS_POS_X;
            colFront.ly = this.y + FRONT_PARTS_POS_Y;
            colFront.rx = colFront.lx + FRONT_PARTS_WIDTH;
            colFront.ry = colFront.ly + FRONT_PARTS_LENGTH;
            // 当たり判定 下部
            colBody.lx = this.x;
            colBody.ly = this.y;
            colBody.rx = colBody.lx + BODY_WIDTH;
            colBody.ry = colBody.ly + BODY_LENGTH;
        }
    }

    // 更新（オーバーライド必須）
    void update() {
    }

    // 描画（オーバーライド必須）
    void draw(Graphics gra) {
    }
}
