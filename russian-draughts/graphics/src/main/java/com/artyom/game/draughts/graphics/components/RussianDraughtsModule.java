package com.artyom.game.draughts.graphics.components;

import com.artyom.game.api.*;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.graphics.player.ComputerPlayer;
import com.artyom.game.draughts.graphics.player.HumanPlayer;
import com.artyom.game.engine.Renderer;
/*
import com.artyom.game.engine.Renderer;
*/

import javax.swing.JComponent;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class RussianDraughtsModule implements GameModule {
    public RussianDraughtsModule(){

    }
    @Override
    public ModuleConfiguration load() {
        return new RussianDraughtsConfig();
    }

    @Override
    public GameComponents run() {
        System.out.println("run");
        RussianDraughtsManager russianDraughtsManager = new RussianDraughtsManager();

        CheckerColor humanColor = new Random().nextInt(2)==0? CheckerColor.WHITE:CheckerColor.BLACK;

        Set<Checker> humanCheckers = russianDraughtsManager.getBoard().getPieces(humanColor)
                .stream()
                .filter(checker -> checker.getColor().equals(humanColor))
                .collect(Collectors.toSet());
        new HumanPlayer(russianDraughtsManager, humanColor, humanCheckers);

        CheckerColor computerColor = humanColor.next();
        Set<Checker> computerCheckers = russianDraughtsManager.getBoard().getPieces(computerColor)
                .stream()
                .filter(checker -> checker.getColor().equals(computerColor))
                .collect(Collectors.toSet());

        new HumanPlayer(russianDraughtsManager, computerColor, computerCheckers);

        return new GameComponents(russianDraughtsManager, new RussianDraughtsScreen(russianDraughtsManager));
    }

    @Override
    public void unload() {

    }

    @Override
    public JComponent getConfigurationPage() {
        return null;
    }

    public static void main(String[] args) {
        GameInputRegistry registry = new GameInputRegistry();
        RussianDraughtsManager manager= new RussianDraughtsManager();
        Renderer renderer = new Renderer(
                new com.artyom.game.GameScreen(
                    new GameComponents(
                            manager,
                            new RussianDraughtsScreen(manager)
                    ), registry)
                , registry);
        renderer.start();
    }
}

