package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;

import java.util.UUID;

public class HeroImpl implements Hero, MutableHero {
    private int damage, mana, health;
    private boolean isActive;
    private Player owner;
    private String getType;
    private String description;

    private String id = UUID.randomUUID().toString();

    public HeroImpl(int damage, int mana, int health, boolean isActive, Player owner, String getType, String description) {
        this.damage = damage;
        this.mana = mana;
        this.health = health;
        this.isActive = isActive;
        this.owner = owner;
        this.getType = getType;
        this.description = description;
    }


    public String getId() {
        return id;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String getType() {
        return getType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getEffectDescription() {
        return description;
    }

    @Override
    public void setMana(int newMana) {
        mana = newMana;
    }

    @Override
    public void changeMana(int manaChange) {
        mana += manaChange;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setActiveFalse() {
        isActive = false;
    }

    @Override
    public void setActiveTrue() {
        isActive = true;
    }
    @Override
    public void changeHealth(int healthChange) {
        health += healthChange;
    }
}
