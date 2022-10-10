package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.AlphaStone.NoCardEffect;
import hotstone.variants.AlphaStone.SetMana3;
import hotstone.variants.AlphaStone.SpanishDeck;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import hotstone.variants.GammaStone.DanishThaiChefs;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;

public class GammaStoneFactory implements StoneFactory {
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
        return new HeroPowerThaiDanish();
    }

    @Override
    public TypeStrategy createTypeStrategy() {
        return new DanishThaiChefs();
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
