package ch.uzh.deck;

import ch.uzh.turn.TurnStrategy;

public class Card {
    private final String name;
    private final TurnStrategy strategy;

    public Card(String name, TurnStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public TurnStrategy getStrategy() {
        return strategy;
    }
    
}
