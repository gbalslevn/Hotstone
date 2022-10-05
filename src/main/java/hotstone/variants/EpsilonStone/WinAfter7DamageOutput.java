package hotstone.variants.EpsilonStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.WinnerStategy;
import hotstone.standard.StandardHotStoneGame;
import hotstone.standard.Stats;

import javax.xml.crypto.dsig.keyinfo.PGPData;

public class WinAfter7DamageOutput implements WinnerStategy {
    @Override
    public Player calculateWinner(Game game) {
        Stats stats = new Stats();
        if (stats.getDamageOutput(game.getPlayerInTurn()) >= 7){
            return game.getPlayerInTurn();
        }
        return null;
    }
}