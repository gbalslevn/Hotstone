package hotstone.variants.DeltaStone;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Strategies.DeckStrategy;
import hotstone.standard.CardImpl;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHotStoneGame;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class DishDeck implements DeckStrategy {
    ArrayList findusDeck = new ArrayList();
    ArrayList peddersonsDeck = new ArrayList();


    @Override
    public void createDeck(StandardHotStoneGame game) {
        ArrayList findusDeck = (ArrayList) game.getDeck(Player.FINDUS);
        ArrayList peddersonsDeck = (ArrayList) game.getDeck(Player.PEDDERSEN);

        //Add 24 cards to findus hand
        deckBuilder(findusDeck, Player.FINDUS);
        deckBuilder(findusDeck, Player.FINDUS);

        //Add 24 cards to pedderson hand
        deckBuilder(peddersonsDeck, Player.PEDDERSEN);
        deckBuilder(peddersonsDeck, Player.PEDDERSEN);

        //Shuffles the 2 decks
        shuffle(findusDeck);
        shuffle(peddersonsDeck);

        Card firstCardP = sortMana(1, peddersonsDeck);
        Card secondCardP = sortMana(2, peddersonsDeck);
        Card thirdCardP = sortMana(4, peddersonsDeck);
        peddersonsDeck.add(firstCardP);
        peddersonsDeck.add(secondCardP);
        peddersonsDeck.add(thirdCardP);

        Card firstCardF = sortMana(1, findusDeck);
        Card secondCardF = sortMana(2, findusDeck);
        Card thirdCardF = sortMana(4, findusDeck);
        findusDeck.add(0,firstCardF);
        findusDeck.add(1, secondCardF);
        findusDeck.add(2, thirdCardF);
    }

    private void deckBuilder(ArrayList deckName, Player owner) {
        deckName.add(new CardImpl(GameConstants.BROWN_RICE_CARD, 1, 1, 2, false, owner));
        deckName.add(new CardImpl(GameConstants.FRENCH_FRIES_CARD, 1, 2, 1, false, owner));
        deckName.add(new CardImpl(GameConstants.GREEN_SALAD_CARD, 2, 2, 3, false, owner));
        deckName.add(new CardImpl(GameConstants.TOMATO_SALAD_CARD, 2, 3, 2, false, owner));
        deckName.add(new CardImpl(GameConstants.POKE_BOWL_CARD, 3, 2, 4, false, owner));
        deckName.add(new CardImpl(GameConstants.PUMPKIN_SOUP_CARD, 4, 2, 7, false, owner));
        deckName.add(new CardImpl(GameConstants.NOODLE_SOUP_CARD, 4, 5, 3, false, owner));
        deckName.add(new CardImpl(GameConstants.SPRING_ROLLS_CARD, 5, 3, 7, false, owner));
        deckName.add(new CardImpl(GameConstants.BAKED_SALMON_CARD, 5, 8, 2, false, owner));
        deckName.add(new CardImpl(GameConstants.CHICKEN_CURRY_CARD, 6, 8, 4, false, owner));
        deckName.add(new CardImpl(GameConstants.BEEF_BURGER_CARD, 6, 5, 6, false, owner));
        deckName.add(new CardImpl(GameConstants.FILET_MIGNON_CARD, 7, 9, 5, false, owner));
    }

    private Card sortMana(int mana, ArrayList<Card> deck) {
        for (Card c : deck) {
            if (c.getManaCost() <= mana) {
                Card resultCard;
                resultCard = c;
                deck.remove(c);
                return resultCard;
            }
        }
        return null;
    }
}
