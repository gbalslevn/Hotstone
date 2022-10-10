package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.BetaStone.IncreaseManaUntil7;
import hotstone.variants.BetaStone.WinWhenHealthIs0;
import hotstone.variants.EpsilonStone.RandomReal;
import hotstone.variants.EtaStone.CardEffect;
import hotstone.variants.EtaStone.EtaStoneDishDeck;

public class SemiStoneFactory implements StoneFactory {
    @Override
    public ManaStrategy createManaStrategy() {
        return new IncreaseManaUntil7();
    }

    @Override
    public WinnerStategy createWinnerStrategy() {
        return new WinWhenHealthIs0();
    }

    @Override
    public PowerStrategy createPowerStrategy() {
        return null;
    }

    @Override
    public TypeStrategy createTypeStrategy() {
        return null;
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new EtaStoneDishDeck();
    }

    @Override
    public CardEffectStrategy createEffectStrategy() {
        return new CardEffect(new RandomReal());
    }
}
