package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.framework.Strategies.CardEffectStrategy;

public class CardImpl implements Card {
    private String name;
    private int manaCost, health, attack;
    private boolean activeStatus;
    private Player owner;
    private String description;



    public CardImpl(String name, int manaCost, int attack, int health, boolean activeStatus, Player owner) {
        this.name = name;
        this.manaCost = manaCost;
        this.health = health;
        this.attack = attack;
        this.owner = owner;
        this.activeStatus = activeStatus;
    }
    public CardImpl(String name, int manaCost, int attack, int health, boolean activeStatus, Player owner, String description) {
        this.name = name;
        this.manaCost = manaCost;
        this.health = health;
        this.attack = attack;
        this.owner = owner;
        this.activeStatus = activeStatus;
        this.description = description;
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


    public void setActiveFalse() {
        activeStatus = false;
    }

    public void setActiveTrue() {
        activeStatus = true;
    }

    public void changeHealth(int healthChange) {
        health += healthChange;
    }

    public void changeAttack(int attackChange) {
        attack += attackChange;
    }
}

