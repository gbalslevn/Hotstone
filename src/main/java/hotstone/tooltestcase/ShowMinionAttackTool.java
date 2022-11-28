/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.tooltestcase;

import hotstone.framework.Game;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AbstractFactory.SemiStoneFactory;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.MinionAttackTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/** Visual test program to develop minion attack tool */
public class ShowMinionAttackTool {
  public static void main(String[] args) {
    Game game = new StandardHotStoneGame(new SemiStoneFactory());

    StandardHotStoneGame g = (StandardHotStoneGame) game;
    g.setManaHero((MutableHero) g.getHero(Player.FINDUS),100);

    game.playCard(Player.FINDUS,
            game.getCardInHand(Player.FINDUS, 0));

    game.endTurn();

    g.setManaHero((MutableHero) g.getHero(Player.PEDDERSEN),100);
    game.playCard(Player.PEDDERSEN,
            game.getCardInHand(Player.PEDDERSEN, 0));

    game.endTurn();

    DrawingEditor editor =
            new MiniDrawApplication("Drag Minions to perform attacks...",
                    new HotStoneFactory(game, Player.FINDUS,
                            HotStoneDrawingType.OPPONENT_MODE));
    editor.open();
    // TODO: Solve exercise by developing a MinionAttackTool
    editor.setTool(new MinionAttackTool(editor,game,Player.FINDUS));
  }
}
