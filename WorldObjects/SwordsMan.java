package WorldObjects;

import java.awt.*;

public class SwordsMan extends Unit implements IUnit
{
    public SwordsMan(int x, int y, int health, Team team)
    {
        super(x, y, health, team);
        if (team == Team.Enemy)
        {
            this.setColor(Color.red);
        }
        else if (team == Team.Player)
        {
            this.setColor(Color.yellow);
        }
        damage = 10;
    }
}
