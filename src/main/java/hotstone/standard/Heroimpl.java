package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.Player;

public class Heroimpl implements Hero {
    int damage;
    int mana;
    int health;
    boolean isActive;
    Player owner;
    String getType;

    public Heroimpl(int damage, int mana, int health, boolean isActive, Player owner, String getType) {
        this.damage = damage;
        this.mana = mana;
        this.health = health;
        this.isActive = isActive;
        this.owner = owner;
        this.getType = getType;
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


    public void changeMana(int manaChange){
        mana += manaChange;
    }

    public int getDamage() {
        return damage;
    }

    public void changeActive(){
        isActive = !isActive;
    }

}
