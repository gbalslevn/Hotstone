package hotstone.framework;

public interface MutableHero extends Hero {
    void setMana(int newMana);

    void changeMana(int manaChange);

    int getDamage();

    void setActiveFalse();

    void setActiveTrue();
    void changeHealth(int healthChange);

}
