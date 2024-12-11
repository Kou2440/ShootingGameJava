// キーボードについてのクラス
package test.Shooting;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard extends KeyAdapter {

    private static ArrayList<Integer> pressedButtons = new ArrayList<>();

    // キーが押されたかどうか
    public static boolean isKeyPressed(int keyCode) {
        return pressedButtons.contains(keyCode);
    }

    // KeyEventキーが押されているかどうか
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if(!pressedButtons.contains(e.getKeyCode())) pressedButtons.add(e.getKeyCode());
    }

    // KeyEventキーが離されたかどうか
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        pressedButtons.remove((Integer) e.getKeyCode());
    }
}
