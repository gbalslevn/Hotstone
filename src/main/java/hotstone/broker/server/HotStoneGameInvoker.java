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
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;

import javax.servlet.http.HttpServletResponse;

public class HotStoneGameInvoker implements Invoker {
    private final Game game;
    private final Gson gson;

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

        ReplyObject reply = null;

        try {
            if (requestObject.getOperationName().equals(OperationNames.GAME_GET_TURN_NUMBER)) {
                //Game go = gson.fromJson(array.get(0), Game.class);
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
            if (requestObject.getOperationName().equals(OperationNames.GAME_PLAY_CARD)) {
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                Card card = gson.fromJson(array.get(1), Card.class);
                Status status = game.playCard(playerInTurn, card);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((status)));
            }
            if (requestObject.getOperationName().equals(OperationNames.GAME_ATTACK_CARD)) {
                Player playerInTurn = gson.fromJson(array.get(0), Player.class);
                Card attackingCard = gson.fromJson(array.get(1), Card.class);
                Card defendingCard = gson.fromJson(array.get(2), Card.class);
                Status status = game.attackCard(playerInTurn, attackingCard, defendingCard);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson((status)));
            }
        } catch (Exception e) {
            reply = new ReplyObject(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return gson.toJson(reply);
    }
}
