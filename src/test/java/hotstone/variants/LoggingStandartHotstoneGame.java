package hotstone.variants;

import hotstone.Observer.GameObserver;
import hotstone.framework.*;

public class LoggingStandartHotstoneGame implements Game {

    private Game decoratee;
    public LoggingStandartHotstoneGame(Game game) {
        this.decoratee = game;
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public Player getPlayerInTurn() {
        return decoratee.getPlayerInTurn();
    }

    @Override
    public Hero getHero(Player who) {
        return decoratee.getHero(who);
    }

    @Override
    public Player getWinner() {
        return decoratee.getWinner();
    }

    @Override
    public int getTurnNumber() {
        return decoratee.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return decoratee.getDeckSize(who);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return decoratee.getCardInHand(who,indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return decoratee.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return decoratee.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return decoratee.getCardInField(who,indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return decoratee.getField(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return decoratee.getFieldSize(who);
    }

    @Override
    public void endTurn() {
        System.out.println(decoratee.getPlayerInTurn() + " has ended his turn!");
        System.out.println(Utility.computeOpponent(decoratee.getPlayerInTurn()) + " drew a card!");
        decoratee.endTurn();
    }

    @Override
    public Status playCard(Player who, Card card) {
        System.out.println(who + " played " + card.getName());
        return decoratee.playCard(who,card);
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        System.out.println(playerAttacking + " attacked " + defendingCard.getName() + " with " + attackingCard.getName());
            return decoratee.attackCard(playerAttacking,attackingCard,defendingCard);
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        System.out.println(playerAttacking + " attacks oppenent hero with: " + attackingCard.getName());
        return decoratee.attackHero(playerAttacking,attackingCard);
    }

    @Override
    public Status usePower(Player who) {
        System.out.println(who + " uses his power");
        return decoratee.usePower(who);
    }

}
