package hotstone.variants;

import hotstone.framework.Game;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaStone.*;
import hotstone.variants.EpsilonStone.HeroPowerFrenchItalian;
import hotstone.variants.GammaStone.DanishThaiChefs;
import hotstone.variants.GammaStone.HeroPowerThaiDanish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestZetaStone {
    private Game game;

    /**
     * Fixture for ZetaStone testing.
     */

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new SetMana3(),new WinAfter4Rounds(), new TypeBaby(), new HeroPowerBaby(), new SpanishDeck());
    }
}
