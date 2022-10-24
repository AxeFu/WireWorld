package engine;

import engine.timer.Repeater;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Game extends JFrame implements GameObject {

    public final Repeater tick;
    private final ArrayList<GameObject> objects = new ArrayList<>();

    public Game(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tick = new Repeater(1000, this::update, Input::update, this::repaint);
        objects.add(this);
    }

    @Override
    public Component add(Component component) {
        super.add(component);
        if (component instanceof GameObject) {
            objects.add((GameObject) component);
        }
        return component;
    }

    public final void start() {
        tick.start();
    }

    public final void stop() {
        tick.stop();
    }

    private void update() {
        for (GameObject object : objects) {
            object.tick();
        }
    }

    public void tick() {

    }
}
