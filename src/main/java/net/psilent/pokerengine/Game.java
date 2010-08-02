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


public class Game
{
     public enum State
    {
        DEAL, DRAW
    }

    private int balance = 0;
    private State state = State.DEAL;
    private List<Card> playerCards = new ArrayList<Card>();
    private List<Card> discards = new ArrayList<Card>();
    private List<Card> deck = Card.newStandardDeck();
    private int lastWinAmount = 0;
    private HandEvaluator.Rank lastWinRank = HandEvaluator.Rank.NONE;
    private List<Card> lastWinHand = new ArrayList<Card>();
    private final Shuffler shuffler;
    private final PayTable payTable;

    public Game(Shuffler shuffler, PayTable payTable)
    {
        if(shuffler == null)
        {
            this.shuffler = new StandardShuffler();
        }
        else
        {
            this.shuffler = shuffler;
        }

        if(payTable == null)
        {
            this.payTable = new StandardPayTable();
        }
        else
        {
            this.payTable = payTable;
        }
    }

    public int getBalance()
    {
        return this.balance;
    }

    public void setBalance(int balance)
    {
        if((balance < 5) || balance % 5 != 0)
            throw new IllegalArgumentException("'balance' must be 5 or greater and evenly divisible by 5.");

        this.balance = balance;
    }

    public State getState()
    {
        return this.state;
    }

    public List<Card> getPlayerCards()
    {
        return Collections.unmodifiableList(this.playerCards);
    }

    public List<Card> getDiscards()
    {
        return  Collections.unmodifiableList(this.discards);
    }

    public List<Card> getDeck()
    {
        return  Collections.unmodifiableList(this.deck);
    }

    public int getLastWinAmount()
    {
        return this.lastWinAmount;
    }

    public HandEvaluator.Rank getLastWinRank()
    {
        return this.lastWinRank;
    }

    public List<Card> getLastWinHand()
    {
        return this.lastWinHand;
    }

    public void deal()
    {
        if(this.balance < 5)
            throw new IllegalStateException("Balance must be 5 or greater");
        if(this.state != State.DEAL)
            throw new IllegalStateException("Game must be in 'game over' state.");
        
        this.balance -= 5;
        this.returnAllCardsToDeck();
        this.shuffleDeck();
        this.dealCardsToPlayer();
        this.state = State.DRAW;
        this.lastWinAmount = 0;
        this.lastWinRank = HandEvaluator.Rank.NONE;
        this.lastWinHand.clear();
    }

    public void draw(List<Card> discardedCards)
    {
        if(discardedCards == null)
            throw new NullPointerException("Argument cannot be null.");
        if(Card.hasDuplicates(discardedCards))
            throw new IllegalArgumentException("'discardedCards' cannot have duplicates.");
        if(hasNonPlayerCards(discardedCards))
            throw new IllegalArgumentException("'discardedCards' must be player cards.");
        if(this.state != State.DRAW)
            throw new IllegalStateException("Game must be in the 'draw' state.");

        this.discardAndDrawCards(discardedCards);
        this.evaluate();
        this.state = State.DEAL;
    }
    
    private void returnAllCardsToDeck()
    {
        this.deck.addAll(this.discards);
        this.deck.addAll(this.playerCards);
        this.discards.clear();
        this.playerCards.clear();
    }

    private void dealCardsToPlayer()
    {
        for(int i = 0; i < 5; i++)
        {
            this.playerCards.add(this.deck.get(i));
        }
        this.deck.removeAll(this.playerCards);
    }

    private void shuffleDeck()
    {
        this.shuffler.shuffle(this.deck);
    }

    private void discardAndDrawCards(List<Card> discardedCards)
    {
        for(int i = 0; i < discardedCards.size(); i++)
        {
            this.discards.add(this.playerCards.set(this.playerCards.indexOf(discardedCards.get(i)), this.deck.get(0)));
            this.deck.remove(0);
        }
    }

    private boolean hasNonPlayerCards(List<Card> cards)
    {
        List<Card> intersection = new ArrayList<Card>();
        intersection = Card.intersection(this.playerCards, cards);

        if(intersection.size() == cards.size())
        {
            for(Card c : cards)
            {
                if(this.playerCards.get(this.playerCards.indexOf(c)) != c)
                    return true;
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    private void evaluate()
    {
        HandEvaluator handEvaluator = new HandEvaluator(this.getPlayerCards());
        this.lastWinRank = handEvaluator.getRank();
        this.lastWinHand.addAll(handEvaluator.getHand());
        this.lastWinAmount = this.payTable.getPayout(this.lastWinRank);
        this.balance += this.lastWinAmount;
    }
}
