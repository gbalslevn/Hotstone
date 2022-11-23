package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;

import java.util.UUID;

public class CardImpl implements Card, MutableCard {
    private String name;
    private int manaCost, health, attack;
    private boolean activeStatus;
    private Player owner;
    private String description;
    private String id = UUID.randomUUID().toString();


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
    public String getId() { return id; }

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
    public Player getOwner() {return owner;}


    @Override
    public void setActiveFalse() {
        activeStatus = false;
    }

    @Override
    public void setActiveTrue() {
        activeStatus = true;
    }

    @Override
    public void changeHealth(int healthChange) {
        health += healthChange;
    }

    @Override
    public void changeAttack(int attackChange) {
        attack += attackChange;
    }

    public String getDescription() {
        return description;
    }
}

