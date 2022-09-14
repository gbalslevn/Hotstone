package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class CardImpl implements Card {
    private String name;
    private int manaCost, health, attack;
    private boolean activeStatus;
    private Player owner;


    public CardImpl(String name, int manaCost, int health, int attack, boolean activeStatus, Player owner) {
        this.name = name;
        this.manaCost = manaCost;
        this.health = health;
        this.attack = attack;
        this.owner = owner;
        this.activeStatus = activeStatus;
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
        return activeStatus;
    }
    @Override
    public Player getOwner() {
        return owner;
    }


    public void setActiveFalse(){
        activeStatus = false;
    }
    public void setActiveTrue(){
        activeStatus = true;
    }

    public void changeHealth(int healthChange){
        health += healthChange;
    }
}
