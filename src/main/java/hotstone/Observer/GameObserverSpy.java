//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hotstone.Observer;

import hotstone.framework.Card;
import hotstone.framework.Player;

public class GameObserverSpy implements GameObserver {
    private String lastMethodCalled = "none";

    public GameObserverSpy() {
    }

    public void onCardPlay(Player who, Card card) {
        this.lastMethodCalled = "onCardPlay";
    }

    public void onTurnChangeTo(Player playerBecomingActive) {
        this.lastMethodCalled = "onTurnChangeTo";
    }

    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        this.lastMethodCalled = "onAttackCard";
    }

    public void onAttackHero(Player playerAttacking, Card attackingCard) {
        this.lastMethodCalled = "onAttackHero";
    }

    public void onUsePower(Player who) {
        this.lastMethodCalled = "onUsePower";
    }

    public void onCardDraw(Player who, Card drawnCard) {
        this.lastMethodCalled = "onCardDraw";
    }

    public void onCardUpdate(Card card) {
        this.lastMethodCalled = "onCardUpdate";
    }

    public void onCardRemove(Player who, Card card) {
        this.lastMethodCalled = "onCardRemove";
    }

    public void onHeroUpdate(Player who) {
        this.lastMethodCalled = "onHeroUpdate";
    }

    public void onGameWon(Player playerWinning) {
        this.lastMethodCalled = "onGameWon";
    }

    public String lastMethodCalled() {
        return this.lastMethodCalled;
    }
}
