package hotstone.Observer;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class GameObserverSpy implements GameObserver {

    private String lastMethodCalled = "none";
    @Override
    public void onCardPlay(Player who, Card card) {
        lastMethodCalled = "onCardPlay";
    }

    @Override
    public void onTurnChangeTo(Player playerBecomingActive) {
        lastMethodCalled = "onTurnChangeTo";
    }

    @Override
    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        lastMethodCalled = "onAttackCard";
    }

    @Override
    public void onAttackHero(Player playerAttacking, Card attackingCard) {
        lastMethodCalled = "onAttackHero";
    }

    @Override
    public void onUsePower(Player who) {
        lastMethodCalled = "onUsePower";
    }

    @Override
    public void onCardDraw(Player who, Card drawnCard) {
        lastMethodCalled = "onCardDraw";
    }

    @Override
    public void onCardUpdate(Card card) {
        lastMethodCalled = "onCardUpdate";
    }

    @Override
    public void onCardRemove(Player who, Card card) {
        lastMethodCalled = "onCardRemove";
    }

    @Override
    public void onHeroUpdate(Player who) {
        lastMethodCalled = "onHeroUpdate";
    }

    @Override
    public void onGameWon(Player playerWinning) {
        lastMethodCalled = "onGameWon";
    }

    public String lastMethodCalled() {
        return lastMethodCalled;
    }
}
