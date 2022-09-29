package hotstone.variants.ZetatStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.WinnerStategy;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.BetaStone.WinWhenHealthIs0;
import hotstone.variants.EpsilonStone.WinAfter7DamageOutput;

public class AlternatingWinner implements WinnerStategy {

    private WinnerStategy betaVersion, epsilonVersion, currentstate;

    public AlternatingWinner(WinnerStategy betaVersion, WinnerStategy epsilonVersion) {
        this.betaVersion = betaVersion;
        this.epsilonVersion = epsilonVersion;
        this.currentstate = null;
    }

    @Override

    public Player calculateWinner(Game game) {

        if (game.getTurnNumber() <= 12) {
            currentstate = betaVersion;
        } else if (game.getTurnNumber() >= 13) {
            if (game.getTurnNumber() == 13) {
                StandardHotStoneGame game1 = (StandardHotStoneGame) game;
                game1.setDamageOutput(Player.FINDUS, 0);
                game1.setDamageOutput(Player.PEDDERSEN, 0);
            }
                currentstate = epsilonVersion;
        }
        return currentstate.calculateWinner(game);
    }
}
