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

public class StandardShuffler_Test
{
    public StandardShuffler_Test()
    {
    }

    @Test (expected=NullPointerException.class)
    public void shuffle_should_throw_when_passed_null_deck()
    {
        Shuffler shuffler = new StandardShuffler();
        shuffler.shuffle(null);
    }
    
    @Test
    public void shuffle_should_change_the_order_of_the_deck()
    {
        List<Card> deck = Card.newStandardDeck();

        Shuffler shuffler = new StandardShuffler();
        shuffler.shuffle(deck);

        assertEquals(false, deck.equals(Card.newStandardDeck()));
    }

    @Test
    public void a_second_shuffle_should_be_different_from_the_previous_shuffle()
    {
        List<Card> deck = Card.newStandardDeck();

        Shuffler shuffler = new StandardShuffler();
        shuffler.shuffle(deck);

        List<Card> firstShuffle = new ArrayList<Card>(deck);
        shuffler.shuffle(deck);

        assertEquals(false, deck.equals(firstShuffle));
    }

}
