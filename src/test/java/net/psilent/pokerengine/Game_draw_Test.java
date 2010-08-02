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
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Game_draw_Test {

    private Game game;

    @Before
    public void init()
    {
        game = new Game(null, null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void draw_should_throw_when_called_with_duplicates()
    {
        game.setBalance(100);
        game.deal();

        List<Card> cards = new ArrayList<Card>();
        cards.add(game.getPlayerCards().get(0));
        cards.add(game.getPlayerCards().get(0));

        game.draw(cards);
    }

    @Test (expected=IllegalArgumentException.class)
    public void draw_should_throw_when_called_with_non_plyer_cards()
    {
        game.setBalance(100);
        game.deal();

        List<Card> cards = new ArrayList<Card>();
        cards.add(new Card(game.getPlayerCards().get(0).getRank(), game.getPlayerCards().get(0).getSuit()));

        game.draw(cards);
    }

    @Test (expected=IllegalStateException.class)
    public void draw_should_throw_if_not_in_draw_state()
    {
        game.setBalance(100);
        game.draw(new ArrayList<Card>());
    }

    @Test
    public void draw_should_put_game_in_game_over_state()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>();
        discardedCards.add(game.getPlayerCards().get(0));
        discardedCards.add(game.getPlayerCards().get(3));
        discardedCards.add(game.getPlayerCards().get(4));

        game.draw(discardedCards);
        assertEquals(Game.State.DEAL, game.getState());
    }

    @Test
    public void draw_should_remove_discarded_cards_from_player_cards()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>();
        discardedCards.add(game.getPlayerCards().get(0));
        discardedCards.add(game.getPlayerCards().get(3));
        discardedCards.add(game.getPlayerCards().get(4));

        game.draw(discardedCards);
        assertEquals(true, Collections.disjoint(game.getPlayerCards(), discardedCards));
    }

    @Test
    public void draw_should_replace_discarded_cards_with_draw_cards()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>();
        discardedCards.add(game.getPlayerCards().get(0));
        discardedCards.add(game.getPlayerCards().get(3));
        discardedCards.add(game.getPlayerCards().get(4));

        List<Card> drawCards = new ArrayList<Card>();
        for(int i = 0; i < discardedCards.size(); i++)
        {
            drawCards.add(game.getDeck().get(i));
        }

        game.draw(discardedCards);
        assertEquals(true, game.getPlayerCards().containsAll(drawCards));
    }

    @Test
    public void draw_should_place_discarded_cards_in_discards()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>();
        discardedCards.add(game.getPlayerCards().get(0));
        discardedCards.add(game.getPlayerCards().get(3));
        discardedCards.add(game.getPlayerCards().get(4));

        game.draw(discardedCards);
        assertEquals(true, game.getDiscards().containsAll(discardedCards));
    }

    @Test
    public void draw_should_remove_draw_cards_from_deck()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>();
        discardedCards.add(game.getPlayerCards().get(0));
        discardedCards.add(game.getPlayerCards().get(3));
        discardedCards.add(game.getPlayerCards().get(4));

        List<Card> drawCards = new ArrayList<Card>();
        for(int i = 0; i < discardedCards.size(); i++)
        {
            drawCards.add(game.getDeck().get(i));
        }

        game.draw(discardedCards);
        assertEquals(true, Collections.disjoint(game.getDeck(), drawCards));
    }

    @Test
    public void draw_should_result_in_all_decks_combined_being_same_as_a_new_deck()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>();
        discardedCards.add(game.getPlayerCards().get(0));
        discardedCards.add(game.getPlayerCards().get(3));
        discardedCards.add(game.getPlayerCards().get(4));

        game.draw(discardedCards);

        List<Card> allCards = new ArrayList<Card>();
        allCards.addAll(game.getDeck());
        allCards.addAll(game.getDiscards());
        allCards.addAll(game.getPlayerCards());

        assertEquals(true, allCards.size() == 52);
        assertEquals(true, allCards.containsAll(Card.newStandardDeck()));
    }

    @Test
    public void drawing_no_cards_should_work()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>();

        game.draw(discardedCards);
        assertEquals(Game.State.DEAL, game.getState());
    }

    @Test
    public void drawing_all_cards_should_work()
    {
        game.setBalance(100);
        game.deal();

        List<Card> discardedCards = new ArrayList<Card>(game.getPlayerCards());

        game.draw(discardedCards);
        assertEquals(Game.State.DEAL, game.getState());
    }
}