package SwingAppGame;

import PathFindingAStar.*;
import WorldObjects.*;

import java.awt.*;

public class Map implements IMap, TileBasedMap
{
    private static final int WIDTH = 20;

    private static final int HEIGHT = 20;

    final IWorldObject[][] units = new IWorldObject[WIDTH][HEIGHT];
    private IWorldObject[][] previousUnits;
    PaintCanvas canvas;

    private final boolean[][] visited = new boolean[WIDTH][HEIGHT];

    @Override
    public IWorldObject checkPosition(int x, int y)
    {
        return this.units[x][y];
    }

    @Override
    public void printMap()
    {
        for (int i = 0; i < units.length; i++)
        {
            for (int j = 0; j < units.length; j++)
            {
                if (units[i][j] == null)
                {
                    canvas.drawSquares(Color.green,j,i);
                }
                else
                {
                    canvas.drawSquares(units[i][j].getColor(),j,i);
                }
            }
        }
    }

    @Override
    public void setWorldObject(IWorldObject object)
    {
        this.units[object.getY()][object.getX()] = object;
        this.printMap();
    }


    @Override
    public void changeUnitPosition(IUnit unit, int newX, int newY)
    {
        try
        {
            if (unit.isAlive())
            {
                IWorldObject checkObject = this.units[newY][newX];
                if (checkObject instanceof Door)
                {
                        moveTowardsDoor(unit, newX, newY);
                }

                else if (checkObject instanceof WorldObjects.IUnit || checkObject == null)
                {
                    if (checkObject instanceof IUnit && ((IUnit) checkObject).isAlive())
                    {
                        moveTowardsAliveUnit(unit, newX, newY);
                    }
                    else
                    {
                        moveTowardsNullOrDeadObject(unit,newX,newY);
                    }
                }
            }
        } catch (Exception ex)
        {
        }

    }

    @Override
    public void updatePreviousMap()
    {
        previousUnits = new IWorldObject[units.length][];
        for (int i = 0; i < units.length; i++)
            previousUnits[i] = units[i].clone();
    }


    @Override
    public void spawnPlayer(int x, int y, int health)
    {
        setWorldObject(new SwordsMan(x, y, health, WorldObject.Team.Player));
    }

    private void modifyMap(IWorldObject object, int newX, int newY)
    {
        object.setX(newX);
        object.setY(newY);
        this.setWorldObject(object);
    }

    private void changePreviousPositionForPreviousUnit(IWorldObject previousUnit, IWorldObject unit)
    {
        if (previousUnit != null && previousUnit.equals(unit))
        {
            previousUnit = null;
        }
        updatePreviousMap();
        this.units[unit.getY()][unit.getX()] = previousUnit;
    }

    private IWorldObject getPreviousObject(IWorldObject object)
    {
        return previousUnits[object.getY()][object.getX()];
    }

    public IWorldObject getCheckObject(int xToCheck, int yToCheck)
    {
        return this.units[yToCheck][xToCheck];
    }

    private void moveTowardsNullOrDeadObject(WorldObjects.IUnit unit, int newX, int newY)
    {
        IWorldObject previousUnit = getPreviousObject((IWorldObject) unit);
        changePreviousPositionForPreviousUnit(previousUnit, (IWorldObject) unit);
        modifyMap((IWorldObject) unit, newX, newY);
    }

    private void moveTowardsAliveUnit(WorldObjects.IUnit unit, int newX, int newY)
    {
        IWorldObject checkObject = units[newY][newX];
        Unit checkUnit = (Unit)checkObject;
        if (checkUnit.getTeam() != unit.getTeam() && checkUnit.getTeam() != Unit.Team.Neutral)
        {
            while (unit.isAlive() && checkUnit.isAlive())
            {
                unit.fight(checkUnit);
                this.printMap();
                try
                {
                    Thread.sleep(100);
                } catch (Exception ex)
                {
                    return;
                }
            }
        }
    }

    private void moveTowardsDoor(WorldObjects.IUnit unit, int newX, int newY)
    {
        IWorldObject checkObject = getCheckObject(newX, newY);
        IWorldObject previousUnit = getPreviousObject((IWorldObject) unit);
        changePreviousPositionForPreviousUnit(previousUnit, (IWorldObject) unit);

        if (checkObject.getY() == unit.getY())
        {
            if (checkObject.getX() < unit.getX())
            {
                newX--;
                modifyMap((IWorldObject) unit, newX, newY);
            } else if (checkObject.getX() > unit.getX())
            {
                newX++;
                modifyMap((IWorldObject) unit, newX, newY);
            }
        } else if (checkObject.getX() == unit.getX())
        {
            if (checkObject.getY() < unit.getY())
            {
                newY--;
                modifyMap((IWorldObject) unit, newX, newY);
            } else if (checkObject.getY() > unit.getY())
            {
                newY++;
                modifyMap((IWorldObject) unit, newX, newY);
            }
        }
    }

    @Override
    public int getWidthInTiles()
    {
        return WIDTH;
    }

    @Override
    public int getHeightInTiles()
    {
        return HEIGHT;
    }

    @Override
    public boolean blocked(IUnit mover, int x, int y)
    {
        IWorldObject checkObject = getWorldObject(x,y);
        return checkObject instanceof Wall;
    }

    @Override
    public float getCost(IUnit mover, int sx, int sy, int tx, int ty)
    {
        return 1;
    }

    @Override
    public void pathFinderVisited(int x, int y)
    {
        visited[x][y] = true;
    }

    private IWorldObject getWorldObject(int x, int y)
    {
        return units[y][x];
    }
}
