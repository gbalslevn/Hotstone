package hotstone.variants.EpsilonStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.WinnerStategy;
import hotstone.standard.StandardHotStoneGame;

import javax.xml.crypto.dsig.keyinfo.PGPData;

public class WinAfter7DamageOutput implements WinnerStategy {
    @Override
    public Player calculateWinner(Game game) {
        if (((StandardHotStoneGame) game).getDamageOutput(game.getPlayerInTurn()) == 7){
            return game.getPlayerInTurn();
        }
        return null;
    }
}
