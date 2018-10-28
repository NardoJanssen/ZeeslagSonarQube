/*
 * Sea Battle Start project.
 */
package seabattlegame;

import org.junit.jupiter.api.*;
import seabattlegui.SquareState;

/**
 * Unit tests for Sea Battle game.
 * @author Nico Kuijpers
 */
public class SeaBattleGameTest {
    
    private ISeaBattleGame game;
    private MockSeaBattleApplication applicationPlayer;
    private MockSeaBattleApplication applicationOpponent;
    
    public SeaBattleGameTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        
        // Create the Sea Battle game
        game = new SeaBattleGame();
        
        // Create mock Sea Battle GUI for player
        applicationPlayer = new MockSeaBattleApplication();
        
        // Create mock Sea Battle GUI for opponent
        applicationOpponent = new MockSeaBattleApplication();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Example test for method registerPlayerName().
     * Test whether an IllegalArgumentException is thrown when parameter 
     * name is null.
     * @author Nico Kuijpers
     */
    @Test
    public void testRegisterPlayerNameNull() {

        try
        {
            // Register player with parameter name null in single-player mode
            String name = null;
            String password = "password";
            boolean singlePlayerMode = true;
            game.registerPlayer(name, password, applicationPlayer, singlePlayerMode);
            Assertions.fail(); // Fails because exception is not caught
        }
        catch (IllegalArgumentException e)
        {
            // succeed
        }
    }
    
    /**
     * Example test for method placeShipsAutomatically().
     * Test whether the correct number of squares contain a ship in the
     * ocean area of the player's application.
     * @Author Nico Kuijpers
     */
    @Test
    public void testPlaceShipsAutomatically() {
        
        // Register player in single-player mode
        game.registerPlayer("Some Name", "Some Password", applicationPlayer, true);
        
        // Place ships automatically
        int playerNr = applicationPlayer.getPlayerNumber();
        game.placeShipsAutomatically(playerNr);
        
        // Count number of squares where ships are placed in player's application
        int expectedResult = 5 + 4 + 3 + 3 + 2;
        int actualResult = applicationPlayer.numberSquaresPlayerWithSquareState(SquareState.SHIP);
        Assertions.assertEquals(expectedResult, actualResult, "Wrong number of squares where ships are placed");
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. 
}

