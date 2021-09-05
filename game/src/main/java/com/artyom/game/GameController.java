package com.artyom.game;

import com.artyom.game.api.GameComponents;
import com.artyom.game.engine.Renderer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    TableView<GameInfo> table = new TableView<>();

    @FXML
    Button chooseFile;

    @FXML
    Button runSelectedGame;

    final FileChooser fileChooser = new FileChooser();

    ObservableList<GameInfo> games = FXCollections.observableArrayList();

    private final GameLoader loader = new GameLoader();

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        table.setItems(games);
        TableColumn<GameInfo, ImageView> iconColumn = new TableColumn<>("icon");
        iconColumn.setCellValueFactory(new PropertyValueFactory<>("image"));
        table.getColumns().add(iconColumn);

        TableColumn<GameInfo, Integer> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        table.getColumns().add(nameColumn);
    }

    public void setStageAndSetupListeners(Stage primaryStage) {
        chooseFile.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                System.out.println(file);
                try {
                    games.add(loader.loadGame(file));
                } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        runSelectedGame.setOnAction(event -> {
            GameComponents components = table.getSelectionModel().getSelectedItem().getGame().module().run();
            Renderer renderer = new Renderer(new GameScreen(components));
            renderer.start();
        });
    }
}
