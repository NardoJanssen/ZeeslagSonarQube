package models;

import java.util.Random;

public class AI {
    private Random rndX = new Random();
    private Random rndY = new Random();

    public int getRandomX()
    {
        return rndX.nextInt(10);
    }

    public int getRandomY()
    {
        return rndY.nextInt(10);
    }
}
