/*
 * Sea Battle Start project.
 */
package seabattlegui;

import java.util.HashMap;
import java.util.Map;

/**
 * Indicate type of ship.
 * @author Nico Kuijpers
 */
public enum ShipType
{
    AIRCRAFTCARRIER (0),  // Aircraft carrier (size 5)
    BATTLESHIP (1),       // Battle ship (size 4)
    CRUISER (2),          // Cruiser (size 3)
    SUBMARINE (3),        // Submarine (size 3)
    MINESWEEPER (4);      // Mine sweeper (size 2)

    private int value;
    private static Map map = new HashMap<>();

    ShipType(int value) {
        this.value = value;
    }

    static {
        for (ShipType pageType : ShipType.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static ShipType valueOf(int pageType) {
        return (ShipType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }

    public int getSize(ShipType type)
    {
        switch (type)
        {
            case AIRCRAFTCARRIER:
                return 5;
            case BATTLESHIP:
                return 4;
            case CRUISER:
                return 3;
            case SUBMARINE:
                return 3;
            case MINESWEEPER:
                return 2;
        }
        return 0;
    }
}
