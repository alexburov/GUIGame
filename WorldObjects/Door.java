package WorldObjects;

import java.awt.*;

public class Door extends WorldObject
{
    public Door(int x, int y)
    {
        this.setX(x);
        this.setY(y);
        this.setTeam(Team.Neutral);
        this.setColor(Color.darkGray);
    }
}
