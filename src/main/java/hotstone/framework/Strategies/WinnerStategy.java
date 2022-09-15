package hotstone.framework.Strategies;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;

public interface WinnerStategy {
    Player calculateWinner(Game game);
}
