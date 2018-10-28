package seabattlegame;

import models.Ship;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.SquareState;

import java.util.ArrayList;
import java.util.Random;

public class PlayerShipsController{
    // Arrays of ships
    private ArrayList<Ship> ships = new ArrayList<>();
    private ArrayList<ShipType> shipTypes = new ArrayList<>();
    private ArrayList<String> playerShotCoords = new ArrayList<>();

    public Ship getLastAddedShip()
    {
        return (ships.get(ships.size() - 1));
    }

    public ArrayList<Ship> getAllPlayerShips()
    {
        return ships;
    }

    public void registerPlayer(String name, String password, ISeaBattleGUI application, boolean singlePlayerMode)
    {

    }

    public void placeShipsAutomatically(int playerNr)
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

            if (!shipTypes.contains(shipType))
            {
                if (placeShip(playerNr, shipType, coordX, coordY, true) || placeShip(playerNr, shipType , coordX, coordY, false))
                {
                    shipTypeInt++;
                }
            }
            if (shipTypes.size() >= 5 && ships.size() >= 5)
            {
                placingShipsAutomaticDone = true;
            }
        }
    }

    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal)
    {
        ArrayList<Integer> Xcoords = new ArrayList<>();
        ArrayList<Integer> Ycoords = new ArrayList<>();

        if (!shipTypes.contains(shipType))
        {
            if (horizontal)
            {
                Xcoords = getShipXCoords(shipType, bowX, Xcoords);
                Ycoords.add(bowY);

                if (Xcoords.get(Xcoords.size() - 1) >= 10 || checkIfOverlappingShips(Xcoords, Ycoords))
                {
                    return false;
                }
            }
            else
            {
                Xcoords.add(bowX);
                Ycoords = getShipYCoords(shipType, bowY, Ycoords);

                if (Ycoords.get(Ycoords.size() - 1) >= 10 || checkIfOverlappingShips(Xcoords, Ycoords))
                {
                    return false;
                }
            }
            Ship ship = new Ship(Xcoords, Ycoords, shipType, horizontal);
            ships.add(ship);
            shipTypes.add(shipType);

            return true;
        }

        return false;
    }

    public boolean checkIfOverlappingShips(ArrayList<Integer> Xcoords, ArrayList<Integer> Ycoords) {
        for (Ship ship : ships)
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



    public Ship removeShip(int posX, int posY) {
        Ship removeShip = new Ship();
        boolean foundShip = false;

        if (!ships.isEmpty() || !shipTypes.isEmpty())
        {
            for (Ship ship : ships)
            {
                if (ship.isHorizontal())
                {
                    for (int Xcoord : ship.getCoordX())
                    {
                        if (Xcoord == posX && ship.getCoordY().get(0) == posY)
                        {
                            removeShip = ship;
                            foundShip = true;
                        }
                    }
                }
                else
                {
                    for (int Ycoord : ship.getCoordY())
                    {
                        if (ship.getCoordX().get(0) == posX && Ycoord == posY)
                        {
                            removeShip = ship;
                            foundShip = true;
                        }
                    }
                }
            }
        }
        if (foundShip)
        {
            ships.remove(removeShip);
            shipTypes.remove(removeShip.getShipType());
        }

        return removeShip;
    }

    public void removeAllShips(int playerNr)
    {
        ships.clear();
        shipTypes.clear();
    }

    public boolean notifyWhenReady(int playerNr)
    {
        return shipTypes.size() == 5 && ships.size() == 5;
    }

    public SquareState firedShot(int playerNr, int posX, int posY)
    {
        //Word uitgevoerd door de AI
        SquareState shipsunk = SquareState.SHOTMISSED;
        if (playerNr == 0)
        {
            for (Ship ship : ships)
            {
                if (ship.getCoordX().contains(posX) && ship.getCoordY().contains(posY))
                {
                    ship.setHitCounter(ship.getHitCounter() - 1);
                    if (ship.getHitCounter() == 0)
                    {
                        shipsunk = SquareState.SHIPSUNK;
                    } else
                    {
                        shipsunk = SquareState.SHOTHIT;
                    }
                }
            }
        }
        return shipsunk;
    }

    public void startNewGame(int playerNr)
    {

    }

    public boolean playerShoots(int playerNr, int x, int y)
    {
        boolean playerTurn = true;

        while (playerTurn)
        {
            String coords = x + "," + y;

            if (!playerShotCoords.contains(coords))
            {
                playerShotCoords.add(coords);
                return true;
            }
            else
            {
                break;
            }
        }
        return false;
    }
}
