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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Game_Test
{
    private Game game;

    @Before
    public void init()
    {
        game = new Game(null, null);
    }

    @Test
    public void should_start_with_0_balance()
    {
        assertEquals(0, game.getBalance());
    }

    @Test
    public void should_start_in_the_game_over_state()
    {
        assertEquals(Game.State.DEAL, game.getState());
    }

    @Test
    public void should_have_no_player_cards()
    {
        assertEquals(0, game.getPlayerCards().size());
    }

    @Test
    public void should_have_no_cards_in_discards()
    {
        assertEquals(0, game.getDiscards().size());
    }

    @Test
    public void should_have_all_52_standard_cards_in_deck()
    {
        assertEquals(true, game.getDeck().size() == 52);
        assertEquals(true, game.getDeck().containsAll(Card.newStandardDeck()));
    }

    @Test
    public void should_start_with_lastWinAmount_of_0()
    {
        assertEquals(0, game.getLastWinAmount());
    }

    @Test
    public void should_start_with_lastWinRank_of_NONE()
    {
        assertEquals(HandEvaluator.Rank.NONE, game.getLastWinRank());
    }

    @Test
    public void should_start_with_empty_lastWinHand()
    {
        assertEquals(0, game.getLastWinHand().size());
    }

    @Test
    public void should_allow_balance_to_be_set()
    {
        int newBalance = 5;
        game.setBalance(newBalance);
        assertEquals(newBalance, game.getBalance());
    }

    @Test (expected=IllegalArgumentException.class)
    public void setBalance_should_throw_when_passed_a_value_less_than_5()
    {
        game.setBalance(0);
    }

    @Test (expected=IllegalArgumentException.class)
    public void setBalance_should_throw_when_passed_a_value_not_divisible_by_5()
    {
        game.setBalance(7);
    }

    @Test (expected=UnsupportedOperationException.class)
    public void should_thow_when_trying_to_modify_player_cards()
    {
        game.getPlayerCards().add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
    }

    @Test (expected=UnsupportedOperationException.class)
    public void should_throw_when_trying_to_modify_discards()
    {
        game.getDiscards().add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
    }

    @Test (expected=UnsupportedOperationException.class)
    public void should_throw_when_trying_to_modify_deck()
    {
        game.getDeck().add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
    }


}