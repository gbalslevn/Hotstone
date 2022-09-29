package hotstone.variants.ZetatStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

public class CincoDeck implements DeckStrategy {
    @Override
    public void createDeck(StandardHotStoneGame game) {
        addCardsToDeck(Player.FINDUS, (ArrayList) game.getDeck(Player.FINDUS));
        addCardsToDeck(Player.PEDDERSEN, (ArrayList) game.getDeck(Player.PEDDERSEN));
    }
    public void addCardsToDeck(Player who, ArrayList deck){
        for (int i = 0; i < 7; i++) {
            deck.add(new CardImpl(GameConstants.CINCO_CARD, 3, 5, 1, false, who));

        }
    }
}
