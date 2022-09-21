package hotstone.variants.DeltaStone;

import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

public class DishDeck implements DeckStrategy {
    @Override
    public void createDeck(StandardHotStoneGame game) {
        ArrayList findusDeck = (ArrayList) game.getDeck(Player.FINDUS);
        ArrayList peddersonsDeck = (ArrayList) game.getDeck(Player.PEDDERSEN);

        //Add 12 cards to findus hand
        deckBuilder(findusDeck, Player.FINDUS);
        deckBuilder(findusDeck, Player.FINDUS);

        //Add 12 cards to pedderson hand
        deckBuilder(peddersonsDeck, Player.PEDDERSEN);
        deckBuilder(peddersonsDeck, Player.PEDDERSEN);

    }

    private void deckBuilder(ArrayList deckName, Player owner) {
        deckName.add(new CardImpl("Brown Rice", 1, 1, 2, false, owner));
        deckName.add(new CardImpl("French Fries", 1, 2, 1, false, owner));
        deckName.add(new CardImpl("Green Salad", 2, 2, 3, false, owner));
        deckName.add(new CardImpl("Tomato Salad", 2, 3, 2, false, owner));
        deckName.add(new CardImpl("Poke Bowl", 3, 2, 4, false, owner));
        deckName.add(new CardImpl("Pumpkin Soup", 4, 2, 7, false, owner));
        deckName.add(new CardImpl("Noodle Soup", 4, 5, 3, false, owner));
        deckName.add(new CardImpl("Spring Rolls", 5, 3, 7, false,owner));
        deckName.add(new CardImpl("Baked Salmon", 5, 8, 2, false,owner));
        deckName.add(new CardImpl("Chicken Curry", 6, 8, 4, false, owner));
        deckName.add(new CardImpl("Beef Burger", 6, 5, 6, false, owner));
        deckName.add(new CardImpl("Filet Mignon", 7, 9, 5, false, owner));
    }
}
