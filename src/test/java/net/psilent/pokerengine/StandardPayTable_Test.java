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

import org.junit.Test;
import static org.junit.Assert.*;

public class StandardPayTable_Test
{
    private PayTable payTable = new StandardPayTable();

    @Test (expected=IllegalArgumentException.class)
    public void draw_should_throw_when_called_with_NONE()
    {
        this.payTable.getPayout(HandEvaluator.Rank.NONE);
    }

    @Test
    public void should_return_4000_for_ROYAL_FLUSH()
    {
        assertEquals(4000, this.payTable.getPayout(HandEvaluator.Rank.ROYAL_FLUSH));
    }

    @Test
    public void should_return_250_for_STRAIGHT_FLUSH()
    {
        assertEquals(250, this.payTable.getPayout(HandEvaluator.Rank.STRAIGHT_FLUSH));
    }

    @Test
    public void should_return_125_for_FOUR_OF_A_KIND()
    {
        assertEquals(125, this.payTable.getPayout(HandEvaluator.Rank.FOUR_OF_A_KIND));
    }

    @Test
    public void should_return_45_for_FULL_HOUSE()
    {
        assertEquals(45, this.payTable.getPayout(HandEvaluator.Rank.FULL_HOUSE));
    }

    @Test
    public void should_return_30_for_FLUSH()
    {
        assertEquals(30, this.payTable.getPayout(HandEvaluator.Rank.FLUSH));
    }

    @Test
    public void should_return_20_for_STRAIGHT()
    {
        assertEquals(20, this.payTable.getPayout(HandEvaluator.Rank.STRAIGHT));
    }

    @Test
    public void should_return_15_for_THREE_OF_A_KIND()
    {
        assertEquals(15, this.payTable.getPayout(HandEvaluator.Rank.THREE_OF_A_KIND));
    }

    @Test
    public void should_return_10_for_TWO_PAIR()
    {
        assertEquals(10, this.payTable.getPayout(HandEvaluator.Rank.TWO_PAIR));
    }

    @Test
    public void should_return_5_for_ONE_PAIR()
    {
        assertEquals(5, this.payTable.getPayout(HandEvaluator.Rank.ONE_PAIR));
    }

    @Test
    public void should_return_0_for_HIGH_CARD()
    {
        assertEquals(0, this.payTable.getPayout(HandEvaluator.Rank.HIGH_CARD));
    }
}
