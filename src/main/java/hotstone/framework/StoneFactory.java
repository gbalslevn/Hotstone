package hotstone.framework;

import hotstone.framework.Strategies.*;

public interface StoneFactory {
    ManaStrategy createManaStrategy();
    WinnerStategy createWinnerStrategy();
    PowerStrategy createPowerStrategy();
    TypeStrategy createTypeStrategy();
    DeckStrategy createDeckStrategy();
    CardEffectStrategy createEffectStrategy();
    // GameObserver createObserverStrategy();

}
