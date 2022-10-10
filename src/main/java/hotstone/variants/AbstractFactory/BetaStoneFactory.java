package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.AlphaStone.HeroPowerBaby;
import hotstone.variants.AlphaStone.NoCardEffect;
import hotstone.variants.AlphaStone.SpanishDeck;
import hotstone.variants.AlphaStone.TypeBaby;
import hotstone.variants.BetaStone.IncreaseManaUntil7;
import hotstone.variants.BetaStone.WinWhenHealthIs0;

public class BetaStoneFactory implements StoneFactory {
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
