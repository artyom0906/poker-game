package com.artyom.game;

import com.artyom.game.Game;
import com.artyom.game.GameLoader;
import com.artyom.game.GameScreen;
import com.artyom.game.api.GameComponents;
import com.artyom.game.engine.Renderer;
import com.artyom.game.texashodlem.players.HumanPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class Main extends Application {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		GameLoader loader = new GameLoader();
		GameInfo info = loader.loadGame(new File("C:\\Users\\matro\\Documents\\kursach\\poker-game\\texas-holdem\\build\\libs"));
		GameComponents components = info.getGame().module().run();
		Renderer renderer = new Renderer(new GameScreen(components));
		renderer.start();

		//launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		URL xmlUrl = getClass().getResource("/mainScene.fxml");
		loader.setLocation(xmlUrl);
		Parent root = loader.load();
		GameController controller = (GameController)loader.getController();
		controller.setStageAndSetupListeners(primaryStage);

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}