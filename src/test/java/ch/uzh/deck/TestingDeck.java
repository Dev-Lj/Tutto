package ch.uzh.deck;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Allows to instantiate Deck, bypassing static createDefaultDeck()
 */
public class TestingDeck {
    private TestingDeck() {}

    public static Deck create(List<Card> cards) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<Deck> constructor = Deck.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);
        Deck deck = constructor.newInstance(cards);
        return deck;
    }
}
