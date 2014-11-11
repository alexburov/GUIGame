package WorldObjects;

import java.awt.*;
import java.util.Random;

public abstract class Unit extends WorldObject implements IUnit
{
    public Unit(int x, int y, int health, Team team)
    {
        setX(x);
        setY(y);
        setHealth(health);
        setTeam(team);
    }

    protected static int damage;

    private int health;



    @Override
    public int getHealth()
    {
        return this.health;
    }

    @Override
    public void setHealth(int newHealth)
    {
        this.health = newHealth;
    }

    @Override
    public int moveUp()
    {
        return this.getY() - 1;
    }

    @Override
    public int moveDown()
    {
        return this.getY() + 1;
    }

    @Override
    public int moveLeft()
    {
        return this.getX() - 1;
    }

    @Override
    public int moveRight()
    {
        return this.getX() + 1;
    }

    @Override
    public void decreaseHealth(int damage)
    {
        int currentHealth = this.getHealth();
        this.setHealth(currentHealth-damage);
    }

    @Override
    public boolean isAlive()
    {
        return this.getHealth() > 0;
    }

    @Override
    public void die()
    {
        this.setColor(Color.orange);
    }

    @Override
    public int getDamage()
    {
        Random random = new Random();
        return damage + random.nextInt(damage*2);
    }

    public void fight(Unit unit)
    {
        if (this.isAlive())
        {
            if (this.isPlayer())
            {
                int damage = this.getDamage();
                unit.decreaseHealth(damage);
                System.out.println("ALLY HEALTH: " + this.getHealth());
                System.out.println("ALLY DEALT " + damage + " to the enemy");
            }
            else if (this.isEnemy())
            {
                int damage = this.getDamage();
                unit.decreaseHealth(damage);
                System.out.println("ENEMY HEALTH: " + this.getHealth());
                System.out.println("ENEMY DEALT " + damage + " to the ALLY");
            }
        }
        else
        {
            this.die();
        }

        if (unit.isAlive())
        {
            if (unit.isPlayer())
            {
                int damage = unit.getDamage();
                this.decreaseHealth(damage);
                System.out.println("ALLY HEALTH: " + unit.getHealth());
                System.out.println("ALLY DEALT " + damage + " to the enemy");
            }
            else if (unit.isEnemy())
            {
                int damage = unit.getDamage();
                this.decreaseHealth(damage);
                System.out.println("ENEMY HEALTH: " + unit.getHealth());
                System.out.println("ENEMY DEALT " + damage + " to the ALLY");
            }
        }
        else
        {
            unit.die();
            dieMessage(unit);
        }
        if (!this.isAlive())
        {
            this.die();
            dieMessage(this);
        }
    }

    private void dieMessage(Unit unit)
    {
        if (unit.isEnemy())
        {
            System.out.println("Enemy died");
        }
        else if (unit.isPlayer())
        {
            System.out.println("Player died");
        }
    }
}
