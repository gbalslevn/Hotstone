package hotstone.variants.ZetatStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.WinnerStategy;
import hotstone.standard.Stats;

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
                currentstate = epsilonVersion;
        }
        return currentstate.calculateWinner(game);
    }
}
