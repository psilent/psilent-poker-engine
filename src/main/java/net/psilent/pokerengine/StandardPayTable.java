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

public class StandardPayTable implements PayTable
{

    public int getPayout(HandEvaluator.Rank rank)
    {
        if(rank == HandEvaluator.Rank.NONE)
            throw new IllegalArgumentException("Argument cannot be NONE.");

        if(rank == HandEvaluator.Rank.ROYAL_FLUSH)
        {
            return 4000;
        }
        else if(rank == HandEvaluator.Rank.STRAIGHT_FLUSH)
        {
            return 250;
        }
        else if(rank == HandEvaluator.Rank.FOUR_OF_A_KIND)
        {
            return 125;
        }
        else if(rank == HandEvaluator.Rank.FULL_HOUSE)
        {
            return 45;
        }
        else if(rank == HandEvaluator.Rank.FLUSH)
        {
            return 30;
        }
        else if(rank == HandEvaluator.Rank.STRAIGHT)
        {
            return 20;
        }
        else if(rank == HandEvaluator.Rank.THREE_OF_A_KIND)
        {
            return 15;
        }
        else if(rank == HandEvaluator.Rank.TWO_PAIR)
        {
            return 10;
        }
        else if(rank == HandEvaluator.Rank.ONE_PAIR)
        {
            return 5;
        }
        else // HIGH_CARD
        {
            return 0;
        }
    }
}
