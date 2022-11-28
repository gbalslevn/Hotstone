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

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HotStoneGameInvoker implements Invoker {
    private final Game game;
    private final Gson gson;

    private final NameService nameService = new NameServiceClass();

    private final HeroNameService heroNameService = new HeroNameServiceClass();


    public HotStoneGameInvoker(Game servant) {
        game = servant;
        gson = new Gson();
    }

    @Override
    public String handleRequest(String request) {
        // Do the demarshalling
        RequestObject requestObject = gson.fromJson(request, RequestObject.class);
        // used for when there is a parameter
        JsonArray array = JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        String heroId = requestObject.getObjectId();

        String cardId = requestObject.getObjectId();

        ReplyObject reply = null;

        try {
            // **************** Game Invoker ********************

            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_TURN_NUMBER)) {
                int turnNumber = game.getTurnNumber();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(turnNumber));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_HAND_SIZE)) {
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                int handSize = game.getHandSize(playerInTurn);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(handSize));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_DECK_SIZE)) {
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                int deckSize = game.getDeckSize(playerInTurn);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((deckSize)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_FIELD_SIZE)) {
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                int fieldSize = game.getFieldSize(playerInTurn);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((fieldSize)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_PLAYER_IN_TURN)) {
                Player playerInTurn = game.getPlayerInTurn();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((playerInTurn)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_WINNER)) {
                Player winner = game.getWinner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((winner)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_CARD)) {
                Player playerAttacking = gson.fromJson(array.get(0), Player.class);
                Card attackingCard = gson.fromJson(array.get(1), Card.class);
                Card defendingCard = gson.fromJson(array.get(2), Card.class);
                Status status = game.attackCard(playerAttacking, attackingCard, defendingCard);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((status)));
            }

            if(requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_HERO)){
                Player playerAttacking = gson.fromJson(array.get(0), Player.class);
                Card attackingCard = gson.fromJson(array.get(1), Card.class);
                Status status = game.attackHero(playerAttacking, attackingCard);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((status)));
            }

            if(requestObject.getOperationName().equals(OperationNames.GAME_USE_POWER)){
                Player playerWho = gson.fromJson(array.get(0), Player.class);
                Status status = game.usePower(playerWho);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((status)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_CARD_IN_HAND)){
                Player playerWho = gson.fromJson(array.get(0), Player.class);
                int index = gson.fromJson(array.get(1), Integer.class);
                Card card = game.getCardInHand(playerWho, index);
                if(card == null){
                    return null;
                }
                String id = card.getId();
                nameService.putCard(id, card);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((id)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_HAND)){
                Player playerWho = gson.fromJson(array.get(0), Player.class);

                List <String> cardIdList = new ArrayList<>();
                for (Card card : game.getHand(playerWho)){
                    nameService.putCard(card.getId(), card);
                    cardIdList.add(card.getId());
                }
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(cardIdList));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_FIELD)){
                Player playerWho = gson.fromJson(array.get(0), Player.class);
                List <String> cardIdList = new ArrayList<>();
                for (Card card : game.getField(playerWho)){
                    nameService.putCard(card.getId(), card);
                    cardIdList.add(card.getId());
                }
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(cardIdList));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_PLAY_CARD)) {
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                String id = gson.fromJson(array.get(1), String.class);
                Card card = nameService.getCard(id);
                Status status = game.playCard(playerInTurn, card);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((status)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_CARD)) {
                Player playerAttacking = gson.fromJson(array.get(0), Player.class);
                String attackingCardID = gson.fromJson(array.get(1), String.class);
                String defendingCardID = gson.fromJson(array.get(2), String.class);
                Card cardAttacking = nameService.getCard(attackingCardID);
                Card cardDefending = nameService.getCard(defendingCardID);
                Status status = game.attackCard(playerAttacking, cardAttacking, cardDefending);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(status));
            }

            if (requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_HERO)) {
                Player playerAttacking = gson.fromJson(array.get(0), Player.class);
                Hero hero = game.getHero(playerAttacking);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((hero)));

            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_CARD_IN_FIELD)){
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                int indexOfCard = gson.fromJson(array.get(1),Integer.class);
                Card card = game.getCardInField(playerInTurn,indexOfCard);
                String id = card.getId();
                nameService.putCard(id, card);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((id)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_END_OF_TURN)){
                game.endTurn();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(null));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_HERO)){
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                Hero hero = game.getHero(playerInTurn);
                String id = hero.getId();
                System.out.println(id);
                heroNameService.putHero(id, hero);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));
            }
            // **************** Hero Invoker ********************

            if (requestObject.getOperationName().startsWith(OperationNames.HERO_PREFIX)) {

            Hero servant = lookupHero(heroId);

            if(requestObject.getOperationName().equals(OperationNames.HERO_GET_MANA)){
                int mana = servant.getMana();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((mana)));
            }

            if(requestObject.getOperationName().equals((OperationNames.HERO_GET_HEALTH))){
                int health = servant.getHealth();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((health)));
            }

            if(requestObject.getOperationName().equals((OperationNames.HERO_IS_ACTIVE))){
                Boolean activeStatus = servant.isActive();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((activeStatus)));
            }

            if(requestObject.getOperationName().equals((OperationNames.HERO_GET_TYPE))){
                String type = servant.getType();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((type)));
            }

            if(requestObject.getOperationName().equals((OperationNames.HERO_GET_OWNER))){
                Player player = servant.getOwner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((player)));
            }
            }

            // **************** Card Invoker ********************

            if (requestObject.getOperationName().startsWith(OperationNames.CARD_PREFIX)){

            Card cardServant = lookupCard(cardId);

            if (requestObject.getOperationName().equals(OperationNames.CARD_GET_NAME)){
                String name = cardServant.getName();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(name));
            }
            if (requestObject.getOperationName().equals(OperationNames.CARD_GET_MANA_COST)){
                int manaCost = cardServant.getManaCost();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(manaCost));
            }
            if (requestObject.getOperationName().equals(OperationNames.CARD_GET_ATTACK)){
                int attack = cardServant.getAttack();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(attack));
            }
            if (requestObject.getOperationName().equals(OperationNames.CARD_GET_HEALTH)){
                int health = cardServant.getHealth();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(health));
            }
            if (requestObject.getOperationName().equals(OperationNames.CARD_IS_ACTIVE)){
                boolean isActive = cardServant.isActive();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(isActive));
            }
            if (requestObject.getOperationName().equals(OperationNames.CARD_GET_OWNER)){
                Player owner = cardServant.getOwner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(owner));
            }
            }

        } catch (Exception e) {
            reply = new ReplyObject(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return gson.toJson(reply);
    }

    private Hero lookupHero(String heroId) {
       return heroNameService.getHero(heroId);
    }

    private Card lookupCard(String cardId) {
        return nameService.getCard(cardId);
    }
}
