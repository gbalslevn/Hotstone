package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.AlphaStone.WinAfter4Rounds;
import hotstone.variants.DeltaStone.SetManaTo7;
import hotstone.variants.EpsilonStone.RandomReal;
import hotstone.variants.EtaStone.CardEffect;
import hotstone.variants.EtaStone.EtaStoneDishDeck;
import hotstone.variants.GammaStone.DanishThaiChefs;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;
import hotstone.variants.RandomFixed;

import java.util.Random;

public class EtaStoneFactoryFixed implements StoneFactory {
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
        return new EtaStoneDishDeck();
    }

    @Override
    public CardEffectStrategy createEffectStrategy() {
        return new CardEffect(new RandomFixed(0));
    }
}
