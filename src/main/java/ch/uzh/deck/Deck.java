package ch.uzh.deck;

import ch.uzh.turn.turnStrategy.*;

import java.util.*;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static List<Card> getDefaultCards() {
        Map<Card, Integer> cardFrequencies = new HashMap<>()
        {{
            put(new Card("Bonus 200", new TuttoBonusTurn(200)), 5);
            put(new Card("Bonus 300", new TuttoBonusTurn(300)), 5);
            put(new Card("Bonus 400", new TuttoBonusTurn(400)), 5);
            put(new Card("Bonus 500", new TuttoBonusTurn(500)), 5);
            put(new Card("Bonus 600", new TuttoBonusTurn(600)), 5);
            put(new Card("x2", new TuttoDoubleTurn()), 5);
            put(new Card("Stop", new StopTurn()), 10);
            put(new Card("Fireworks", new FireworksTurn()), 5);
            put(new Card("Plus/Minus", new PlusMinusTurn()), 5);
            put(new Card("Cloverleaf", new CloverleafTurn()), 1);
            put(new Card("Straight", new StraightTurn()), 5);
        }};

        List<Card> defaultCards = new ArrayList<>();
        for(Map.Entry<Card, Integer> card : cardFrequencies.entrySet()) {
            for(int i = 0; i < card.getValue(); i++) {
                defaultCards.add(card.getKey());
            }
        }
        return defaultCards;
    }

    public static Deck createDefaultDeck() {
        List<Card> defaultCards = getDefaultCards();
        Collections.shuffle(defaultCards);

        return new Deck(defaultCards);
    }

    public Card drawCard() {
        Card drawnCard = cards.remove(0);
        cards.add(drawnCard);
        return drawnCard;
    }

    // TODO what is this used for?
    public void shuffle() {
        Collections.shuffle(cards);
    }


}
