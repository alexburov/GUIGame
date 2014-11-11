package PathFindingAStar;
import WorldObjects.IUnit;

public class ClosestHeuristic implements IAStarHeuristic
{
    @Override
    public float getCost(TileBasedMap map, IUnit mover, int x, int y, int tx, int ty)
    {
        float dx = tx - x;
        float dy = ty - y;

        float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));

        return result;
    }
}
