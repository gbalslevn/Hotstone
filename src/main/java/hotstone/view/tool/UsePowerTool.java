package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.standard.StandardHotStoneGame;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/**
 * Almost complete implementation of CardPlayTool: a MiniDraw tool
 * to play a card.
 * <p>
 * Much of the behaviour in this tool revolves around being able to
 * move the card figure BACK in case the game tells us that the
 * card could NOT be played (status != Status.OK).
 */

// TODO: Finish the implementation of PlayCard tool
public class UsePowerTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    protected final HotStoneDrawing model;
    private Player whoAmIPlaying;

    public UsePowerTool(DrawingEditor editor, Game game, Player whoAmIPlaying) {
        this.editor = editor;
        this.game = game;
        this.whoAmIPlaying = whoAmIPlaying;
        model = (HotStoneDrawing) editor.drawing();
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        StandardHotStoneGame game1 = (StandardHotStoneGame) game;
        game1.setManaHero((MutableHero) game.getHero(Player.FINDUS), 10);
        // Find hero image below
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
                Status status = game.usePower(whoAmIPlaying);
            if (hsf.getType() == HotStoneFigureType.HERO_FIGURE && status == Status.OK) {
                    editor.showStatus("Hero uses power. Result =" + status);
            }
        }
    }
}

