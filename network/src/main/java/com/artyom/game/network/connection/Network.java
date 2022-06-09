package com.artyom.game.network.connection;
import java.io.*;
import java.net.Socket;

public class Network<T extends Enum<T>, V extends Serializable> implements Closeable, Runnable {

    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    private final NetworkListener<T, V> networkListener;

    public Network(Socket socket, NetworkListener<T, V>  networkListener) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.networkListener = networkListener;
        Thread networkThread = new Thread(this);
        networkThread.start();
    }

    public void send(Message<T, V>  message) throws IOException {
        synchronized (this.out) {
            out.writeObject(message);
        }
    }
    @SuppressWarnings("unchecked")
    public Message<T, V>  receive() throws IOException, ClassNotFoundException {
        synchronized (this.in) {
            return ( Message<T, V>) in.readObject();
        }
    }

    @Override
    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    @Override
    public void run() {
        while (!socket.isClosed()){
            try {
                this.networkListener.onMessage(this.receive());
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }
    }
}