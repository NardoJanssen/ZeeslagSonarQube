/*
 * Sea Battle Start project.
 */
package seabattlegame;

import models.Ship;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.SquareState;

import java.util.ArrayList;

/**
 * The Sea Battle game. To be implemented.
 * @author Nico Kuijpers
 */
public class SeaBattleGame implements ISeaBattleGame {
    private PlayerShipsController playerShipsController;

    public SeaBattleGame() {
        playerShipsController = new PlayerShipsController();
    }


    @Override
    public Ship getLastAddedShip() {
        return playerShipsController.getLastAddedShip();
    }

    @Override
    public ArrayList<Ship> getAllPlayerShips() {
        return playerShipsController.getAllPlayerShips();
    }

    @Override
    public void registerPlayer(String name, String password, ISeaBattleGUI application, boolean singlePlayerMode) {

    }

    @Override
    public void placeShipsAutomatically(int playerNr) {
        playerShipsController.placeShipsAutomatically(playerNr);
    }

    @Override
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        return playerShipsController.placeShip(playerNr, shipType, bowX, bowY, horizontal);
    }

    @Override
    public Ship removeShipPlayer(int playerNr, int posX, int posY) {
        return playerShipsController.removeShip(posX, posY);
    }

    @Override
    public void removeAllShips(int playerNr) {
        playerShipsController.removeAllShips(playerNr);
    }

    @Override
    public boolean notifyWhenReady(int playerNr) {
        return playerShipsController.notifyWhenReady(playerNr);
    }

    @Override
    public SquareState fireShot(int playerNr, int posX, int posY) {
        //Word aangeroepen door AI/enemy player
        return playerShipsController.firedShot(playerNr, posX, posY);
    }

    @Override
    public void startNewGame(int playerNr) {

    }

    @Override
    public boolean checkIfShotThisSpotAlready(int playerNr, int x, int y) {

        return playerShipsController.playerShoots(playerNr, x, y);
    }
}
