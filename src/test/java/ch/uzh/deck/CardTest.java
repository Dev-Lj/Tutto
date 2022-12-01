package ch.uzh.deck;

import ch.uzh.turn.TurnStrategy;
import ch.uzh.turn.turnStrategy.StopTurn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private final String cardName = "testcard";
    private final TurnStrategy strategy = new StopTurn();   // TODO okay to do this?
    Card card = new Card(cardName, strategy);

    @Test
    void testGetName() {
        assertEquals(cardName, card.getName());
    }

    @Test
    void testGetStrategy() {
         assertEquals(strategy, card.getStrategy());
    }
}