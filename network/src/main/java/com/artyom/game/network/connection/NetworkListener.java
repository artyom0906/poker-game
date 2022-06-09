package com.artyom.game.network.connection;

import java.io.Serializable;

public interface NetworkListener<T extends Enum<T>, V extends Serializable> {
    void onMessage(Message<T, V> message);
}
