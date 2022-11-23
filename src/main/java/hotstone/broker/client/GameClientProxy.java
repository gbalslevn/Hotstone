/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.client;

import com.google.gson.reflect.TypeToken;
import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.Observer.GameObserver;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

/** Template/starter code for your ClientProxy of Game.
 */
public class GameClientProxy implements Game, ClientProxy {
  public static final String GAME_OPBJECTID = "singleton";

  private final Requestor requestor;
  public GameClientProxy(Requestor requestor) {
    this.requestor = requestor;
  }

  @Override
  public int getTurnNumber() {
    int number = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_TURN_NUMBER, Integer.class);
    return number;
  }

  @Override
  public Player getPlayerInTurn() {
    Player playerInTurn = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_PLAYER_IN_TURN, Player.class);
    return playerInTurn;
  }

  @Override
  public Hero getHero(Player who) {
    return null;
  }

  @Override
  public Player getWinner() {
    Player winner = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_WINNER, Player.class);
    return winner;
  }

  @Override
  public int getDeckSize(Player who) {
    int deckSize = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_DECK_SIZE, Integer.class, who);
    return deckSize;
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    String objectId = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_CARD_IN_HAND,String.class,who,indexInHand);
    if(objectId != null){
      return new CardClientProxy(requestor, objectId);
    }
    return null;
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List <String> cardID;
    cardID = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_HAND,collectionType,who);
    List<Card> allCard = new LinkedList<>();
    for(String id : cardID){
      allCard.add(new CardClientProxy(requestor,id));
    }
    return allCard;
  }

  @Override
  public int getHandSize(Player who) {
    int handsize = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_HAND_SIZE, Integer.class, who);
    return handsize;
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
    int fieldSize = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_GET_FIELD_SIZE, Integer.class, who);
    return fieldSize;
  }

  @Override
  public void endTurn() {

  }

  @Override
  public Status playCard(Player who, Card card) {
    Status status = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_PLAY_CARD, Status.class, who, card);
    return status;
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    Status status = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_ATTACK_CARD, Status.class,
            playerAttacking, attackingCard, defendingCard);
    return status;
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    Status status = requestor.sendRequestAndAwaitReply(
            GAME_OPBJECTID, OperationNames.GAME_ATTACK_HERO,
            Status.class, playerAttacking, attackingCard);
    return status;
  }

  @Override
  public Status usePower(Player who) {
    Status status = requestor.sendRequestAndAwaitReply(GAME_OPBJECTID, OperationNames.GAME_USE_POWER, Status.class, who);
    return status;
  }

  @Override
  public void addObserver(GameObserver var1) {

  }
}
