package org.example.retrovideogamesdf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class HelloApplication extends Application {
    private GameMap<String, Game> gamesMap = new GameMap<>();
    private GameMachineMap<String, GamesMachine> gamesMachineMap = new GameMachineMap<>();
    private GamePortMap<String, GamePort> gamePortMap = new GamePortMap<>();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        GameMap<?, ?> loadedGamesMap = GameMap.loadFromFile("gamesData.ser");
        if (loadedGamesMap != null) {
            // Access the loaded data
            System.out.println("Loaded Data: " + loadedGamesMap.entrySet());
        }
        GameMachineMap<?, ?> loadedGameMachineMap = GameMachineMap.loadFromFile("gamesData.ser");
        if (loadedGamesMap != null) {
            // Access the loaded data
            System.out.println("Loaded Data: " + loadedGameMachineMap.entrySet());
        }
        GamePortMap<?, ?> loadedGamePortMap = GamePortMap.loadFromFile("gamesData.ser");
        if (loadedGamesMap != null) {
            // Access the loaded data
            System.out.println("Loaded Data: " + loadedGamePortMap.entrySet());
        }

}
}