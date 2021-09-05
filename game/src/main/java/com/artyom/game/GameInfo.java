package com.artyom.game;

import javafx.scene.image.ImageView;

public class GameInfo {
    private ImageView image;
    private String gameName;
    private Game game;

    public GameInfo(ImageView img, String gameName, Game game) {
        this.image = img;
        this.gameName = gameName;
        this.game = game;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
