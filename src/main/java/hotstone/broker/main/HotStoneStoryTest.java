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

package hotstone.broker.main;

import frds.broker.Requestor;
import frds.broker.ipc.http.UriTunnelClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.common.BrokerConstants;
import hotstone.framework.Game;
import hotstone.framework.Player;

public class HotStoneStoryTest {
  public static void main(String[] args)  {
    // Get the name of the host from the commandline parameters
    String host = args[0];
    // and execute the story test, talking to the server with that name
    new HotStoneStoryTest(host);
  }

  public HotStoneStoryTest(String host) {
    // Create the client side Broker roles
    UriTunnelClientRequestHandler clientRequestHandler
            = new UriTunnelClientRequestHandler(host, BrokerConstants.HOTSTONE_PORT,
            false, BrokerConstants.HOTSTONE_TUNNEL_PATH);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

    Game game = new GameClientProxy(requestor);
    testSimpleMethods(game);
  }

  private void testSimpleMethods(Game game) {
    System.out.println("=== Testing pass-by-value methods of Game ===");
    System.out.println(" --> Game turnNumber     " + game.getTurnNumber());
    System.out.println(" --> Game winner         " + game.getWinner());
    System.out.println(" --> Game handsize         " + game.getHandSize(game.getPlayerInTurn()));
    System.out.println(" --> Game fieldsize         " + game.getFieldSize(game.getPlayerInTurn()));
    System.out.println(" --> Game decksize         " + game.getDeckSize(game.getPlayerInTurn()));
    System.out.println(" --> Game player in turn         " + game.getPlayerInTurn());
    System.out.println(" --> Game play card         " + game.playCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0)));
    System.out.println(" --> Game attack card         " + game.attackCard(Player.FINDUS,game.getCardInHand(Player.FINDUS,0),game.getCardInHand(Player.PEDDERSEN,0) ));
    System.out.println(" --> Game attack hero         " + game.attackHero(Player.FINDUS,game.getCardInHand(Player.FINDUS,0)));
    System.out.println(" --> Game use power         " + game.usePower(Player.FINDUS));
    System.out.println("=== End ===");
  }
}
