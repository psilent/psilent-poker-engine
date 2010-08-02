//   Copyright 2010 Arnold B. Spence
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package net.psilent.pokerengine;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Game_deal_Test {

    private Game game;

    @Before
    public void init()
    {
        game = new Game(null, null);
    }

    @Test (expected=IllegalStateException.class)
    public void deal_should_throw_if_balance_less_than_5()
    {
        game.deal();
    }

    @Test (expected=IllegalStateException.class)
    public void deal_should_throw_if_not_in_game_over_state()
    {
        game.setBalance(100);
        game.deal();
        game.deal();
    }

    @Test
    public void deal_should_reduce_balance_by_5()
    {
        int startingBalance = 100;

        game.setBalance(startingBalance);
        game.deal();
        assertEquals(startingBalance - 5, game.getBalance());
    }

    @Test
    public void deal_should_put_game_in_draw_state()
    {
        game.setBalance(100);
        game.deal();
        assertEquals(Game.State.DRAW, game.getState());
    }

    @Test
    public void  deal_should_give_5_cards_to_the_player()
    {
        game.setBalance(100);
        game.deal();
        assertEquals(5, game.getPlayerCards().size());
    }

    @Test
    public void deal_should_leave_47_cards_in_the_deck()
    {
        game.setBalance(100);
        game.deal();
        assertEquals(47, game.getDeck().size());
    }

    @Test
    public void deal_should_leave_no_discards()
    {
        game.setBalance(100);
        game.deal();
        assertEquals(0, game.getDiscards().size());
    }

    @Test
    public void deal_should_result_in_all_decks_combined_being_same_as_a_new_deck()
    {
        game.setBalance(100);
        game.deal();

        List<Card> allCards = new ArrayList<Card>();
        allCards.addAll(game.getDeck());
        allCards.addAll(game.getDiscards());
        allCards.addAll(game.getPlayerCards());

        assertEquals(true, allCards.size() == 52);
        assertEquals(true, allCards.containsAll(Card.newStandardDeck()));
    }

    @Test
    public void deal_should_reset_lastWinAmount_to_0()
    {
        game.setBalance(100);
        game.deal();
        assertEquals(0, game.getLastWinAmount());
    }

    @Test
    public void deal_should_reset_lastWinRank_to_NONE()
    {
        game.setBalance(100);
        game.deal();
        assertEquals(HandEvaluator.Rank.NONE, game.getLastWinRank());
    }

    @Test
    public void deal_should_reset_lastWinHand_to_empty()
    {
        game.setBalance(100);
        game.deal();
        assertEquals(0, game.getLastWinHand().size());
    }

}