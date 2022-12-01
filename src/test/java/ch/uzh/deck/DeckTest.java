package ch.uzh.deck;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private final Deck deck = Deck.createDefaultDeck();

    @Test
    void testGetDefaultCards() throws NoSuchFieldException, IllegalAccessException {
        Field cardFrequenciesField = Deck.class.getDeclaredField("cardFrequencies");
        cardFrequenciesField.setAccessible(true);
        Map<Card, Integer> cardFrequencies = (Map<Card, Integer>) cardFrequenciesField.get(null);
        int nrOfCardsInMap = 0;
        for(Map.Entry<Card, Integer> card : cardFrequencies.entrySet()) {
            nrOfCardsInMap += card.getValue();
        }
        int nrOfCardsInList = Deck.getDefaultCards().size();
        assertEquals(nrOfCardsInMap, nrOfCardsInList);
    }

    @Test
    void testCreateDefaultDeck() throws NoSuchFieldException, IllegalAccessException {
        Field cardsField = Deck.class.getDeclaredField("cards");
        cardsField.setAccessible(true);
        List<Card> cards = (List<Card>) cardsField.get(deck);
        assertTrue(cards.size() > 0);
    }

    @Test
    void testDrawCard_neverEmpty() throws NoSuchFieldException, IllegalAccessException {
        Field cardsField = Deck.class.getDeclaredField("cards");
        cardsField.setAccessible(true);
        List<Card> cardsBeforeDraw = (List<Card>) cardsField.get(deck);
        deck.drawCard();
        List<Card> cardsAfterDraw = (List<Card>) cardsField.get(deck);
        assertEquals(cardsBeforeDraw.size(), cardsAfterDraw.size());
    }
}