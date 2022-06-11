package com.artyom.game.draughts.graphics.player;

import java.awt.*;
import java.io.Serializable;

public record CheckerPlayerEvent(EventType type, Point position) implements Serializable {

}
