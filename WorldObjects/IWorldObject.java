package WorldObjects;

import java.awt.*;

public interface IWorldObject
{
    int getX();
    int getY();
    WorldObject.Team getTeam();

    void setX(int newX);
    void setY(int newY);
    void setTeam(WorldObject.Team team);
    Color getColor();
    void setColor(Color color);
    boolean isEnemy();
    boolean isNeutral();
    boolean isPlayer();
}
