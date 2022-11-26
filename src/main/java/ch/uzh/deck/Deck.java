package ch.uzh.deck;

import java.util.Arrays;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDefaultDeck() {
        Card[] defaultCards = {};
        return new Deck(Arrays.asList(defaultCards));
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public void shuffle() {
        // TODO implement
    }


}
