package WorldObjects;

import java.awt.*;

public class Archer extends Unit implements IUnit
{
    public Archer(int x, int y, int health, WorldObject.Team team)
    {
        super(x, y, health, team);
        if (team == WorldObject.Team.Enemy)
        {
            this.setColor(Color.red);
        }
        else if (team == WorldObject.Team.Player)
        {
            this.setColor(Color.yellow);
        }
        damage = 5;
    }
}
