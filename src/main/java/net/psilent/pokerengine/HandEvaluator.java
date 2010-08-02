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

public class HandEvaluator
{

    public enum Rank 
    {
        NONE, HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT,
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH;
    }

    private final List<Card> hand;
    private final Rank rank;

    public HandEvaluator(List<Card> hand)
    {
        if(hand == null)
            throw new NullPointerException("Argument cannot be null.");
        if(hand.size() < 5 || Card.hasDuplicates(hand))
            throw new IllegalArgumentException("'hand' must have 5 cards with no duplicates.");

        List<Card> copy = new ArrayList<Card>(hand);

        Collections.sort(copy);
        this.rank = this.rankHand(copy);
        this.hand = copy;
    }

    public Rank getRank()
    {
        return this.rank;
    }

    public List<Card> getHand()
    {
        return Collections.unmodifiableList(this.hand);
    }

    private Rank rankHand(List<Card> hand)
    {
        if(this.checkForRoyalFlush(hand))
        {
            return Rank.ROYAL_FLUSH;
        }
        else if(this.checkForStraightFlush(hand))
        {
            return Rank.STRAIGHT_FLUSH;
        }
        else if(this.checkForFourOfAKind(hand))
        {
            return Rank.FOUR_OF_A_KIND;
        }
        else if(this.checkForFullHouse(hand))
        {
            return Rank.FULL_HOUSE;
        }
        else if(this.checkForFlush(hand))
        {
            return Rank.FLUSH;
        }
        else if(this.checkForStraight(hand))
        {
            return Rank.STRAIGHT;
        }
        else if(this.checkForThreeOfAKind(hand))
        {
            return Rank.THREE_OF_A_KIND;
        }
        else if(this.checkForTwoPair(hand))
        {
            return Rank.TWO_PAIR;
        }
        else if(this.checkForOnePair(hand))
        {
            return Rank.ONE_PAIR;
        }
        else
        {
            this.identifyHighCard(hand);
            return Rank.HIGH_CARD;
        }
    }

    private boolean checkForRoyalFlush(List<Card> hand)
    {
        if(this.isRoyalStraight(hand) && this.isFlush(hand))
        {
            return true;
        }
        else
        {
            return false;
        }
     }

    private boolean checkForStraightFlush(List<Card> hand)
    {
        if(this.isStraight(hand) && this.isFlush(hand))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkForFourOfAKind(List<Card> hand)
    {
        if(hand.get(0).getRank() == hand.get(3).getRank())
        {
            hand.remove(4);
            return true;
        }
        else if(hand.get(1).getRank() == hand.get(4).getRank())
        {
            hand.remove(0);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkForFullHouse(List<Card> hand)
    {
        if((hand.get(0).getRank() == hand.get(1).getRank()
            && hand.get(2).getRank() == hand.get(4).getRank())
            ||
            (hand.get(0).getRank() == hand.get(2).getRank()
            && hand.get(3).getRank() == hand.get(4).getRank()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkForFlush(List<Card> hand)
    {
        if(this.isFlush(hand))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkForStraight(List<Card> hand)
    {
        if(this.isStraight(hand))
        {
            return true;
        }
        else
        {
            return false;
        }
     }

    private boolean checkForThreeOfAKind(List<Card> hand)
    {
        if(hand.get(0).getRank() == hand.get(1).getRank()
            && hand.get(0).getRank() == hand.get(2).getRank())
        {
            Card card1 = hand.get(3);
            Card card2 = hand.get(4);
            hand.remove(card1);
            hand.remove(card2);
            return true;
        }
        else if(hand.get(1).getRank() == hand.get(2).getRank()
            && hand.get(1).getRank() == hand.get(3).getRank())
        {
            Card card1 = hand.get(0);
            Card card2 = hand.get(4);
            hand.remove(card1);
            hand.remove(card2);
            return true;
        }
        else if(hand.get(2).getRank() == hand.get(3).getRank()
            && hand.get(2).getRank() == hand.get(4).getRank())
        {
            Card card1 = hand.get(0);
            Card card2 = hand.get(1);
            hand.remove(card1);
            hand.remove(card2);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkForTwoPair(List<Card> hand)
    {
        if(hand.get(0).getRank() == hand.get(1).getRank()
            && hand.get(2).getRank() == hand.get(3).getRank())
        {
            hand.remove(4);
            return true;
        }
        else if(hand.get(1).getRank() == hand.get(2).getRank()
            && hand.get(3).getRank() == hand.get(4).getRank())
        {
            hand.remove(0);
            return true;
        }
        else if(hand.get(0).getRank() == hand.get(1).getRank()
            && hand.get(3).getRank() == hand.get(4).getRank())
        {
            hand.remove(2);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkForOnePair(List<Card> hand)
    {
        for(int i = 0; i < 4; i++)
        {
            if(hand.get(i).getRank() == hand.get(i + 1).getRank()
                && hand.get(i).getRank().getValue() > Card.Rank.TEN.getValue())
            {
                Card p1 = hand.get(i);
                Card p2 = hand.get(i + 1);
                hand.clear();
                hand.add(p1);
                hand.add(p2);
                break;
            }
        }

        return hand.size() == 2;
    }

    private void identifyHighCard(List<Card> hand)
    {
        for(int i = 0; i < 4; i++)
        {
            hand.remove(0);
        }
    }

    private boolean isFlush(List<Card> hand)
    {
        return hand.get(0).getSuit() == hand.get(1).getSuit()
            && hand.get(0).getSuit() == hand.get(2).getSuit()
            && hand.get(0).getSuit() == hand.get(3).getSuit()
            && hand.get(0).getSuit() == hand.get(4).getSuit();
    }

    private boolean isRoyalStraight(List<Card> hand)
    {
        return hand.get(0).getRank() == Card.Rank.TEN
            && hand.get(1).getRank() == Card.Rank.JACK
            && hand.get(2).getRank() == Card.Rank.QUEEN
            && hand.get(3).getRank() == Card.Rank.KING
            && hand.get(4).getRank() == Card.Rank.ACE;
    }

    private boolean isStraight(List<Card> hand)
    {
        boolean firstFourStraight =
            hand.get(0).getRank().getValue() + 1 == hand.get(1).getRank().getValue()
            && hand.get(1).getRank().getValue() + 1 == hand.get(2).getRank().getValue()
            && hand.get(2).getRank().getValue() + 1 == hand.get(3).getRank().getValue();

        if(firstFourStraight && hand.get(0).getRank() == Card.Rank.TWO && hand.get(4).getRank() == Card.Rank.ACE)
        {
            return true;
        }
        else if(firstFourStraight && hand.get(3).getRank().getValue() + 1 == hand.get(4).getRank().getValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
