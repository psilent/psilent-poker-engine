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
import org.junit.Test;
import static org.junit.Assert.*;

public class Game_draw_evaluation_Test {

    private PayTable payTable = new StandardPayTable();

    @Test
    public void drawing_should_add_the_lastWinAmount_to_the_balance()
    {
        Game game = new Game(new Shuffler_ROYAL_FLUSH(), null);
        game.setBalance(100);
        game.deal();

        int balance = game.getBalance();
        game.draw(new ArrayList<Card>());

        assertEquals(balance + game.getLastWinAmount(), game.getBalance());
    }

    @Test
    public void drawing_ROYAL_FLUSH_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_ROYAL_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.ROYAL_FLUSH), game.getLastWinAmount());
    }

    @Test
    public void drawing_ROYAL_FLUSH_should_result_in_lastWinRank_of_ROYAL_FLUSH()
    {
        Game game = new Game(new Shuffler_ROYAL_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.ROYAL_FLUSH, game.getLastWinRank());
    }

    @Test
    public void drawing_ROYAL_FLUSH_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_ROYAL_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_STRAIGHT_FLUSH_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_STRAIGHT_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.STRAIGHT_FLUSH), game.getLastWinAmount());
    }

    @Test
    public void drawing_STRAIGHT_FLUSH_should_result_in_lastWinRank_of_STRAIGHT_FLUSH()
    {
        Game game = new Game(new Shuffler_STRAIGHT_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.STRAIGHT_FLUSH, game.getLastWinRank());
    }

    @Test
    public void drawing_STRAIGHT_FLUSH_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_STRAIGHT_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_FOUR_OF_A_KIND_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_FOUR_OF_A_KIND(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.FOUR_OF_A_KIND), game.getLastWinAmount());
    }

    @Test
    public void drawing_FOUR_OF_A_KIND_should_result_in_lastWinRank_of_FOUR_OF_A_KIND()
    {
        Game game = new Game(new Shuffler_FOUR_OF_A_KIND(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.FOUR_OF_A_KIND, game.getLastWinRank());
    }

    @Test
    public void drawing_FOUR_OF_A_KIND_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_FOUR_OF_A_KIND(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_FULL_HOUSE_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_FULL_HOUSE(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.FULL_HOUSE), game.getLastWinAmount());
    }

    @Test
    public void drawing_FULL_HOUSE_should_result_in_lastWinRank_of_FULL_HOUSE()
    {
        Game game = new Game(new Shuffler_FULL_HOUSE(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.FULL_HOUSE, game.getLastWinRank());
    }

    @Test
    public void drawing_FULL_HOUSE_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_FULL_HOUSE(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_FLUSH_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.FLUSH), game.getLastWinAmount());
    }

    @Test
    public void drawing_FLUSH_should_result_in_lastWinRank_of_FLUSH()
    {
        Game game = new Game(new Shuffler_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.FLUSH, game.getLastWinRank());
    }

    @Test
    public void drawing_FLUSH_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_FLUSH(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_STRAIGHT_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_STRAIGHT(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.STRAIGHT), game.getLastWinAmount());
    }

    @Test
    public void drawing_STRAIGHT_should_result_in_lastWinRank_of_STRAIGHT()
    {
        Game game = new Game(new Shuffler_STRAIGHT(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.STRAIGHT, game.getLastWinRank());
    }

    @Test
    public void drawing_STRAIGHT_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_STRAIGHT(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_THREE_OF_A_KIND_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_THREE_OF_A_KIND(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.THREE_OF_A_KIND), game.getLastWinAmount());
    }

    @Test
    public void drawing_THREE_OF_A_KIND_should_result_in_lastWinRank_of_THREE_OF_A_KIND()
    {
        Game game = new Game(new Shuffler_THREE_OF_A_KIND(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.THREE_OF_A_KIND, game.getLastWinRank());
    }

    @Test
    public void drawing_THREE_OF_A_KIND_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_THREE_OF_A_KIND(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_TWO_PAIR_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_TWO_PAIR(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.TWO_PAIR), game.getLastWinAmount());
    }

    @Test
    public void drawing_TWO_PAIR_should_result_in_lastWinRank_of_TWO_PAIR()
    {
        Game game = new Game(new Shuffler_TWO_PAIR(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.TWO_PAIR, game.getLastWinRank());
    }

    @Test
    public void drawing_TWO_PAIR_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_TWO_PAIR(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_ONE_PAIR_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_ONE_PAIR(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.ONE_PAIR), game.getLastWinAmount());
    }

    @Test
    public void drawing_ONE_PAIR_should_result_in_lastWinRank_of_ONE_PAIR()
    {
        Game game = new Game(new Shuffler_ONE_PAIR(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.ONE_PAIR, game.getLastWinRank());
    }

    @Test
    public void drawing_ONE_PAIR_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_ONE_PAIR(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }

    @Test
    public void drawing_HIGH_CARD_should_set_lastWinAmount_correctly()
    {
        Game game = new Game(new Shuffler_HIGH_CARD(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(payTable.getPayout(HandEvaluator.Rank.HIGH_CARD), game.getLastWinAmount());
    }

    @Test
    public void drawing_HIGH_CARD_should_result_in_lastWinRank_of_HIGH_CARD()
    {
        Game game = new Game(new Shuffler_HIGH_CARD(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(HandEvaluator.Rank.HIGH_CARD, game.getLastWinRank());
    }

    @Test
    public void drawing_HIGH_CARD_should_set_lastWinHand_correctly()
    {
        Game game = new Game(new Shuffler_HIGH_CARD(), null);
        game.setBalance(100);
        game.deal();
        game.draw(new ArrayList<Card>());

        assertEquals(new HandEvaluator(game.getPlayerCards()).getHand(), game.getLastWinHand());
    }


}