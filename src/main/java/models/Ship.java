package models;

import seabattlegui.ShipType;

import java.util.ArrayList;

public class Ship {

    private ArrayList<Integer> coordX;
    private ArrayList<Integer> coordY;
    private ShipType shipType;
    private int hitCounter;
    private boolean horizontal;

    public Ship(ArrayList<Integer> coordX, ArrayList<Integer> coordY, ShipType shipType, boolean horizontal) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.shipType = shipType;
        this.horizontal = horizontal;
        this.hitCounter = shipType.getSize(shipType);
    }

    public Ship() {
    }

    public ArrayList<Integer> getCoordX() {
        return coordX;
    }

    public void setCoordX(ArrayList<Integer> coordX) {
        this.coordX = coordX;
    }

    public ArrayList<Integer> getCoordY() {
        return coordY;
    }

    public void setCoordY(ArrayList<Integer> coordY) {
        this.coordY = coordY;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public int getHitCounter() {
        return hitCounter;
    }

    public void setHitCounter(int hitCounter) {
        this.hitCounter = hitCounter;
    }
}
