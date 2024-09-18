package org.example.retrovideogamesdf;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.util.*;



public class HelloController {

    private final GameMap<String, Game> gamesMap = new GameMap<>();
    private final GameMachineMap<String, GamesMachine> gamesMachineMap = new GameMachineMap<>();
    private final GamePortMap<String, GamePort> gamePortMap = new GamePortMap<>();

    @FXML
    private TextField gameNameField;
    @FXML
    private TextField gameNameField2;
    @FXML
    private TextField originalGameField;
    @FXML
    private TextField originalGameField2;
    @FXML
    private TextField GameMachineSearchField;
    @FXML
    private TextField GamePortSearchField;

    @FXML
    private TextField publisherField;
    @FXML
    private TextField gameDescriptionField;
    @FXML
    private TextField machineDescriptionField;
    @FXML
    private TextField developerField;
    @FXML
    private TextField originalGamesMachineDevelopedForField;
    @FXML
    private TextField initialReleaseYearField;
    @FXML
    private TextField  initialReleaseYearOfGamePortField;
    @FXML
    private TextField coverArtImageGameField;
    @FXML
    private TextField GameSearchField;
    @FXML
    private TextField initialLaunchYearField;
    @FXML
    private TextField machineNameField;
    @FXML
    private TextField machineNameField2;

    @FXML
    private TextField manufacturerField;
    @FXML
    private TextField gameMachineTypeField;
    @FXML
    private TextField mediaTypeField;

    @FXML
    private TextField initialRRPField;
    @FXML
    private TextField photoURLField;
    @FXML
    private TextField portDeveloperField;
    @FXML
    private TextArea gamesMachinesList;
    @FXML
    private TextArea gameList;
    @FXML
    private TextArea gamePortsList;



    //Search


//    private String getFieldValue(GamesMachine machine, String criteria) {
//        switch (criteria) {
//            case "Name/Title":
//                return machine.getMachineName();
//            case "Type":
//                return machine.getGameMachineType();
//            case "Year":
//                return String.valueOf(machine.getInitialLaunchYear());
//
//            default:
//                return "";
//        }
//    }

    //Add Buttons
    @FXML
    public void onAddGameButton(ActionEvent actionEvent) {
        String gameName = gameNameField.getText();
        String publisher = publisherField.getText();
        String gameDescription = gameDescriptionField.getText();
        String developer = developerField.getText();
        String originalGamesMachineName = originalGamesMachineDevelopedForField.getText();
        int initialReleaseYear = Integer.parseInt(initialReleaseYearField.getText());
        String coverArtImageGame = coverArtImageGameField.getText();

            GamesMachine originalGamesMachine = findOrCreateGamesMachine(originalGamesMachineName);

            Game newGame = new Game(gameName, publisher, gameDescription, developer, originalGamesMachine, initialReleaseYear, coverArtImageGame);
            gamesMap.putGames(gameName, newGame);
        System.out.println("Game Name = "+newGame.getGameName() +"  Publisher = "+ newGame.getPublisher()+"  Game Description = "+ newGame.getGameDescription()+"  Developer Of Game = "+ newGame.getDeveloper()+ "  Original Machine Developed For = "+newGame.getOriginalGamesMachineDevelopedFor()+ "  Initial Release Year Of Game = "+newGame.getInitialReleaseYear()+"  Cover Art Image URl = "+ newGame.getCoverArtImageGame());


        gameList.setText("Game Name = "+newGame.getGameName() +"  Publisher = "+ newGame.getPublisher()+"  Game Description = "+ newGame.getGameDescription()+"  Developer Of Game = "+ newGame.getDeveloper()+ "  Original Machine Developed For = "+newGame.getOriginalGamesMachineDevelopedFor()+ "  Initial Release Year Of Game = "+newGame.getInitialReleaseYear()+"  Cover Art Image URl = "+ newGame.getCoverArtImageGame());




        clearGameFields();
    }
    public void onAddGameMachineButton(ActionEvent actionEvent) {
        String machineName = machineNameField.getText();
        String manufacturer = manufacturerField.getText();
        String machineDescription = machineDescriptionField.getText();
        String gameMachineType = gameMachineTypeField.getText();
        String mediaType = mediaTypeField.getText();

        int initialLaunchYear = Integer.parseInt(initialLaunchYearField.getText());
        double intitialRRP = Double.parseDouble(initialRRPField.getText());
        String photoURL = photoURLField.getText();

        GamesMachine newGamesMachine = new GamesMachine(machineName,manufacturer,machineDescription,gameMachineType,mediaType,initialLaunchYear,intitialRRP,photoURL);

        System.out.println("Machine Name = "+newGamesMachine.getMachineName() +"  Manufacturer = "+ newGamesMachine.getManufacturer()+ "  Machine Description = "+newGamesMachine.getMachineDescription()+ "  Initial Launch Year Of Game Machine = "+newGamesMachine.getInitialLaunchYear());

        gamesMachineMap.putGamesMachine(machineName,newGamesMachine);
        gamesMachinesList.setText("Machine Name = "+newGamesMachine.getMachineName() +"  Manufacturer = "+ newGamesMachine.getManufacturer()+ "  Machine Description = "+newGamesMachine.getMachineDescription()+ "  Initial Launch Year Of Game Machine = "+newGamesMachine.getInitialLaunchYear());


        clearGameMachineFields();
    }
    public void onAddGamePortButton(ActionEvent actionEvent) {
        String portDeveloper = portDeveloperField.getText();
        String originalGame = originalGameField.getText();
        int initialReleaseYearOfGamePort = Integer.parseInt((initialReleaseYearOfGamePortField.getText()));



            GamePort newGamePort = new GamePort(portDeveloper,originalGame, initialReleaseYearOfGamePort);

            gamePortMap.putGamePort(portDeveloper, newGamePort);
            gamePortsList.setText("Port Dev = "+newGamePort.getPortDeveloper()+"  Original Game = " + newGamePort.getOriginalGame()+"  Initial Release Year = " +newGamePort.getInitialReleaseYearOfGamePort());
        System.out.println("Port Dev = "+newGamePort.getPortDeveloper()+"  Original Game = " + newGamePort.getOriginalGame()+"  Initial Release Year = " +newGamePort.getInitialReleaseYearOfGamePort());

        clearGamePortFields();
        }

    @FXML
    private void onDeleteGamesMachineButton(ActionEvent actionEvent) {
        String machineName = machineNameField2.getText();

        if (!machineName.isEmpty()) {

            deleteGamesMachine(machineName);

        }
    }

    //Delete Buttons
public void deleteGamesMachine(String machineName) {
    GamesMachine gamesMachine = gamesMachineMap.getGamesMachine(machineName);

    if (gamesMachine != null) {
        for (Game game : Manager.getGames().values()) {
            Manager.removeGameAndPorts(game.getGameName());
        }

        // Remove the game machine
        gamesMachineMap.remove(machineName);

    }
}
    @FXML
    private void onDeleteGameButton(ActionEvent actionEvent) {
        String gameName = gameNameField2.getText();

        if (!gameName.isEmpty()) {
            deleteGame(gameName);

        }
    }
    public void deleteGame(String gameName) {
        Game game = gamesMap.getGame(gameName);

        if (game != null) {
            Manager.removeGamePorts();

            gamesMap.remove(gameName);
        }
    }
    @FXML
    private void onDeleteGamePortButton(ActionEvent actionEvent) {
        String originalGame = originalGameField2.getText();

        if (!originalGame.isEmpty()) {
            deleteGamePort(originalGame);
        }
    }
    public void deleteGamePort(String originalGame) {
        gamePortMap.remove(originalGame);
    }






    private GamesMachine findOrCreateGamesMachine(String machineName) {
        GamesMachine gamesMachine = gamesMachineMap.getGamesMachine(machineName);

        return gamesMachine;
    }

    private void clearGameFields() {
        gameNameField.clear();
        publisherField.clear();
        gameDescriptionField.clear();
        developerField.clear();
        originalGamesMachineDevelopedForField.clear();
        initialReleaseYearField.clear();
        coverArtImageGameField.clear();
    }
    private void clearGameMachineFields() {
        machineNameField.clear();
        manufacturerField.clear();
        machineDescriptionField.clear();
        gameMachineTypeField.clear();
        mediaTypeField.clear();
        initialLaunchYearField.clear();
        initialRRPField.clear();
        photoURLField.clear();
    }private void clearGamePortFields() {
        portDeveloperField.clear();
        originalGameField.clear();
        initialReleaseYearField.clear();
    }


    @FXML
    private void handleSortGamesButtonClick(ActionEvent actionEvent) {
        sortGames("name", true);

    }
    @FXML
    private void handleSortGameMachinesButtonClick(ActionEvent actionEvent) {
        sortGameMachines("name", true);

    }
    @FXML
    private void handleSortGamePortsButtonClick(ActionEvent actionEvent) {
        sortGamePorts("name", true);

    }


    //Sort Methods
    public void sortGames(String sortBy, boolean ascending) {
        Comparator<Game> comparator = getGameComparator(sortBy);

        if (!ascending) {
            comparator.reversed();
        }

        gamesMap.sortGames();
    }

    private Comparator<Game> getGameComparator(String sortBy) {
        switch (sortBy) {
            case "name":
                return Comparator.comparing(Game::getGameName, String.CASE_INSENSITIVE_ORDER);
            case "publisher":
                return Comparator.comparing(Game::getPublisher, String.CASE_INSENSITIVE_ORDER);
            case "releaseYear":
                return Comparator.comparingInt(Game::getInitialReleaseYear);
            default:
                return Comparator.comparing(Game::getGameName, String.CASE_INSENSITIVE_ORDER);
        }
    }

    public void sortGameMachines(String sortBy, boolean ascending) {
            Comparator<GamesMachine> comparator = getGameMachineComparator(sortBy);

        if (!ascending) {
            comparator.reversed();
        }

        gamesMachineMap.sortGameMachines();
    }

    private Comparator<GamesMachine> getGameMachineComparator(String sortBy) {
        switch (sortBy) {
            case "machine name":
                return Comparator.comparing(GamesMachine::getMachineName, String.CASE_INSENSITIVE_ORDER);
            case "Manufacturer":
                return Comparator.comparing(GamesMachine::getManufacturer, String.CASE_INSENSITIVE_ORDER);
            case "Machine type":
                return Comparator.comparing(GamesMachine::getGameMachineType, String.CASE_INSENSITIVE_ORDER);
            case "Media Type":
                return Comparator.comparing(GamesMachine::getMediaType, String.CASE_INSENSITIVE_ORDER);
            case "Initial Launch Year":
                return Comparator.comparingInt(GamesMachine::getInitialLaunchYear);
            default:
                return Comparator.comparing(GamesMachine::getMachineName, String.CASE_INSENSITIVE_ORDER);
        }
    }
    public void sortGamePorts(String sortBy, boolean ascending) {
        Comparator<GamePort> comparator = getGameMachinePortComparator(sortBy);

        if (!ascending) {
            comparator.reversed();
        }

        gamesMachineMap.sortGameMachines();
    }

    private Comparator<GamePort> getGameMachinePortComparator(String sortBy) {
        switch (sortBy) {
            case "Port Developer":
                return Comparator.comparing(GamePort::getPortDeveloper, String.CASE_INSENSITIVE_ORDER);
            case "Original Game":
                return Comparator.comparing(GamePort::getOriginalGame, String.CASE_INSENSITIVE_ORDER);
            case "Initial Release Year":
                return Comparator.comparingInt(GamePort::getInitialReleaseYearOfGamePort);

            default:
                return Comparator.comparing(GamePort::getPortDeveloper, String.CASE_INSENSITIVE_ORDER);
        }
    }

    public GameMap<String, Game> filterGames(String searchTerm) {
        GameMap<String, Game> filteredGames = new GameMap<>();

        for (GameMap.Entry<String, Game> entry : gamesMap.entrySet()) {
            Game game = entry.getValue();
            if (matchesSearchTermGame(game, searchTerm)) {
                gamesMap.putGames(String.valueOf(game),game);
            }
        }

        return filteredGames;
    }
    public GameMachineMap<String, GamesMachine> filterGameMachines(String searchTerm) {
        GameMachineMap<String, GamesMachine> filteredGamesMachine = new GameMachineMap<>();

        for (GameMachineMap.Entry<String, GamesMachine> entry : gamesMachineMap.entrySet()) {
            GamesMachine gamesMachine = entry.getValue();
            if (matchesSearchTermGameMachine(gamesMachine, searchTerm)) {
                gamesMachineMap.putGamesMachine(String.valueOf(gamesMachine), gamesMachine);
            }
        }

        return filteredGamesMachine;
    }
    public GamePortMap<String, GamePort> filteredGamePorts(String searchTerm) {
        GamePortMap<String, GamePort> filteredGamePorts = new GamePortMap<>();

        for (GamePortMap.Entry<String, GamePort> entry : gamePortMap.entrySet()) {
            GamePort gamePort = entry.getValue();
            if (matchesSearchTermGamePort(gamePort, searchTerm)) {
                gamePortMap.putGamePort(String.valueOf(gamePort), gamePort);
            }
        }

        return filteredGamePorts;
    }
    @FXML
    private void onSearchGamesButtonClicked(ActionEvent actionEvent) {
        String searchTerm = GameSearchField.getText().trim().toLowerCase();
        GameMap<String, Game> filteredGames = filterGames(searchTerm);

    }
    @FXML
    private void onSearchGameMachinesButtonClicked(ActionEvent actionEvent) {
        String searchTerm = GameMachineSearchField.getText().trim().toLowerCase();
        GameMachineMap<String, GamesMachine> filteredGameMachines = filterGameMachines(searchTerm);

    }
    @FXML
    private void onSearchGamePortsButtonClicked(ActionEvent actionEvent) {
        String searchTerm = GamePortSearchField.getText().trim().toLowerCase();
        GamePortMap<String, GamePort> filteredGamePorts = filteredGamePorts(searchTerm);

    }


    private boolean matchesSearchTermGame(Game game, String searchTerm) {
        return game.getGameName().toLowerCase().contains(searchTerm.toLowerCase());
    }
    private boolean matchesSearchTermGameMachine(GamesMachine gamesMachine , String searchTerm) {
        return gamesMachine.getMachineName().toLowerCase().contains(searchTerm.toLowerCase());
    }
    private boolean matchesSearchTermGamePort(GamePort gamePort, String searchTerm) {
        return gamePort.getOriginalGame().toLowerCase().contains(searchTerm.toLowerCase());
    }

//    @FXML
//    private void initialize() {
//        // Initialize your data (populate the gamesMachineMap with data)
//        populateData();
//
//        // Initialize the context menu
//        contextMenu = new ContextMenu();
//
//        // Populate the context menu
//        updateContextMenu();
//
//        // Set the context menu to the TreeView
//        gameNameField.setContextMenu(contextMenu);
//    }

//    private void updateContextMenu() {
//        // Clear existing menu items
//        contextMenu.getItems().clear();
//
//        // Iterate through games machines
//        for (GameMachineMap.Entry<String, GamesMachine> gamesMachineEntry : gamesMachineMap.entrySet()) {
//            String gamesMachineName = gamesMachineEntry.getKey();
//            GamesMachine gamesMachine = gamesMachineEntry.getValue();
//
//            MenuItem gamesMachineItem = new MenuItem(gamesMachineName);
//
//            // Add an event handler for the games machine menu item
//            gamesMachineItem.setOnAction(event -> handleGamesMachineSelection(gamesMachine));
//
//            // Add the games machine menu item to the context menu
//            contextMenu.getItems().add(gamesMachineItem);
//        }
//    }
//
//    private void handleGamesMachineSelection(GamesMachine gamesMachine) {
//        // Handle the selection of a games machine
//        // You can implement the logic for the drill-down menu here
//        System.out.println("Selected Games Machine: " + gamesMachine);
//    }
//
//    private void populateData() {
//        String machineName= String.valueOf(machineNameField);
//        String manufacturer= String.valueOf(manufacturerField);
//        String machineDescription= String.valueOf(machineDescriptionField);
//        String gameMachineType= String.valueOf(gameMachineTypeField);
//        String mediaType= String.valueOf(mediaTypeField);
//        int initialLaunchYear= Integer.parseInt(String.valueOf(initialLaunchYearField));
//        double initialRRP= Double.parseDouble(String.valueOf(gameNameField));
//        String photoURL= String.valueOf(gameNameField);
//        GamesMachine gamesMachine1 = new GamesMachine(machineName,manufacturer,machineDescription,gameMachineType,mediaType,initialLaunchYear,initialRRP,photoURL);
//
//        String gameName = String.valueOf(gameNameField);
//        String publisher= String.valueOf(publisherField);
//        String gameDescription= String.valueOf(gameDescriptionField);
//        String developer= String.valueOf(developerField);
//        GamesMachine originalGamesMachineDevelopedFor = findOrCreateGamesMachine(String.valueOf(machineNameField));
//        int initialReleaseYear= Integer.parseInt(String.valueOf(initialReleaseYearField));
//        String coverArtImageGame= String.valueOf(gameNameField);
//        // Create a game and add it to the games machine
//        Game game1 = new Game(gameName,publisher,gameDescription,developer,originalGamesMachineDevelopedFor,initialReleaseYear,coverArtImageGame);
//
//
//        gamesMachine1.getGames().put(String.valueOf(Integer.parseInt("Game1")), game1);
//        String portDeveloper= String.valueOf(gameNameField);
//        String originalGame= String.valueOf(originalGameField);
//        int initialReleaseYearOfGamePort = Integer.parseInt(String.valueOf(gameNameField));
//
//        // Create a game port and add it to the game
//        GamePort gamePort1 = new GamePort(portDeveloper,originalGame,initialReleaseYearOfGamePort);
//        game1.getGamePorts().put(String.valueOf(Integer.parseInt("GamePort1")), gamePort1);
//
//        // Add the games machine to the gamesMachineMap
//        gamesMachineMap.putGamesMachine("GamesMachine1", gamesMachine1);
//    }



}



