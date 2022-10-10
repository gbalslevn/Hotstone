package hotstone.variants.AbstractFactory;

import hotstone.framework.StoneFactory;
import hotstone.framework.Strategies.*;
import hotstone.variants.AlphaStone.HeroPowerBaby;
import hotstone.variants.AlphaStone.NoCardEffect;
import hotstone.variants.AlphaStone.SetMana3;
import hotstone.variants.AlphaStone.TypeBaby;
import hotstone.variants.BetaStone.WinWhenHealthIs0;
import hotstone.variants.EpsilonStone.WinAfter7DamageOutput;
import hotstone.variants.ZetatStone.AlternatingWinner;
import hotstone.variants.ZetatStone.CincoDeck;

public class ZetaStoneFactory implements StoneFactory {
    @Override
    public ManaStrategy createManaStrategy() {
        return new SetMana3();
    }

    @Override
    public WinnerStategy createWinnerStrategy() {
        return new AlternatingWinner(new WinWhenHealthIs0(),new WinAfter7DamageOutput());
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
        return new CincoDeck();
    }

    @Override
    public CardEffectStrategy createEffectStrategy() {
        return new NoCardEffect();
    }
}
