package ch.uzh;

/**
 * Hello world!
 *
 */
public class App 
{
    public final static boolean DEBUG_MODE = false;

    public static void main( String[] args )
    {
        Game game = Game.createNewGame();
        game.play();
    }
}
