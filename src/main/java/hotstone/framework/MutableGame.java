package hotstone.framework;

import hotstone.standard.HeroImpl;

public interface MutableGame extends Game {
    void drawCard(Player who);
}
