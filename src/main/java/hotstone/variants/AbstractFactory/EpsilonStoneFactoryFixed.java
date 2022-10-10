package hotstone.variants.AbstractFactory;
import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.AlphaStone.NoCardEffect;
import hotstone.variants.AlphaStone.SetMana3;
import hotstone.variants.AlphaStone.SpanishDeck;
import hotstone.variants.EpsilonStone.FrenchItalianChefs;
import hotstone.variants.EpsilonStone.HeroPowerFrenchItalian;
import hotstone.variants.EpsilonStone.RandomReal;
import hotstone.variants.EpsilonStone.WinAfter7DamageOutput;
import hotstone.variants.RandomFixed;

public class EpsilonStoneFactoryFixed implements StoneFactory {
    @Override
    public ManaStrategy createManaStrategy() {
        return new SetMana3();
    }

    @Override
    public WinnerStategy createWinnerStrategy() {
        return new WinAfter7DamageOutput();
    }

    @Override
    public PowerStrategy createPowerStrategy() {
        return new HeroPowerFrenchItalian(new RandomFixed(1));
    }

    @Override
    public TypeStrategy createTypeStrategy() {
        return new FrenchItalianChefs();
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
