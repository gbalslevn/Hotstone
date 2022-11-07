//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hotstone.Observer;

import hotstone.framework.Card;
import hotstone.framework.Player;
import java.util.ArrayList;
import java.util.List;

public class ObserverHandler {
    private List<GameObserver> observerList = new ArrayList();

    public ObserverHandler() {
    }

    public void addObserver(GameObserver observer) {
        this.observerList.add(observer);
    }

    public void notifyPlayCard(Player who, Card card) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onCardPlay(who, card);
        });
    }

    public void notifyTurnChangeTo(Player playerInTurn) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onTurnChangeTo(playerInTurn);
        });
    }

    public void notifyAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onAttackCard(playerAttacking, attackingCard, defendingCard);
        });
    }

    public void notifyCardUpdate(Card card) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onCardUpdate(card);
        });
    }

    public void notifyCardRemove(Player who, Card card) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onCardRemove(who, card);
        });
    }

    public void notifyAttackHero(Player playerAttacking, Card attackingCard) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onAttackHero(playerAttacking, attackingCard);
        });
    }

    public void notifyHeroUpdate(Player who) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onHeroUpdate(who);
        });
    }

    public void notifyUsePower(Player who) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onUsePower(who);
        });
    }

    public void notifyGameWon(Player playerWinning) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onGameWon(playerWinning);
        });
    }

    public void notifyCardDraw(Player who, Card drawnCard) {
        this.observerList.forEach((gameObserver) -> {
            gameObserver.onCardDraw(who, drawnCard);
        });
    }
}
