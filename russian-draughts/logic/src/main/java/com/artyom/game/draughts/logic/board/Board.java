package com.artyom.game.draughts.logic.board;

import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.logic.state.PlayerMove;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class Board {


    private final RussianDraughtsManager manager;

    public Board(RussianDraughtsManager manager) {
        this.manager = manager;
        genPieces();
    }

    private final Map<CheckerColor, Set<Checker>> pieces = new HashMap<>();

    public Set<Checker> getPieces(CheckerColor color) {
        return pieces.get(color);
    }

    public void update(Checker checker, Point new_position) {

        if (manager.getState() instanceof PlayerMove move && checker.getColor() != move.getColor()) return;

        List<Checker> possibleBeat = pieces.get(checker.getColor()).stream()
                .filter(checker1 -> !findAllBeatsForChecker(checker1).isEmpty()).toList();
        possibleBeat.forEach(p -> {
            System.out.println("beat all:" + p);
        });


        if(possibleBeat.isEmpty() && !isCheckerAtPos(new_position)) {
            if(checker.isKing()){
                if(     Math.abs(checker.getPoint().getX() - new_position.getX()) ==
                        Math.abs(checker.getPoint().getY() - new_position.getY())){
                    checker.getPoint().setLocation(new_position);
                    manager.nextState();
                    manager.doAction();
                }
            }else {
                if (Math.abs(checker.getPoint().getX() - new_position.getX()) == 1 &&
                    Math.abs(checker.getPoint().getY() - new_position.getY()) == 1 &&
                    (
                            (checker.getColor() == CheckerColor.BLACK && new_position.y > checker.getPoint().getY()) ||
                            (checker.getColor() == CheckerColor.WHITE && new_position.y < checker.getPoint().getY())
                    )) {
                    checker.getPoint().setLocation(new_position);
                    if((checker.getPoint().getY() == 7.0 && checker.getColor()==CheckerColor.BLACK) ||
                            (checker.getPoint().getY() == 0.0 && checker.getColor()==CheckerColor.WHITE) ){
                        checker.setKing(true);
                    }
                    manager.nextState();
                    manager.doAction();
                    System.out.println(new_position + " " + checker.getColor() + " " + checker);
                }
            }
        }


        System.out.println("here");
        if(possibleBeat.contains(checker)){
            for (Checker other : findAllBeatsForChecker(checker)) {
                System.out.println(other);
                System.out.println(checker);
                int x = (int) (checker.getPoint().getX() - other.getPoint().getX());
                int y = (int) (checker.getPoint().getY() - other.getPoint().getY());
                System.out.println((other.getPoint().getX() - x) + " " + (other.getPoint().getY() - y));
                System.out.println(new_position);
                int dx = x;
                int dy = y;
                if(checker.isKing()){
                    dx/=Math.abs(x==0?1:x);
                    dy/=Math.abs(y==0?1:y);
                }
                if((new_position.x == (int) other.getPoint().getX() - dx) && // для дамк
                   (new_position.y == (int) other.getPoint().getY() - dy)){ // для дамк
                    checker.getPoint().setLocation(new_position);
                    System.out.println("beating: "+checker);
                    other.setActive(false);
                    pieces.get(other.getColor()).remove(other);
                    if((checker.getPoint().getY() == 7.0 && checker.getColor()==CheckerColor.BLACK) ||
                            (checker.getPoint().getY() == 0.0 && checker.getColor()==CheckerColor.WHITE) ){
                        checker.setKing(true);
                    }
                    if(findAllBeatsForChecker(checker).isEmpty()) {
                        manager.nextState();
                        manager.doAction();
                    }
                }
            }
        }

    }

    public void genPieces() {
        this.pieces.put(CheckerColor.WHITE, Checker.builder(CheckerColor.WHITE).build());
        this.pieces.put(CheckerColor.BLACK, Checker.builder(CheckerColor.BLACK).build());
    }

    public List<Checker> findAllBeatsForChecker(Checker checker){
        return  pieces.get(checker.getColor().next()).stream().filter(other -> {
            int x = (int) (other.getPoint().getX() - checker.getPoint().getX());
            int y = (int) (other.getPoint().getY() - checker.getPoint().getY());
            var point = new Object() {
                int x1 = (int) (other.getPoint().getX());
                int y1 = (int) (other.getPoint().getY());
            };
            if(checker.isKing()){
                point.x1 += x/Math.abs(x==0?1:x);
                point.y1 += y/Math.abs(y==0?1:y);
            }else {
                point.x1 += x;
                point.y1 += y;
            }
            boolean isValidKingMove = ((Math.abs(x) == Math.abs(y)) && checker.isKing());
            boolean isValidCheckerMove = (Math.abs(x) == 1) && (Math.abs(y) == 1) && !checker.isKing();
            boolean isInBorder = point.x1 >= 0 && point.x1 < 8 && point.y1 >= 0 && point.y1 < 8;
            return  (isValidKingMove || isValidCheckerMove) && isInBorder &&
                    pieces.values().stream().flatMap(Collection::stream).noneMatch(checker2 ->
                            checker2.getPoint().getX() == point.x1 &&
                            checker2.getPoint().getY() == point.y1
                    );
        }).toList();
    }

    public boolean isCheckerAtPos(Point2D position){
        return this.pieces.values().stream().anyMatch(color -> color.stream().anyMatch(checker -> ((int)checker.getPoint().getX() == (int) position.getX()) &&
                ((int)checker.getPoint().getY() == (int) position.getY())));
    }

}
