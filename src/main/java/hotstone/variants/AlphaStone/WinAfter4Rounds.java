package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.WinnerStategy;

public class WinAfter4Rounds implements WinnerStategy {
    @Override
    public Player calculateWinner(Game game) {
        return game.getTurnNumber() >= 8 ? Player.FINDUS : null;
    }
}
