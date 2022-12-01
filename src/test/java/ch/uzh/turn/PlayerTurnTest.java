package ch.uzh.turn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ch.uzh.command.Command;
import ch.uzh.command.NullCommand;
import ch.uzh.deck.Card;

public class PlayerTurnTest {
    PlayerTurn testTurn = new PlayerTurn();

    /**
     * InnerPlayerTurnTest
     */
    public class MockStrategy implements TurnStrategy {
        private boolean wasPlayed = false;

        @Override
        public Command playTurn(PlayerTurn currentTurn) {
            this.wasPlayed = true;
            return new NullCommand();
        }

        @Override
        public int getScore() {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean wasPlayed () {
            return this.wasPlayed;
        }
    }

    @Test
    void testEndTurn() {
        testTurn.endTurn();
        assertFalse(testTurn.isActive());
    }

    @Test
    void testGetScore() {
        testTurn.setScore(100);
        assertEquals(100, testTurn.getScore());
    }

    @Test
    void testIsActive() {
        assertTrue(testTurn.isActive());
    }

    @Test
    void testLooseTurn() {
        testTurn.setScore(100);
        testTurn.looseTurn();
        assertEquals(0, testTurn.getScore());
        assertFalse(testTurn.isActive());
    }

    @Test
    void testPlayTurn() {
        MockStrategy mockStrategy = new MockStrategy();
        testTurn.playTurn(new Card("testCard", mockStrategy));
        assertTrue(mockStrategy.wasPlayed());
    }

    @Test
    void testSetScore() {
        testTurn.setScore(100);
        assertEquals(100, testTurn.getScore());
    }
}
