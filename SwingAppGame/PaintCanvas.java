package SwingAppGame;

import javax.swing.*;
import java.awt.*;

public class PaintCanvas extends JPanel
{
    int boxSize = 20;
    int width,height;
    Graphics d;

    public PaintCanvas(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    @Override
    public void paintComponent(Graphics g) {
        update(g);
    }

    public void update(Graphics g)
    {
        //drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);

        for (int i = 0; i < 20; i++) {
            g.drawLine(i * boxSize, 0, i * boxSize, height);

        }
        for (int i = 0; i < 20; i++) {
            g.drawLine(0, i * boxSize, width, i * boxSize);
        }
    }
    public void drawSquares(Color color, int x, int y)
    {
        d = this.getGraphics();
        d.setColor(color);
        d.fillRect(x*boxSize,y*boxSize,boxSize,boxSize);
    }
}
