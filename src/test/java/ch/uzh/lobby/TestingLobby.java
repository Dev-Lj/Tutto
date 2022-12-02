package ch.uzh.lobby;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Allows to instantiate Lobby, bypassing static createNewLobby
 */
public class TestingLobby {
    private TestingLobby(){}

    public static Lobby create(List<Player> players) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<Lobby> constructor = Lobby.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);
        Lobby lobby = constructor.newInstance(players);
        return lobby;
    }
}
