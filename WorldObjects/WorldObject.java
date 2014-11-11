package WorldObjects;

import java.awt.*;

public abstract class WorldObject implements IWorldObject
{
    private int x, y;
    private Team team;
    private Color color;

    public enum Team
    {
        Enemy,
        Player,
        Neutral
    }

    @Override
    public int getX()
    {
        return this.x;
    }

    @Override
    public void setX(int newX)
    {
        this.x = newX;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public void setY(int newY)
    {
            this.y = newY;
    }

    @Override
    public void setTeam(Team team)
    {
        this.team = team;
    }

    @Override
    public Team getTeam()
    {
        return this.team;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    @Override
    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public boolean isEnemy()
    {
        return this.getTeam() == Team.Enemy;
    }

    @Override
    public boolean isNeutral()
    {
        return this.getTeam() == Team.Neutral;
    }

    @Override
    public boolean isPlayer()
    {
        return this.getTeam() == Team.Player;
    }
}
