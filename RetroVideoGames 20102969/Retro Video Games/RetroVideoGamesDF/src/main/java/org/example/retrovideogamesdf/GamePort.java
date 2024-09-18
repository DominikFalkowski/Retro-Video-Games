package org.example.retrovideogamesdf;

public class GamePort {



    public GamesMachine newMachine;
    public String portDeveloper;
    public String originalGame;

    public GamePort(String portDeveloper, String originalGame, int initialReleaseYearOfGamePort) {
        this.portDeveloper = portDeveloper;
        this.originalGame = originalGame;
        this.initialReleaseYearOfGamePort = initialReleaseYearOfGamePort;
    }

    public int initialReleaseYearOfGamePort;
    public GamePort nextGamePort;

    public GamePort getNextGamePort() {
        return nextGamePort;
    }

    public void setNextGamePort(GamePort nextGamePort) {
        this.nextGamePort = nextGamePort;
    }

    public int getInitialReleaseYearOfGamePort() {
        return initialReleaseYearOfGamePort;
    }

    public void setInitialReleaseYearOfGamePort(int initialReleaseYearOfGamePort) {
        this.initialReleaseYearOfGamePort = initialReleaseYearOfGamePort;
    }



    public void setOriginalGame(String originalGame) {
        this.originalGame = originalGame;
    }

    public String getOriginalGame() {
        return originalGame;
    }

    public GamesMachine getNewMachine() {
        return newMachine;
    }

    public void setNewMachine(GamesMachine newMachine) {
        this.newMachine = newMachine;
    }

    @Override
    public String toString() {
        return "GamePort{" +
                "newMachine=" + newMachine +
                ", portDeveloper='" + portDeveloper + '\'' +
                ", originalGame='" + originalGame + '\'' +
                ", initialReleaseYearOfGamePort=" + initialReleaseYearOfGamePort +
                ", nextGamePort=" + nextGamePort +
                '}';
    }

    public String getPortDeveloper() {
        return portDeveloper;
    }

    public void setPortDeveloper(String portDeveloper) {
        this.portDeveloper = portDeveloper;
    }
}