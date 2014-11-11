package WorldObjects;

public interface IUnit
{
    int moveUp();
    int moveDown();
    int moveLeft();
    int moveRight();
    void fight(Unit unit);
    void decreaseHealth(int damage);
    void die();


    boolean isAlive();

    WorldObject.Team getTeam();
    int getDamage();
    int getX();
    int getY();
    int getHealth();

    void setTeam(WorldObject.Team newTeam);
    void setX(int newX);
    void setY(int newY);
    void setHealth(int newHealth);
}
