package ch.uzh.lobby;

public class Player {
    private int score;
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void reduceScore(int reduction) {
        this.score = this.score - reduction < 0 ? 0 : this.score - reduction;
    }

    public void addScore(int addition) {
        this.score += addition;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
}
