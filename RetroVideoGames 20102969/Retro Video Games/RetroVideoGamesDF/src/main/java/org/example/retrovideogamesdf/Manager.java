package org.example.retrovideogamesdf;

import java.util.Map;

public class Manager {
    static Map<String, Game> games;
    static Map<String, GamePort> gamePorts;
    public static void removeGame(String gameName) {
        games.remove(gameName);
    }

    // Optional: Remove associated game ports
    public static void removeGameAndPorts(String gameName) {
        Game game = games.get(gameName);
        if (game != null) {
            for (GamePort gamePort : getGamePorts().values()) {
                removeGamePort(gamePort.getOriginalGame());
            }
        }
        removeGame(gameName);
    }

    public void addGamePort(String gameName, GamePort gamePort) {
        Game game = games.get(gameName);
        if (game != null) {
            addGamePort(gamePort);
        }
    }

    public static void removeGamePort(String portName) {
        for (Game game : games.values()) {
            removeGamePort(portName);
        }
    }
    public static Map<String, Game> getGames() {
        return games;
    }
    public void addGame(Game game) {
        games.put(game.getGameName(), game);
    }
    public void addGamePort(GamePort gamePort) {
        gamePorts.put(gamePort.getOriginalGame(), gamePort);
    }

    public static void removeGamePorts() {
        gamePorts.clear();
    }

    public static Map<String, GamePort> getGamePorts() {
        return gamePorts;
    }

}
