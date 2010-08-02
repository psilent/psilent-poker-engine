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
import org.junit.Test;
import static org.junit.Assert.*;

public class HandEvaluator_Test
{
    @Test (expected=NullPointerException.class)
    public void should_throw_when_constructed_with_null_hand()
    {
        new HandEvaluator(null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void should_throw_when_constructed_with_less_than_5_cards()
    {
        new HandEvaluator(new ArrayList<Card>());
    }

    @Test (expected=IllegalArgumentException.class)
    public void should_throw_when_constructed_with_duplicates()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));

        new HandEvaluator(new ArrayList<Card>());
    }

    @Test (expected=UnsupportedOperationException.class)
    public void should_throw_when_modifying_hand()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);
       hand.getHand().clear();
    }

    @Test
    public void should_identify_a_royal_flush()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.ROYAL_FLUSH, hand.getRank());
    }

    @Test
    public void should_identify_a_royal_flush_unsorted()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.ROYAL_FLUSH, hand.getRank());
    }
    
    @Test
    public void should_identify_a_straight_flush()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.STRAIGHT_FLUSH, hand.getRank());
    }

    @Test
    public void should_identify_a_straight_flush_ace_low()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.STRAIGHT_FLUSH, hand.getRank());
    }

    @Test
    public void should_identify_four_of_a_kind()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.SPADES));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);

       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.FOUR_OF_A_KIND, hand.getRank());
    }

    @Test
    public void should_identify_a_full_house_pair_low()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.FULL_HOUSE, hand.getRank());
    }

    @Test
    public void should_identify_a_full_house_pair_high()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.FULL_HOUSE, hand.getRank());
    }

    @Test
    public void should_identify_a_flush()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.NINE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.FLUSH, hand.getRank());
    }

    @Test
    public void should_identify_a_straight()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(cardList) && cardList.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.STRAIGHT, hand.getRank());
    }

    @Test
    public void should_identify_three_of_a_kind_first_three()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.SIX, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.THREE_OF_A_KIND, hand.getRank());
    }

    @Test
    public void should_identify_three_of_a_kind_middle_three()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.THREE_OF_A_KIND, hand.getRank());
    }

    @Test
    public void should_identify_three_of_a_kind_last_three()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.SPADES));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.THREE_OF_A_KIND, hand.getRank());
    }

    @Test
    public void should_identify_two_pair_first_four()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.TWO_PAIR, hand.getRank());
    }

    @Test
    public void should_identify_two_pair_last_four()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.SPADES));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.TWO, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.TWO_PAIR, hand.getRank());
    }

    @Test
    public void should_identify_two_pair_split()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.SPADES));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.TWO_PAIR, hand.getRank());
    }

    @Test
    public void should_identify_one_pair_first_two()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.QUEEN, Card.Suit.SPADES));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.ONE_PAIR, hand.getRank());
    }

    @Test
    public void should_identify_one_pair_last_two()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.DIAMONDS));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.ONE_PAIR, hand.getRank());
    }

    @Test
    public void should_identify_high_card()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));

       List<Card> returnedHand = new ArrayList<Card>(cardList);

       cardList.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.THREE, Card.Suit.SPADES));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.HEARTS));
       cardList.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS));

       HandEvaluator hand = new HandEvaluator(cardList);
       assertEquals(true, hand.getHand().containsAll(returnedHand) && returnedHand.size() == hand.getHand().size());
       assertEquals(HandEvaluator.Rank.HIGH_CARD, hand.getRank());
    }
}
