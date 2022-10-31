package hotstone.framework;

import hotstone.Observer.GameObserver;

public class StandardHotStoneSpy implements Game {
    private String lastMethodCalled;

    @Override
    public Player getPlayerInTurn() {
        return null;
    }

    @Override
    public Hero getHero(Player who) {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    @Override
    public int getTurnNumber() {
        return 0;
    }

    @Override
    public int getDeckSize(Player who) {
        return 0;
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return null;
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return null;
    }

    @Override
    public int getHandSize(Player who) {
        return 0;
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return null;
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return null;
    }

    @Override
    public int getFieldSize(Player who) {
        return 0;
    }

    @Override
    public void endTurn() {

    }

    @Override
    public Status playCard(Player who, Card card) {
        return null;
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        return null;
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        return null;
    }

    @Override
    public Status usePower(Player who) {
        return null;
    }

    @Override
    public void addObserver(GameObserver observer) {

    }
}
