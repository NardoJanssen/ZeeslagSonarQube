package seabattlegame;

import models.Ship;
import seabattlegui.ShipType;
import seabattlegui.SquareState;

import java.util.ArrayList;

public interface OtherPlayerController {
    void placeShipsAutomatically();
    boolean placeShip(ShipType aiShipType, int bowX, int bowY, boolean horizontal);
    Ship removeShip(int playerNr, int posX, int posY);
    boolean checkIfOverlappingShips(ArrayList<Integer> Xcoords, ArrayList<Integer> Ycoords);
    ArrayList<Ship> getAllShips();
    SquareState firedShot(int playerNr, int posX, int posY);
    void shoots();
    ArrayList<Ship> getShips();
    int aiGetX();
    int aiGetY();
    ArrayList<Integer> getShipXCoords(ShipType shipType, int bowX, ArrayList<Integer> coords);
    ArrayList<Integer> getShipYCoords(ShipType shipType, int bowY, ArrayList<Integer> coords);
}
