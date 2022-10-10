package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.BetaStone.IncreaseManaUntil7;
import hotstone.variants.BetaStone.WinWhenHealthIs0;
import hotstone.variants.EpsilonStone.HeroPowerFrenchItalian;
import hotstone.variants.EpsilonStone.RandomReal;
import hotstone.variants.EtaStone.CardEffect;
import hotstone.variants.EtaStone.EtaStoneDishDeck;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;
import hotstone.variants.RandomFixed;
import hotstone.variants.SemiStone.AlternatingHeroPower;
import hotstone.variants.SemiStone.RandomHeroType;

public class SemiStoneFactoryFixed implements StoneFactory {@Override
public ManaStrategy createManaStrategy() {
    return new IncreaseManaUntil7();
}

    @Override
    public WinnerStategy createWinnerStrategy() {
        return new WinWhenHealthIs0();
    }

    @Override
    public PowerStrategy createPowerStrategy() {
        return new AlternatingHeroPower(new HeroPowerThaiDanish(),new HeroPowerFrenchItalian(new RandomFixed(1)));
    }

    @Override
    public TypeStrategy createTypeStrategy() {
        return new RandomHeroType(new RandomFixed(1));
    }

    @Override
    public DeckStrategy createDeckStrategy() {
        return new EtaStoneDishDeck();
    }

    @Override
    public CardEffectStrategy createEffectStrategy() {
        return new CardEffect(new RandomFixed(1));
    }
}
