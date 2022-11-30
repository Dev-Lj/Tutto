package ch.uzh.lobby;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private final String playerName = "testplayer";
    Player defaultPlayer = new Player(playerName);

    void setScore(Player player, int score) throws NoSuchFieldException, IllegalAccessException {
        Field scoreField = Player.class.getDeclaredField("score");
        scoreField.setAccessible(true);
        scoreField.set(player, score);
    }

    @Test
    void testGetName() {
        assertEquals(defaultPlayer.getName(), playerName);
    }

    @Test
    void testGetScore() throws NoSuchFieldException, IllegalAccessException {
        int testValue = 500;
        setScore(defaultPlayer, testValue);
        assertEquals(defaultPlayer.getScore(), testValue);
    }

    @Test
    void testReduceScore() throws NoSuchFieldException, IllegalAccessException {
        int testValue = 500;
        setScore(defaultPlayer, testValue);
        defaultPlayer.reduceScore(100);
        assertEquals(testValue-100, defaultPlayer.getScore());
    }

    @Test
    void testReduceScore_belowZero() throws NoSuchFieldException, IllegalAccessException {
        int testValue = 500;
        setScore(defaultPlayer, testValue);
        defaultPlayer.reduceScore(550);
        assertEquals(0, defaultPlayer.getScore());
    }

    @Test
    void testAddScore() {
        defaultPlayer.addScore(20);
        assertEquals(20, defaultPlayer.getScore());
    }

    @Test
    void testCompareTo_smaller() {
        Player p1 = new Player("Ahmed");
        Player p2 = new Player("Balthasar");
        int result = p1.compareTo(p2);
        assertTrue(result < 0);
    }

    @Test
    void testCompareTo_bigger() {
        Player p1 = new Player("Ahmed");
        Player p2 = new Player("Balthasar");
        int result = p2.compareTo(p1);
        assertTrue(result > 0);
    }

    @Test
    void testRankComparator() throws NoSuchFieldException, IllegalAccessException {
        Player p1 = new Player("Ahmed");
        setScore(p1, 100);
        Player p2 = new Player("Balthasar");
        setScore(p2, 200);
        Comparator<Player> comparator = Player.RankComparator;
        int result = comparator.compare(p1, p2);
        assertTrue(result > 0);
    }

    @Test
    void testRankComparator_equal() throws NoSuchFieldException, IllegalAccessException {
        Player p1 = new Player("Ahmed");
        setScore(p1, 100);
        Player p2 = new Player("Balthasar");
        setScore(p2, 100);
        Comparator<Player> comparator = Player.RankComparator;
        int result = comparator.compare(p1, p2);
        assertEquals(0, result);
    }
}