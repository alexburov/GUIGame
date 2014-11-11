package SwingAppGame;

import WorldObjects.Door;
import WorldObjects.IWorldObject;
import WorldObjects.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SwingApp extends JFrame implements KeyListener, MouseListener
{
    static Game game;


    public SwingApp()
    {
        PaintCanvas canvas = new PaintCanvas(400,400);
        this.setUndecorated(true); // Remove title bar
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.add(canvas);
        game = new Game(canvas);
    }

    public static void main(String[] args)
    {
        SwingApp test = new SwingApp();
        test.setSize(400, 400);
        test.setTitle("MyGame");
        test.setResizable(false);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.addKeyListener(test);
        test.addMouseListener(test);
        buildWall();
        buildHouse();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        while (game.getSelectedObject() != null)
        {
            try
            {
                Thread.sleep(175);
            } catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
            game.getComputerMove();
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    static void buildHouse()
    {
        for (int i = 2; i < 10; i++)
        {
            game.map.setWorldObject(new Wall(i, 2));
            game.map.setWorldObject(new Wall(2, i));
            game.map.setWorldObject(new Wall(10, i));
            if (i == 5)
            {
                game.map.setWorldObject(new Door(5, 9));
            }
            else
            {
                game.map.setWorldObject(new Wall(i, 9));
            }
            for (int j = 10; j < 12; j++)
            {
                game.map.setWorldObject(new Wall(6, j));
                game.map.setWorldObject(new Wall(4, j));
            }
        }
    }

    static void buildWall()
    {
        for (int i = 0; i < 20; i++)
        {
            if (i != 11)
            {
                game.map.setWorldObject(new Wall(13, i));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
            System.out.println(e.getX() + " " + e.getY());
            int x = e.getX() / 20;
            int y = e.getY() / 20;
            System.out.println((e.getX() / 20) + " " + (e.getY() / 20));
            IWorldObject object = game.map.getCheckObject(x, y);
            if (object != null && game.getSelectedObject() == null)
            {
                game.setSelectedObject(object);
                object.setColor(Color.white);
                game.map.printMap();
            } else
            {
                game.setxDest(x);
                game.setyDest(y);
            }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
