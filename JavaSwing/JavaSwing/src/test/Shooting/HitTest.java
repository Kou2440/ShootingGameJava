// 当たり判定処理クラス
package test.Shooting;

public class HitTest {
    // 処理をまとめたメソッド
    private static boolean hitTest(CollisionStruct obj1, CollisionStruct obj2) {
        if (obj1.lx <= obj2.rx && obj2.lx <= obj1.rx && obj1.ly <= obj2.ry && obj2.ly <= obj1.ry) {
            return true;
        }
        return false;
    }

    // オブジェクト同士が当たったかどうか判定するメソッド
    public static boolean isHit(PlayerAndEnemyBase obj1, PlayerAndEnemyBase obj2) {
        if (hitTest(obj1.colFront, obj2.colFront) || hitTest(obj1.colBody, obj2.colFront) || hitTest(obj1.colBody, obj2.colBody)) {
            return true;
        }
        return false;
    }

    // 弾とオブジェクトが当たったかどうか判定するクラス
    public static boolean isHit(Bullet b, PlayerAndEnemyBase obj) {
        if (obj.tag != b.tag) {
            if ((b.col.lx <= obj.colFront.rx && obj.colFront.lx <= b.col.rx && b.col.ly <= obj.colFront.ry && obj.colFront.ly <= b.col.ry) ||
                (b.col.lx <= obj.colBody.rx && obj.colBody.lx <= b.col.rx && b.col.ly <= obj.colBody.ry && obj.colBody.ly <= b.col.ry)) {
                return true;
            }
        }
        return false;
    }

}
