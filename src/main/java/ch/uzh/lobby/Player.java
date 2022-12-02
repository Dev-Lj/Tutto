package ch.uzh.lobby;

import java.util.Comparator;

public class Player implements Comparable<Player> {
    private int score;
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String toRankingString(int rank) {
        return String.format("%d. %s: %d Points", rank++, name, score);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    /**
     * @pre addition >= 0
     */
    public void reduceScore(int reduction) {
        assert reduction >= 0;
        this.score = Math.max(this.score - reduction, 0);
    }

    /**
     * @pre addition >= 0
     */
    public void addScore(int addition) {
        assert addition >= 0;
        this.score += addition;
    }

    @Override
    public int compareTo(Player o) {
        return name.compareTo(o.getName());
    }

    public static Comparator<Player> RankComparator = new Comparator<>() {
        @Override
        public int compare(Player o1, Player o2) {
            return o2.getScore() - o1.getScore();
        }
    };
}
