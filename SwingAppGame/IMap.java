package SwingAppGame;

import WorldObjects.IUnit;
import WorldObjects.IWorldObject;

import java.awt.*;
import java.io.IOException;

public interface IMap
{
    IWorldObject checkPosition(int x, int y);
    void printMap();
    void setWorldObject(IWorldObject unit);
    void changeUnitPosition(IUnit unit, int newX, int newY) throws IOException, AWTException;
    void updatePreviousMap();
    void spawnPlayer(int x, int y, int health);
}
