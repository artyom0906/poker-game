package com.artyom.game.network.connection;

import java.io.Serializable;

public record Message<T extends Enum<T>, V extends Serializable>(T messageType, V message) implements Serializable {
}
