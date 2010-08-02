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
import java.util.HashSet;
import java.util.List;

public class Card implements Comparable<Card>
{

    public enum Rank
    {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
        TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private final int value;

        private Rank(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank()
    {
        return this.rank;
    }

    public Suit getSuit()
    {
        return this.suit;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;

        final Card other = (Card) obj;
        if(this.rank != other.rank && (this.rank == null || !this.rank.equals(other.rank)))
        {
            return false;
        }
        if(this.suit != other.suit && (this.suit == null || !this.suit.equals(other.suit)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + (this.rank != null ? this.rank.hashCode() : 0);
        hash = 23 * hash + (this.suit != null ? this.suit.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return this.rank + " of " + this.suit;
    }
    
    public int compareTo(Card o)
    {
        if(o == null)
            throw new NullPointerException("Argument cannot be null.");

        if(getClass() != o.getClass())
            throw new ClassCastException("'o' is not an instance of Card.");

        final Card that = (Card)o;
        if(this.rank.getValue() < that.getRank().getValue())
        {
            return -1;
        }
        else if(this.rank.getValue() > that.rank.getValue())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public static List<Card> newStandardDeck()
    {
        List<Card> deck = new ArrayList<Card>();
        for(Suit suit : Suit.values())
            for(Rank rank : Rank.values())
                deck.add(new Card(rank, suit));

        return deck;
    }

    public static boolean hasDuplicates(List<Card> hand)
    {
        if(hand == null)
            throw new NullPointerException("Argument cannot be null.");

        HashSet<Card> set = new HashSet<Card>();
        for(int i = 0; i < hand.size(); i++)
        {
            if(!set.add(hand.get(i)))
            {
                return true;
            }
        }
        return false;
    }

    public static List<Card> intersection(List<Card> list1, List<Card> list2)
    {
        if(list1 == null || list2 == null)
            throw new NullPointerException("Argument cannot be null.");

        List<Card> tempList = new ArrayList<Card>(list2);
        tempList.retainAll(list1);
        return tempList;
    }
}