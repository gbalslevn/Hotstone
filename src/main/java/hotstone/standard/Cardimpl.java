package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class Cardimpl implements Card {
    String name;
    int manaCost;
    int health;
    int attack;
    Player owner;


    public Cardimpl(String name, int manaCost, int health, int attack, Player owner) {
        this.name = name;
        this.manaCost = manaCost;
        this.health = health;
        this.attack = attack;
        this.owner = owner;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

}
