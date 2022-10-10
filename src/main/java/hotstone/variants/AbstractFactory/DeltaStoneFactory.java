package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.AlphaStone.NoCardEffect;
import hotstone.variants.AlphaStone.SetMana3;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import hotstone.variants.DeltaStone.DishDeck;
import hotstone.variants.DeltaStone.SetManaTo7;
import hotstone.variants.GammaStone.DanishThaiChefs;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;

public class DeltaStoneFactory implements StoneFactory {
    @Override
    public ManaStrategy createManaStrategy() {
        return new SetManaTo7();
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
        return new DishDeck();
    }

    @Override
    public CardEffectStrategy createEffectStrategy() {
        return new NoCardEffect();
    }
}
