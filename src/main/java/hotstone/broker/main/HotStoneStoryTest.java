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
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

public class HotStoneStoryTest {
  public static void main(String[] args)  {
    String host = "localhost";
    Player whoToPlay = Player.FINDUS;
    String gameID = GameClientProxy.GAME_OPBJECTID;

    if(args.length != 2){
      System.out.println("needs: host, who, id");
      System.out.println("host = localhost || IP address");
      System.out.println("who = findus or peddersen");
    } else {
      // Get the name of the host from the commandline parameters
      host = args[0];
      whoToPlay = Player.PEDDERSEN;
      if(args[1].equals("findus") || args[1].equals("Findus")){
        whoToPlay = Player.FINDUS;
      }
    }

    // Create the client side Broker roles
    UriTunnelClientRequestHandler clientRequestHandler
            = new UriTunnelClientRequestHandler(host, BrokerConstants.HOTSTONE_PORT,
            false, BrokerConstants.HOTSTONE_TUNNEL_PATH);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

    Game game = new GameClientProxy(requestor);
    Factory factory = new HotStoneFactory(game, whoToPlay, HotStoneDrawingType.OPPONENT_MODE);
    DrawingEditor editor = new MiniDrawApplication( "Playing: " + whoToPlay + "on GameID: " + gameID, factory );

    editor.open();
    editor.setTool(new NullTool());
    // and execute the story test, talking to the server with that name
    // new HotStoneStoryTest(host);
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
