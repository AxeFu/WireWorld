package engine;

import engine.inputs.InputAdapter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

@SuppressWarnings("unused")
public class Input extends InputAdapter {

    public static Input instance = new Input();

    private Input() {

    }

    public static void update() {
        System.arraycopy(keys, 0, lastKeys, 0, KEY_LENGTH);
        System.arraycopy(buttons, 0, lastButtons, 0, BUTTON_LENGTH);
        wheel = 0;
    }

    private static final int KEY_LENGTH = 512;
    private static final boolean[] keys = new boolean[KEY_LENGTH];
    private static final boolean[] lastKeys = new boolean[KEY_LENGTH];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public static boolean getKey(int keyCode) {
        return keys[keyCode];
    }

    public static boolean getKeyDown(int keyCode) {
        return keys[keyCode] && !lastKeys[keyCode];
    }

    public static boolean getKeyUp(int keyCode) {
        return !keys[keyCode] && lastKeys[keyCode];
    }

    private static final int BUTTON_LENGTH = 5;
    private static final boolean[] buttons = new boolean[BUTTON_LENGTH];
    private static final boolean[] lastButtons = new boolean[BUTTON_LENGTH];

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
        start = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
        delta = new Point();
        end = e.getPoint();
    }

    public static boolean getButton(int button) {
        return buttons[button];
    }

    public static boolean getButtonDown(int button) {
        return buttons[button] && !lastButtons[button];
    }

    public static boolean getButtonUp(int button) {
        return !buttons[button] && lastButtons[button];
    }

    public static int wheel;
    public static Point start = new Point();
    public static Point end = new Point();
    public static Point delta = new Point();
    public static Point current = new Point();

    @Override
    public void mouseDragged(MouseEvent e) {
        current = e.getPoint();
        delta = new Point(current.x - start.x,current.y - start.y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        current = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wheel += e.getWheelRotation();
    }

}
