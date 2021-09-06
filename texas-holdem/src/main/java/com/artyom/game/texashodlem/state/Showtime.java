package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.texashodlem.cards.Card;
import com.artyom.game.texashodlem.TexasHoldem;
import com.artyom.game.texashodlem.cards.Combo;
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
        }).peek(player -> {
            player.countCombos();
            player.setMaxRank(player.getCards().stream().max(Comparator.comparing(Card::getRank)).orElseThrow().getRank().getId());
        }).sorted(Comparator.nullsLast((player1, player2) -> {
            TexasHoldemPlayer p1 = (TexasHoldemPlayer)player1;
            TexasHoldemPlayer p2 = (TexasHoldemPlayer)player2;
            Integer p1_combos = p1.getCombos().stream().map(Combo::getId).reduce(0, Integer::sum);
            Integer p2_combos = p2.getCombos().stream().map(Combo::getId).reduce(0, Integer::sum);
            int comp = p1_combos.compareTo(p2_combos);
            if(comp!=0) return comp;
            return Integer.compare(p1.getMaxRank(), p2.getMaxRank());
        }).reversed()).collect(Collectors.toList());
        players.forEach(player ->{
            System.out.print(player + " ");
            System.out.print(player.getMaxRank() + " ");
            System.out.println(player.getCombos());
        });
    }


}
