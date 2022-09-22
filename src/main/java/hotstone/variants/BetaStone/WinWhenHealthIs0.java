package hotstone.variants.BetaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.WinnerStategy;
import hotstone.framework.Utility;

public class WinWhenHealthIs0 implements WinnerStategy {
    @Override
    public Player calculateWinner(Game game) {
        Player playerInTurn = game.getPlayerInTurn();
        //Your own hero can only die when it's not your turn
        if (game.getHero(Utility.computeOpponent(playerInTurn)).getHealth() <= 0){
        return playerInTurn;
        } return null;
    }
}
