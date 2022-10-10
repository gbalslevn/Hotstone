package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.AlphaStone.*;

public class AlphaStoneFactory implements StoneFactory {
    @Override
    public ManaStrategy createManaStrategy() {
        return new SetMana3();
    }

    @Override
    public WinnerStategy createWinnerStrategy() {
        return new WinAfter4Rounds();
    }

    @Override
    public PowerStrategy createPowerStrategy() {
        return new HeroPowerBaby();
    }

    @Override
    public TypeStrategy createTypeStrategy() {
        return new TypeBaby();
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new SpanishDeck();
    }

    @Override
    public CardEffectStrategy createEffectStrategy() {
        return new NoCardEffect();
    }
}
