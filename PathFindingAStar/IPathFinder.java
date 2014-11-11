package PathFindingAStar;
import WorldObjects.IUnit;

public interface IPathFinder
{
    public IPath findPath(IUnit mover, int sx, int sy, int tx, int ty);
}
