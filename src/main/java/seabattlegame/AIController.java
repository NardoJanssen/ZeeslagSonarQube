package seabattlegame;

import models.AI;
import models.Ship;
import seabattlegui.ShipType;
import seabattlegui.SquareState;

import java.util.ArrayList;
import java.util.Random;

public class AIController implements OtherPlayerController {
    private ArrayList<Ship> aiShips = new ArrayList<>();
    private ArrayList<ShipType> aiShipTypes = new ArrayList<>();
    private AI ai = new AI();
    private ArrayList<String> aiShotCoords = new ArrayList<>();
    private int randomX;
    private int randomY;

    public void placeShipsAutomatically()
    {
        Random rndX = new Random();
        Random rndY = new Random();
        int coordX;
        int coordY;
        int shipTypeInt = 0;
        boolean placingShipsAutomaticDone = false;

        while (!placingShipsAutomaticDone)
        {
            coordX = rndX.nextInt(10);
            coordY = rndY.nextInt(10);
            ShipType shipType = ShipType.valueOf(shipTypeInt);

            if (!aiShipTypes.contains(shipType))
            {
                if (placeShip( shipType, coordX, coordY, true) || placeShip(shipType , coordX, coordY, false))
                {
                    shipTypeInt++;
                }
            }
            if (aiShipTypes.size() >= 5 && aiShips.size() >= 5)
            {
                placingShipsAutomaticDone = true;
            }
        }
    }

    public boolean placeShip(ShipType aiShipType, int bowX, int bowY, boolean horizontal)
    {
        ArrayList<Integer> Xcoords = new ArrayList<>();
        ArrayList<Integer> Ycoords = new ArrayList<>();

        if (!aiShipTypes.contains(aiShipType))
        {
            if (!horizontal)
            {
                Xcoords.add(bowX);
                Ycoords = getShipYCoords(aiShipType, bowY, Ycoords);

                if (Ycoords.get(Ycoords.size() - 1) >= 10 || checkIfOverlappingShips(Xcoords, Ycoords))
                {
                    return false;
                }
            }
            else
            {
                Xcoords = getShipXCoords(aiShipType, bowX, Xcoords);
                Ycoords.add(bowY);

                if (Xcoords.get(Xcoords.size() - 1) >= 10 || checkIfOverlappingShips(Xcoords, Ycoords))
                {
                    return false;
                }
            }
            aiShips.add(new Ship(Xcoords, Ycoords, aiShipType, horizontal));
            aiShipTypes.add(aiShipType);

            return true;
        }

        return false;
    }

    public Ship removeShip(int playerNr, int posX, int posY) {
        Ship removeShip = new Ship();
        boolean foundShip = false;

        if (!aiShips.isEmpty() || !aiShipTypes.isEmpty()) {
            for (Ship ship : aiShips) {
                if (ship.isHorizontal()) {
                    for (int Xcoord : ship.getCoordX()) {
                        if (Xcoord == posX && ship.getCoordY().get(0) == posY) {
                            removeShip = ship;
                            foundShip = true;
                        }
                    }
                } else {
                    for (int Ycoord : ship.getCoordY()) {
                        if (ship.getCoordX().get(0) == posX && Ycoord == posY) {
                            removeShip = ship;
                            foundShip = true;
                        }
                    }
                }
            }
        }
        if (foundShip) {
            aiShips.remove(removeShip);
            aiShipTypes.remove(removeShip.getShipType());
        }

        return removeShip;
    }

    public boolean checkIfOverlappingShips(ArrayList<Integer> Xcoords, ArrayList<Integer> Ycoords)
    {
        for (Ship ship : aiShips)
        {
            for (int Xcoord : Xcoords)
            {
                if (ship.getCoordX().contains(Xcoord))
                {
                    for (int Ycoord : Ycoords)
                    {
                        if (ship.getCoordY().contains(Ycoord))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Ship> getAllShips()
    {
        return aiShips;
    }

    public SquareState firedShot(int playerNr, int posX, int posY) {
        //Player shoots on square, check if hit an AI-ship
        SquareState squareState = SquareState.SHOTMISSED;
        for (Ship ship : aiShips)
        {
            if (ship.getCoordX().contains(posX) && ship.getCoordY().contains(posY))
            {
                ship.setHitCounter(ship.getHitCounter() - 1);
                if (ship.getHitCounter() == 0)
                {
                    squareState = SquareState.SHIPSUNK;
                }
                else
                {
                    squareState = SquareState.SHOTHIT;
                }
            }
        }

        return squareState;
    }

    public void shoots()
    {
        boolean aiTurn = true;

        while (aiTurn)
        {
            randomX = ai.getRandomX();
            randomY = ai.getRandomY();
            String randomCoords = randomX + "," + randomY;

            if (!aiShotCoords.contains(randomCoords))
            {
                aiShotCoords.add(randomCoords);
                aiTurn = false;
            }
        }
    }

    public int aiGetX() {
        return randomX;
    }

    public int aiGetY() {
        return randomY;
    }

    public ArrayList<Ship> getShips() {
        return aiShips;
    }

    public ArrayList<Integer> getShipXCoords(ShipType shipType, int bowX, ArrayList<Integer> coords)
    {
        //Horizontal increases
        for (int i = 0; i < shipType.getSize(shipType); i++)
        {
            coords.add(bowX);
            bowX++;
        }

        return coords;
    }

    public ArrayList<Integer> getShipYCoords(ShipType shipType, int bowY, ArrayList<Integer> coords)
    {
        //Vertical increases
        for (int i = 0; i < shipType.getSize(shipType); i++)
        {
            coords.add(bowY);
            bowY++;
        }

        return coords;
    }
}
