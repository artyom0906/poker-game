package com.artyom.game;

import com.artyom.game.api.GameModule;
import com.artyom.game.api.GameScreen;
import com.artyom.game.api.ModuleConfiguration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class GameLoader {
    public GameInfo loadGame(File file) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        URLClassLoader child = new URLClassLoader(
                new URL[] {file.toURI().toURL()},
                ClassLoader.getSystemClassLoader()
        );
        Properties gameProperties = new Properties();
        gameProperties.load(child.getResourceAsStream("gameconfig.properties"));
        Map<String, String> mapProp = gameProperties.entrySet().stream().collect(
                Collectors.toMap(
                        e -> (String) e.getKey(),
                        e -> (String) e.getValue()
                ));
        System.err.println(mapProp.get("game"));
        GameModule module = (GameModule) child.loadClass(mapProp.get("game")).getDeclaredConstructor().newInstance();
        Game game = new Game(module, module.load());
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(child.getResourceAsStream(game.configuration().getImage()))));
        double width2 = imageView.getImage().getWidth()/imageView.getImage().getHeight()*90d;
        imageView.setFitHeight(90d);
        imageView.setFitWidth(width2);
        return new GameInfo(imageView, game.configuration().getName(), game);

        //ServiceLoader<GameScreen> serviceLoader = ServiceLoader.load(GameScreen.class, child);
        //System.err.println("init");
        //serviceLoader.forEach(gameScreen -> {
        //	gameScreen.init();
        //	System.out.println(gameScreen);
        //});
    }
}
