package com.artyom.game.draughts.graphics.player;

import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.GameManager;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.graphics.board.BoardRenderer;
import com.artyom.game.draughts.graphics.checker.CheckerRenderer;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.logic.state.PlayerMove;

import java.awt.*;
import java.util.Optional;
import java.util.Set;

public class HumanPlayer extends DraughtsPlayer{

    private final RussianDraughtsManager game;
    private final PlayerEventListener listener;

    public HumanPlayer(GameManager game, CheckerColor color, Set<Checker> checkers) {
        this(game, color, checkers, null);
    }
    public HumanPlayer(GameManager game, CheckerColor color, Set<Checker> checkers, PlayerEventListener listener) {
        super(game, color, checkers);
        this.listener = listener;
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

        Point point = new Point(position.x - BoardRenderer.MARGIN_LEFT, position.y - BoardRenderer.MARGIN_TOP);

        point.x /= BoardRenderer.BOARD_SQUARE_SCALE;
        point.y /= BoardRenderer.BOARD_SQUARE_SCALE;

        System.out.println("x: " + point.x + " y: " + point.y);

        Optional<Checker> checkerR = this.render.getCheckers().stream().filter(checker ->
                checker.getPoint().getX() == point.x &&
                checker.getPoint().getY() == point.y && checker.isActive()
        ).findFirst();
        System.out.println(game.getState());
        CheckerPlayerEvent event = null;
        if(checkerR.isPresent() && game.getState() instanceof PlayerMove move &&
                checkerR.get().getColor().equals(move.getColor())) {
            System.out.println("exist");
            if(render.getSelected()==null || render.getSelected()!=null && !checkerR.get().equals(render.getSelected())) {
                render.setSelected(checkerR.get());
                event = new CheckerPlayerEvent(EventType.SelectChecker, point);
            }else {
                render.setSelected(null);
                event = new CheckerPlayerEvent(EventType.UnselectChecker, null);
            }
        }else if(render.getSelected()!=null){
            event = new CheckerPlayerEvent(EventType.MakeMove, point);
            game.getBoard().update(render.getSelected(), point);
            render.setSelected(null);
        }
        if(listener!=null && event!=null) listener.handle(event);
        super.mouseClicked(position, button);
    }


}
