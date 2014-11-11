package PathFindingAStar;

import WorldObjects.IUnit;

public interface TileBasedMap
{
    public int getWidthInTiles();
    public int getHeightInTiles();
    public boolean blocked(IUnit mover, int x, int y);
    public float getCost(IUnit unit, int sx, int sy, int tx, int ty);
    public void pathFinderVisited(int x, int y);
}
