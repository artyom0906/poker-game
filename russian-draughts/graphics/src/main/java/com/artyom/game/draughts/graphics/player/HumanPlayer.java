package com.artyom.game.draughts.graphics.player;

import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.GameManager;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.graphics.board.BoardRenderable;
import com.artyom.game.draughts.graphics.checker.CheckerRenderable;
import com.artyom.game.draughts.logic.board.Board;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;

import java.awt.*;
import java.util.Optional;
import java.util.Set;

public class HumanPlayer extends DraughtsPlayer{

    private final RussianDraughtsManager game;
    private CheckerRenderable selectedChecker;
    public HumanPlayer(GameManager game, CheckerColor color, Set<Checker> checkers) {
        super(game, color, checkers);
        this.game = (RussianDraughtsManager) game;
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }

    @Override
    public void init(GameInputRegistry registry) {
        registry.register(this);
    }

    @Override
    public void mouseClicked(Point position, int button) {


        Point point = new Point(position.x - BoardRenderable.MARGIN_LEFT, position.y - BoardRenderable.MARGIN_TOP);

        point.x /= BoardRenderable.BOARD_SQUARE_SCALE;
        point.y /=BoardRenderable.BOARD_SQUARE_SCALE;

        System.out.println("x: " + point.x + " y: " + point.y);

        Optional<CheckerRenderable> checkerR = this.pieces.stream().filter(checker ->
                checker.getChecker().getPoint().getX() == point.x &&
                checker.getChecker().getPoint().getY() == point.y
        ).findFirst();

        if(checkerR.isPresent()) {
            System.out.println("exist");
            if(!checkerR.get().isSelected()) {
                selectedChecker = checkerR.get();
            }else {
                selectedChecker = null;
            }
            checkerR.get().setSelected(!checkerR.get().isSelected());
        }else {
            selectedChecker.setSelected(false);
            game.getBoard().update(selectedChecker.getChecker(), point);
        }
        super.mouseClicked(position, button);
    }
}
