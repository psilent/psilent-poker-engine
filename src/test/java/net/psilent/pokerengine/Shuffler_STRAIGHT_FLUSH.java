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

import java.util.Collections;
import java.util.List;

class Shuffler_STRAIGHT_FLUSH implements Shuffler
{
    public void shuffle(List<Card> cardList)
    {
        int cardIndex;
        cardIndex= cardList.indexOf(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        Collections.swap(cardList, 0, cardIndex);
        cardIndex= cardList.indexOf(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
        Collections.swap(cardList, 1, cardIndex);
        cardIndex= cardList.indexOf(new Card(Card.Rank.FOUR, Card.Suit.CLUBS));
        Collections.swap(cardList, 2, cardIndex);
        cardIndex= cardList.indexOf(new Card(Card.Rank.FIVE, Card.Suit.CLUBS));
        Collections.swap(cardList, 3, cardIndex);
        cardIndex= cardList.indexOf(new Card(Card.Rank.SIX, Card.Suit.CLUBS));
        Collections.swap(cardList, 4, cardIndex);
    }
}
