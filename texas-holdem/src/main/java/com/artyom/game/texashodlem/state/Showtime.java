package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.texashodlem.cards.Card;
import com.artyom.game.texashodlem.TexasHoldem;
import com.artyom.game.texashodlem.cards.Combo;
import com.artyom.game.texashodlem.cards.Rank;
import com.artyom.game.texashodlem.players.TexasHoldemPlayer;

import java.util.*;
import java.util.stream.Collectors;

public class Showtime implements GameState {
    @Override
    public void next(GameManager game) {
        game.setState(new EndRound());
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        if (game instanceof TexasHoldem texasHoldem)
            texasHoldem.setWinner(
                    texasHoldem.getPlayers().stream()
                            .map(player -> (TexasHoldemPlayer) player)
                            .peek(player -> player.getCards().forEach(Card::show))
                            .peek(TexasHoldemPlayer::countCombos)
                            .sorted((player1, player2) -> {
                                Map.Entry<Combo, Integer> p1_combos = player1.getCombos().entrySet().stream()
                                        .filter(e -> e.getValue() != -1)
                                        .min(Map.Entry.comparingByKey()).orElseThrow();
                                Map.Entry<Combo, Integer> p2_combos = player2.getCombos().entrySet().stream()
                                        .filter(e -> e.getValue() != -1)
                                        .min(Map.Entry.comparingByKey()).orElseThrow();

                                int t = p1_combos.getKey().compareTo(p2_combos.getKey());
                                System.out.println(p1_combos + " " + p2_combos + " => " + t + " " + p2_combos.getValue().compareTo(p1_combos.getValue()));
                                if (t == 0) {
                                    return p2_combos.getValue().compareTo(p1_combos.getValue());
                                }
                                return t;
                            }).peek(System.out::println).collect(Collectors.toList()).get(0));
                /*.forEach(player -> {
                    System.out.print(player + " ");
                    System.out.print(Rank.toEnum(player.getMaxRank()) + " ");
                    System.out.println(player.getCombos().entrySet().stream().filter(e -> e.getValue() != -1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
                });*/
        game.nextState();
    }
}
