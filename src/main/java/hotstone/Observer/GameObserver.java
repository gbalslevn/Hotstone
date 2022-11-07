//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package hotstone.Observer;

import hotstone.framework.Card;
import hotstone.framework.Player;

public interface GameObserver {
    void onCardPlay(Player var1, Card var2);

    void onTurnChangeTo(Player var1);

    void onAttackCard(Player var1, Card var2, Card var3);

    void onAttackHero(Player var1, Card var2);

    void onUsePower(Player var1);

    void onCardDraw(Player var1, Card var2);

    void onCardUpdate(Card var1);

    void onCardRemove(Player var1, Card var2);

    void onHeroUpdate(Player var1);

    void onGameWon(Player var1);
}
