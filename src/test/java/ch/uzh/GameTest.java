package ch.uzh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import ch.uzh.deck.Card;
import ch.uzh.deck.Deck;
import ch.uzh.deck.MockDeck;
import ch.uzh.lobby.Lobby;
import ch.uzh.lobby.MockLobby;
import ch.uzh.lobby.Player;
import ch.uzh.turn.PlayerTurn;
import ch.uzh.turn.turnStrategy.MockStrategy;

public class GameTest {
    private int requiredScoreToWin;
    private List<Player> players;
    private Lobby testLobby;
    private Deck testDeck;
    private Game testGame;

    

    public GameTest() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        requiredScoreToWin = 1000;
        players = Arrays.asList(new Player[]{new Player("a"), new Player("b"), new Player("c"), new Player("d")});
        testLobby = MockLobby.create(players);
        testDeck = MockDeck.create(Deck.getDefaultCards());
        testGame = TestingGame.create(testDeck, testLobby, requiredScoreToWin);
    }

    @Test
    void testEnd() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        testGame.end(players.get(2));
        assertEquals(players.get(2), testLobby.getPlayersSortedByRank().next());
        assertTrue(testGame.isFinished());
    }

    @Test
    void testGetDeck() {
        assertEquals(testDeck, testGame.getDeck());
    }

    @Test
    void testGetLobby() {
        assertEquals(testLobby, testGame.getLobby());
    }

    @Test
    void testIsFinished() {
        assertFalse(testGame.isFinished());
        testGame.end(players.get(0));
        assertTrue(testGame.isFinished());
    }

    @Test
    void testPlayCardTurn_EndTurn() throws Throwable {
        MockStrategy strategy = new MockStrategy();
        Card card = new Card("mockCard", strategy);
        PlayerTurn currentTurn = new PlayerTurn();
        Player player = players.get(0);
        ConsoleInput input = TestingConsoleInput.createInstance(new String[]{"E"});
        try {
            TestingGame.playCardTurn(testGame, card, currentTurn, player, input);
        } catch (Exception e) {
            fail();
        }
        assertTrue(strategy.wasPlayed());
        assertTrue(strategy.getMockCommand().wasExecuted());
        assertFalse(currentTurn.isActive());
    }

    @Test
    void testPlayCardTurn_ContinueTurn() throws Throwable {
        MockStrategy strategy = new MockStrategy();
        Card card = new Card("mockCard", strategy);
        PlayerTurn currentTurn = new PlayerTurn();
        Player player = players.get(0);
        ConsoleInput input = TestingConsoleInput.createInstance(new String[]{"D"});
        try {
            TestingGame.playCardTurn(testGame, card, currentTurn, player, input);
        } catch (Exception e) {
            fail();
        }
        assertTrue(strategy.wasPlayed());
        assertTrue(strategy.getMockCommand().wasExecuted());
        assertTrue(currentTurn.isActive());
    }

    @Test
    void testPlayCardTurn_LooseTurn() throws Throwable {
        MockStrategy strategy = new MockStrategy();
        strategy.setHasLost(true);
        Card card = new Card("mockCard", strategy);
        PlayerTurn currentTurn = new PlayerTurn();
        Player player = players.get(0);
        // Empty input, so if the method asks the player if they want to play again, an error would be thrown
        ConsoleInput input = TestingConsoleInput.createInstance(new String[]{});
        try {
            TestingGame.playCardTurn(testGame, card, currentTurn, player, input);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void testCheckForWinner_hasWon() throws Throwable {
        Player currentPlayer = players.get(0);
        currentPlayer.addScore(1000);
        try {
            TestingGame.checkForWinner(testGame, currentPlayer, 1000);
        } catch (Exception e) {
            fail();
        }
        assertTrue(testGame.isFinished());
    }

    @Test
    void testCheckForWinner_hasNotWon() throws Throwable {
        Player currentPlayer = players.get(0);
        currentPlayer.addScore(999);
        try {
            TestingGame.checkForWinner(testGame, currentPlayer, 1000);
        } catch (Exception e) {
            fail();
        }
        assertFalse(testGame.isFinished());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testAwaitRollDice() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, Throwable {
        // fails with timeout/no new Input
        TestingGame.awaitRollDice(testGame, TestingConsoleInput.createInstance(new String[]{"D", "R"}));
        return;
    }
}
