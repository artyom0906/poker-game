package com.artyom.game.draughts.logic.board;

import com.artyom.game.api.GameManager;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.logic.state.PlayerMove;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

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

        /*System.out.println(checker.getPoint() + " " + checker.getColor() + " " +
                other.getPoint() + " " + other.getColor() + " " +
                Math.abs(checker.getPoint().getX() - other.getPoint().getX()) + " " +
                Math.abs(checker.getPoint().getY() - other.getPoint().getY()));*/
        List<Checker> possibleBeat = pieces.get(checker.getColor()).stream()
                .filter(checker1 -> !findAllBeatsForChecker(checker1).isEmpty()).toList();
        possibleBeat.forEach(p -> {
            System.out.println("beat all:" + p);
        });


        if (Math.abs(checker.getPoint().getX() - new_position.getX()) == 1 &&
            Math.abs(checker.getPoint().getY() - new_position.getY()) == 1 && possibleBeat.isEmpty() &&
            ((checker.getColor()==CheckerColor.BLACK && new_position.y > checker.getPoint().getY()) ||
             (checker.getColor()==CheckerColor.WHITE && new_position.y < checker.getPoint().getY()))) {


            checker.getPoint().setLocation(new_position);
            manager.nextState();
            manager.doAction();
            System.out.println(new_position + " " + checker.getColor() + " " + checker);
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
                if((new_position.x == (int) other.getPoint().getX() - x/Math.abs(x==0?1:x)) && // для дамк
                   (new_position.y == (int) other.getPoint().getY() - y/Math.abs(y==0?1:y))){ // для дамк
                    checker.getPoint().setLocation(new_position);
                    System.out.println("beating: "+checker);
                    other.setActive(false);
                    pieces.get(other.getColor()).remove(other);
                    if(findAllBeatsForChecker(checker).isEmpty()) {
                        manager.nextState();
                        manager.doAction();
                    }
                }
            }
        }

        System.out.println(checker + " " + (checker.getPoint().getY() == 0.0));
        if((checker.getPoint().getY() == 7.0 && checker.getColor()==CheckerColor.BLACK) ||
           (checker.getPoint().getY() == 0.0 && checker.getColor()==CheckerColor.WHITE) ){
            checker.setKing(true);
        }
        System.out.println(checker.isKing());

        System.out.println(pieces.size());
    }

    public void genPieces() {
        this.pieces.put(CheckerColor.WHITE, Checker.builder(CheckerColor.WHITE).build());
        this.pieces.put(CheckerColor.BLACK, Checker.builder(CheckerColor.BLACK).build());
    }

    private List<Checker> findAllBeatsForChecker(Checker checker){
        return  pieces.get(checker.getColor().next()).stream().filter(other -> {
            int x = (int) (other.getPoint().getX() - checker.getPoint().getX());
            int y = (int) (other.getPoint().getY() - checker.getPoint().getY());
            int x1 = (int)(other.getPoint().getX() + x/Math.abs(x==0?1:x)); // abs для дамок
            int y1 =(int)(other.getPoint().getY() +  y/Math.abs(y==0?1:y));
            return (Math.abs(x) == Math.abs(y)) && x1 >= 0 && x1 < 8 && y1 >= 0 && y1 < 8 && // abs(x) == 1 для шашки
                    pieces.values().stream().flatMap(Collection::stream).noneMatch(checker2 ->
                            checker2.getPoint().getX() == x1 &&
                            checker2.getPoint().getY() == y1
                    );
        }).toList();
    }

}
