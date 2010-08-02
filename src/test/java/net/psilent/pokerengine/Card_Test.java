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

public class Card_Test
{
    public Card_Test()
    {
    }

    @Before
    public void setUp()
    {
    }

    @Test
    public void a_new_card_should_have_the_specified_values()
    {
        Card card = new Card(Card.Rank.ACE, Card.Suit.CLUBS);

        assertEquals(true, card.getRank() == Card.Rank.ACE && card.getSuit() == Card.Suit.CLUBS);
    }

    @Test
    public void a_new_standard_deck_should_have_all_52_standard_cards()
    {
        List<Card> newDeck = Card.newStandardDeck();

        for(Card.Suit suit : Card.Suit.values())
            for (Card.Rank rank : Card.Rank.values())
                assertEquals(true, newDeck.contains(new Card(rank, suit)));
        // Also make sure there are not more than 52 cards in deck
        assertEquals(52, newDeck.size());
    }

    @Test (expected=NullPointerException.class)
    public void hasDuplicates_should_throw_when_called_with_null_hand()
    {
        Card.hasDuplicates(null);
    }

    @Test
    public void hasDuplicates_should_identify_duplicates()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));

       assertEquals(true, Card.hasDuplicates(cardList));
    }

    @Test
    public void hasDuplicates_should_not_identify_duplicates_when_there_are_no_duplicates()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS));
       cardList.add(new Card(Card.Rank.SEVEN, Card.Suit.HEARTS));

       assertEquals(false, Card.hasDuplicates(cardList));
    }

    @Test (expected=NullPointerException.class)
    public void intersection_should_throw_when_called_with_null_list1()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));

       Card.intersection(null, cardList);
    }

    @Test (expected=NullPointerException.class)
    public void intersection_should_throw_when_called_with_null_list2()
    {
       List<Card> cardList = new ArrayList<Card>();
       cardList.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));

       Card.intersection(cardList, null);
    }

    @Test (expected=NullPointerException.class)
    public void intersection_should_throw_when_called_with_all_nulls()
    {
       Card.intersection(null, null);
    }

    @Test
    public void intersection_should_detect_one_card_in_common()
    {
       List<Card> cardList1 = new ArrayList<Card>();
       cardList1.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
       cardList1.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS));
       cardList1.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));

       List<Card> cardList2 = new ArrayList<Card>();
       cardList2.add(new Card(Card.Rank.SEVEN, Card.Suit.CLUBS));
       cardList2.add(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
       cardList2.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));

       List<Card> result = new ArrayList<Card>();
       result.add(new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));

       assertEquals(result, Card.intersection(cardList1, cardList2));
    }

}
