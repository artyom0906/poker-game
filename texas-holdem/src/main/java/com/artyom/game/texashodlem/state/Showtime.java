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
        List<TexasHoldemPlayer> players = game.getPlayers().stream().map(player -> (TexasHoldemPlayer)player).peek(player -> {
            player.getCards().forEach(Card::show);
        }).peek(TexasHoldemPlayer::countCombos).sorted(Comparator.nullsLast((player1, player2) -> {
            TexasHoldemPlayer p1 = (TexasHoldemPlayer)player1;
            TexasHoldemPlayer p2 = (TexasHoldemPlayer)player2;
            Integer p1_combos = p1.getCombos().entrySet().stream().filter(e->e.getValue()!=-1).max(Map.Entry.comparingByKey()).get().getValue();
            Integer p2_combos = p2.getCombos().entrySet().stream().filter(e->e.getValue()!=-1).max(Map.Entry.comparingByKey()).get().getValue();
            return p1_combos.compareTo(p2_combos);
        }).reversed()).collect(Collectors.toList());
        players.forEach(player ->{
            System.out.print(player + " ");
            System.out.print(Rank.toEnum(player.getMaxRank()) + " ");
            System.out.println(player.getCombos().entrySet().stream().filter(e->e.getValue()!=-1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        });
        game.nextState();
    }
}
