package hotstone.variants.AlphaStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;


public class SpanishDeck implements DeckStrategy {

    @Override
    public void createDeck(StandardHotStoneGame game) {
        addCardsToDeck(Player.FINDUS, (ArrayList) game.getDeck(Player.FINDUS));
        addCardsToDeck(Player.PEDDERSEN, (ArrayList) game.getDeck(Player.PEDDERSEN));
    }
    public void addCardsToDeck(Player who, ArrayList deck){
        deck.add(0, new CardImpl(GameConstants.UNO_CARD, 1, 1, 1, false, who));
        deck.add(1, new CardImpl(GameConstants.DOS_CARD, 2, 2, 2, false, who));
        deck.add(2, new CardImpl(GameConstants.TRES_CARD, 3, 3, 3, false, who));
        deck.add(3, new CardImpl(GameConstants.CUATRO_CARD, 2, 3, 1, false, who));
        deck.add(4, new CardImpl(GameConstants.CINCO_CARD, 3, 5, 1, false, who));
        deck.add(5, new CardImpl(GameConstants.SEIS_CARD, 2, 1, 3, false, who));
        deck.add(6, new CardImpl(GameConstants.SIETE_CARD, 3, 2, 4, false, who));
    }
}
