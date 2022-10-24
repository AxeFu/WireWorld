import paint.Camera;

import java.awt.*;

public class Screen extends Camera {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.drawString("Control: ", 20, 20);
        g.drawString("Mouse wheel is zoom: ", 20, 40);
        g.drawString("z = start, x = stop, c = clear", 20, 60);
        g.drawString("1-3 is color", 20, 80);
        g.drawString("1 - Wire, 2 - Electron, 3 - Tail", 20, 100);
        g.drawString("Mouse buttons is drawing/cleaning: ", 20, 120);
        g.drawString("Press Alt - enable camera move mode. Drag left button to move", 20, 140);
    }

}
