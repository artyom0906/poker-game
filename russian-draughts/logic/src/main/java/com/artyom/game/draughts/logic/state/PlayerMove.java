package com.artyom.game.draughts.logic.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.draughts.logic.board.Board;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;

import java.awt.*;
import java.awt.geom.Point2D;

public class PlayerMove implements GameState {
    private final CheckerColor color;

    //private final GameManager manager;
    public PlayerMove(CheckerColor color) {
        this.color = color;
    }

    @Override
    public void next(GameManager game) {
        game.setState(new PlayerMove(color.next()));
    }
    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        if(game instanceof RussianDraughtsManager manager){
            if(manager.getBoard().getPieces(color).stream().noneMatch(checker -> checkPossibleMoves(checker, manager.getBoard()))){
                manager.setState(new GameEnd(color.next()));
                manager.doAction();
            }
        }

    }
    private boolean checkPossibleMoves(Checker checker, Board board){
        Point2D pos = checker.getPoint();
        if(!checker.isKing()){
            int x1 = (int) (pos.getX()+1);
            int x2 = (int) (pos.getX()-1);
            int y = (int) pos.getY() + (checker.getColor()==CheckerColor.WHITE?-1:1);
            boolean right_border = (!(x1 <= 7) && board.isCheckerAtPos(new Point2D.Float(x2, y)));
            boolean left_border =  (!(x2 >= 0) && board.isCheckerAtPos(new Point2D.Float(x1, y)));
            boolean canBeat = !board.findAllBeatsForChecker(checker).isEmpty();
            if((right_border || left_border) && !canBeat){
                System.out.println("err: "+checker);
                return false;
            }
            return true;
        }
        return false;
    }

    public CheckerColor getColor() {
        return color;
    }
}

