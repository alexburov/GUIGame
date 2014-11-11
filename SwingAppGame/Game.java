package SwingAppGame;

import PathFindingAStar.*;
import WorldObjects.*;

public class Game implements IGame
{
    Map map = new Map();

    private SwordsMan player;
    private SwordsMan enemy;
    private AStarPathFinder pathFinder;
    private PathFindingAStar.Path path;
    private IWorldObject selectedObject;
    private int xDest, yDest;




    public Game(PaintCanvas canvas)
    {
        map.canvas = canvas;
        map.updatePreviousMap();
        map.spawnPlayer(2, 10, 100);
        player = (SwordsMan)map.units[10][2];
        enemy = new SwordsMan(17,5,70, Unit.Team.Enemy);
        map.setWorldObject(enemy);
        pathFinder = new AStarPathFinder(map,500,false, new ClosestHeuristic());
    }

    @Override
    public void getPlayerMove(char n)
    {
            switch (n)
            {
                case 'w':
                {
                    map.changeUnitPosition(player, player.getX(), player.moveUp());
                    break;
                }
                case 's':
                {
                    map.changeUnitPosition(player, player.getX(), player.moveDown());
                    break;
                }
                case 'a':
                {
                    map.changeUnitPosition(player, player.moveLeft(), player.getY());
                    break;
                }
                case 'd':
                {
                    map.changeUnitPosition(player, player.moveRight(), player.getY());
                    break;
                }
            }
    }

    @Override
    public void getComputerMove()
    {
        path = pathFinder.findPath((Unit)selectedObject, selectedObject.getX(), selectedObject.getY(), getxDest(), getyDest());
        Path path1 = pathFinder.findPath(enemy,enemy.getX(),enemy.getY(),player.getX(),player.getY());
        if (path != null)
        {
            map.changeUnitPosition((Unit)selectedObject,path.getStep(1).getX(),path.getStep(1).getY());
            map.changeUnitPosition(enemy ,path1.getStep(1).getX(),path1.getStep(1).getY());
        }
    }

    @Override
    public void getMove(char n)
    {
        getPlayerMove(n);
        getComputerMove();
    }

    public IWorldObject getSelectedObject()
    {
        return selectedObject;
    }

    public int getxDest()
    {
        return xDest;
    }

    public int getyDest()
    {
        return yDest;
    }

    public void setxDest(int xDest)
    {
        this.xDest = xDest;
    }

    public void setyDest(int yDest)
    {
        this.yDest = yDest;
    }

    public void setSelectedObject(IWorldObject selectedObject)
    {
        this.selectedObject = selectedObject;
    }
}
