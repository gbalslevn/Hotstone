package hotstone.variants.AlphaStone;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;


public class SpanishDeck implements DeckStrategy {

    @Override
    public void createDeck(StandardHotStoneGame game) {
        ArrayList findusDeck = (ArrayList) game.getDeck(Player.FINDUS);
        ArrayList peddersonsDeck = (ArrayList) game.getDeck(Player.PEDDERSEN);

        //Add 7 cards to findus hand
        findusDeck.add(0, new CardImpl("Uno", 1, 1, 1, false, Player.FINDUS));
        findusDeck.add(1, new CardImpl("Dos", 2, 2, 2, false, Player.FINDUS));
        findusDeck.add(2, new CardImpl("Tres", 3, 3, 3, false, Player.FINDUS));
        findusDeck.add(3, new CardImpl("Cuatro", 2, 3, 1, false, Player.FINDUS));
        findusDeck.add(4, new CardImpl("Cinco", 3, 5, 1, false, Player.FINDUS));
        findusDeck.add(5, new CardImpl("Seis", 2, 1, 3, false, Player.FINDUS));
        findusDeck.add(6, new CardImpl("Siete", 3, 2, 4, false, Player.FINDUS));

        //Add 7 cards to pedderson hand
        peddersonsDeck.add(0, new CardImpl("Uno", 1, 1, 1, false, Player.PEDDERSEN));
        peddersonsDeck.add(1, new CardImpl("Dos", 2, 2, 2, false, Player.PEDDERSEN));
        peddersonsDeck.add(2, new CardImpl("Tres", 3, 3, 3, false, Player.PEDDERSEN));
        peddersonsDeck.add(3, new CardImpl("Cuatro", 2, 3, 1, false, Player.PEDDERSEN));
        peddersonsDeck.add(4, new CardImpl("Cinco", 3, 5, 1, false, Player.PEDDERSEN));
        peddersonsDeck.add(5, new CardImpl("Seis", 2, 1, 3, false, Player.PEDDERSEN));
        peddersonsDeck.add(6, new CardImpl("Siete", 3, 2, 4, false, Player.PEDDERSEN));
    }
}
