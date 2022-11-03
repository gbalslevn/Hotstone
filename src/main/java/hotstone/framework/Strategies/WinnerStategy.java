package hotstone.framework.Strategies;

import hotstone.framework.Game;
import hotstone.framework.Player;

public interface WinnerStategy {
    Player calculateWinner(Game game);
}
