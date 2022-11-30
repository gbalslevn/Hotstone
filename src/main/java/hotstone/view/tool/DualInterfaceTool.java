package hotstone.view.tool;

import frds.broker.IPCException;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;
import java.io.IOException;

/** Template for the State tool - similar to MiniDraw SelectionTool
 * it is a tool that delegates all mouse events to a subtool, and
 * the kind of subtool to use is determined by what is clicked in the
 * mouse down event. If it is a button, then delegate to ButtonTool,
 * if it is a card, delegate to PlayCardTool, etc.
 *
 * Quite a lot of the code is complete - fill in the missing pieces...
 */

public class DualInterfaceTool extends NullTool {
    private final Tool theNullTool;
    private final Drawing model;
    private Tool state;
    private DrawingEditor editor;
    private Game game;

    private Player player;

    public DualInterfaceTool(DrawingEditor editor, Game game, Player whoToPlay) {
        this.editor = editor;
        this.game = game;
        model = editor.drawing();
        state = theNullTool = new NullTool();
        this.player = whoToPlay;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
            if (hsf.getType() == HotStoneFigureType.CARD_FIGURE) {
                state = new CardPlayTool(editor, game, game.getPlayerInTurn());
            } else if (hsf.getType() == HotStoneFigureType.TURN_BUTTON) {
                state = new EndTurnTool(editor, game);
            } else if (hsf.getType() == HotStoneFigureType.MINION_FIGURE) {
                state = new MinionAttackTool(editor,game,game.getPlayerInTurn());
            } else if (hsf.getType() == HotStoneFigureType.HERO_FIGURE) {
                state = new UsePowerTool(editor,game,game.getPlayerInTurn());
            } else if (hsf.getType() == HotStoneFigureType.WIN_BUTTON) {
                state = theNullTool; // Have to kill the window to restart.
            } else if (hsf.getType() == HotStoneFigureType.OPPONENT_ACTION_BUTTON) {
                model.requestUpdate();
            }
        }
        state.mouseDown(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        try{
            state.mouseUp(e, x, y);
            if(state != theNullTool){
                System.out.println("---> Bruteforce Redraw");
                model.requestUpdate();
            }

        } catch (IPCException exc) {

        }
        state = theNullTool;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        state.mouseDrag(e, x, y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
        state.mouseMove(e, x, y);
    }

}
