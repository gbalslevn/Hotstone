package hotstone.framework;

// Interface for mutable methods
public interface MutableCard {

    void setActiveFalse();

    void setActiveTrue();

    void changeHealth(int healthChange);

    void changeAttack(int attackChange);
}
