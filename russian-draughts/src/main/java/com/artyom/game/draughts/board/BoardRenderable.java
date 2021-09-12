package com.artyom.game.draughts.board;

import com.artyom.game.api.Entity;
import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.checker.Checker;
import com.artyom.game.draughts.checker.CheckerRenderable;

import java.awt.*;
import java.util.stream.Collectors;

public class BoardRenderable extends Board<CheckerRenderable> implements Entity {

    public BoardRenderable(Board<Checker> board) {
        this.getPieces().addAll(board.getPieces().stream().map(CheckerRenderable::new).collect(Collectors.toList()));
    }

    @Override
    public void init(GameInputRegistry registry) {
        registry.register(this);
    }

    @Override
    public void update(Input input) {
        this.getPieces().forEach(piece->piece.update(input));
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i%2==j%2)
                    g.setColor(new Color(0xEEEED2));
                else
                    g.setColor(new Color(0x769756));
                g.fillRect(Board.MARGIN_LEFT+i*Board.BOARD_SQUARE_SCALE, Board.MARGIN_TOP+j*Board.BOARD_SQUARE_SCALE, Board.BOARD_SQUARE_SCALE, Board.BOARD_SQUARE_SCALE);
            }
        }
        this.getPieces().forEach(piece->piece.render(g));
    }
}
