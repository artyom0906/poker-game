package com.artyom.game.draughts.graphics.components;

import com.artyom.game.api.ConfigComponent;
import com.artyom.game.draughts.graphics.player.CheckerPlayerEvent;
import com.artyom.game.draughts.graphics.player.EventType;
import com.artyom.game.draughts.graphics.player.HumanPlayer;
import com.artyom.game.draughts.graphics.player.NetworkPlayer;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.network.connection.Message;
import com.artyom.game.network.connection.Network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Set;

public class ConfigMenu extends ConfigComponent {
    public ConfigMenu(ActionListener listener, RussianDraughtsManager manager, RussianDraughtsScreen screen){
        super(listener);

        JRadioButton option1 = new JRadioButton("Connect");
        option1.setMnemonic(0);
        JRadioButton option2 = new JRadioButton("Host");
        option2.setMnemonic(1);
        JRadioButton option3 = new JRadioButton("Local");
        option3.setMnemonic(2);

        option1.setBounds(50, 20, 100, 30);
        option2.setBounds(150, 20, 100, 30);
        option3.setBounds(250, 20, 100, 30);

        option3.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

        add(option1);
        add(option2);
        add(option3);

        JButton button = new JButton("start the game");
        button.setBounds(50, 50, 200, 30);
        button.setPreferredSize(new Dimension(150, 40));
        button.setActionCommand("start");
        button.addActionListener(listener);

        JTextField address = new JTextField();
        address.setBounds(50,100, 200,30);
        address.setSize(new Dimension(150, 40));
        address.setEnabled(false);
        option1.addActionListener(e -> address.setEnabled(true));
        option2.addActionListener(e -> address.setEnabled(false));
        option3.addActionListener(e -> address.setEnabled(false));

        button.addActionListener((event)->{
            switch (group.getSelection().getMnemonic()){
                case 0 -> {
                    //CheckerColor humanColor = new Random().nextInt(2)==0? CheckerColor.WHITE:CheckerColor.BLACK;

                    CheckerColor humanColor = CheckerColor.BLACK;

                    Set<Checker> humanCheckers = manager.getBoard().getPieces(humanColor);
                    var ref = new Object() {
                        Network<EventType, CheckerPlayerEvent> network = null;
                    };
                    HumanPlayer humanPlayer = new HumanPlayer(manager, humanColor, humanCheckers, event1 -> {
                        try {
                            ref.network.send(new Message<>(event1.type(), event1));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    CheckerColor computerColor = humanColor.next();
                    Set<Checker> computerCheckers = manager.getBoard().getPieces(computerColor);
                    NetworkPlayer player = new NetworkPlayer(manager, computerColor, computerCheckers);
                    try {
                        ref.network = new Network<>(new Socket(address.getText(), 1234), player);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    humanPlayer.init(screen.getRegistry());
                    player.init(screen.getRegistry());
                }
                case 1 -> {
                    //CheckerColor humanColor = new Random().nextInt(2)==0? CheckerColor.WHITE:CheckerColor.BLACK;

                    CheckerColor humanColor = CheckerColor.WHITE;

                    Set<Checker> humanCheckers = manager.getBoard().getPieces(humanColor);
                    var ref = new Object() {
                        Network<EventType, CheckerPlayerEvent> network = null;
                    };
                    HumanPlayer humanPlayer = new HumanPlayer(manager, humanColor, humanCheckers, event1 -> {
                        try {
                            ref.network.send(new Message<>(event1.type(), event1));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    CheckerColor computerColor = humanColor.next();
                    Set<Checker> computerCheckers = manager.getBoard().getPieces(computerColor);
                    NetworkPlayer player = new NetworkPlayer(manager, computerColor, computerCheckers);
                    try(ServerSocket serverSocket = new ServerSocket(1234)) {
                        ref.network = new Network<>(serverSocket.accept(), player);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    humanPlayer.init(screen.getRegistry());
                    player.init(screen.getRegistry());
                }
                case 2 -> {
                    CheckerColor humanColor = new Random().nextInt(2)==0? CheckerColor.WHITE:CheckerColor.BLACK;

                    Set<Checker> humanCheckers = manager.getBoard().getPieces(humanColor);
                    HumanPlayer player1 =  new HumanPlayer(manager, humanColor, humanCheckers);

                    CheckerColor computerColor = humanColor.next();
                    Set<Checker> computerCheckers = manager.getBoard().getPieces(computerColor);
                    HumanPlayer player2 = new HumanPlayer(manager, computerColor, computerCheckers);

                    player1.init(screen.getRegistry());
                    player2.init(screen.getRegistry());
                }
                default -> {}
            }
            System.out.println(address.getText());
        });

        this.add(button);
        this.add(address);
        setLayout(null);
    }
}
