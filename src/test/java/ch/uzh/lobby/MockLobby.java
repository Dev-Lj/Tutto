package ch.uzh.lobby;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MockLobby {
    private MockLobby(){}

    public static Lobby create(List<Player> players) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Constructor<Lobby> constructor = Lobby.class.getDeclaredConstructor(List.class);
        constructor.setAccessible(true);
        Lobby lobby = constructor.newInstance(players);
        return lobby;
    }
}
