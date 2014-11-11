package WorldObjects;

import java.awt.*;

public class Wall extends WorldObject
{
    public Wall(int x, int y)
    {
        this.setX(x);
        this.setY(y);
        this.setTeam(Team.Neutral);
        this.setColor(Color.blue);
    }
}
