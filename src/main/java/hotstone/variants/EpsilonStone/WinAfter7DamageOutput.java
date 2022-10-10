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
        return Stats.getDamageOutput(game.getPlayerInTurn(), game.getTurnNumber()) >= 7 ? game.getPlayerInTurn() : null;
    }
}
