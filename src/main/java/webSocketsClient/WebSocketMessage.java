package webSocketsClient;

import models.Ship;

import javax.websocket.Session;
import java.io.IOException;

public class WebSocketMessage
{
    Session session;

    public WebSocketMessage(Session session)
    {
        this.session = session;
    }

    private void SendMessage(String message)
    {
        try
        {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void firedShot(int playerNr, int x, int y)
    {
        String message = "shot " + x + "-" + y + " player " + playerNr;
        //send message to server, wait for response, return that
        System.out.println(message);
        SendMessage(message);
    }

    public void sendShip(int playerNr, Ship ship)
    {
        String message = "ship [";
        if (ship.isHorizontal())
        {
            int i = 0;
            for (int coord : ship.getCoordX())
            {
                i++;
                message += coord + "-" + ship.getCoordY().get(0) + ((ship.getCoordX().size() > i) ? "," : "");
            }
        }
        else
        {
            int i = 0;
            for (int coord : ship.getCoordY())
            {
                i++;
                message += ship.getCoordX().get(0) + "-" + coord + ((ship.getCoordY().size() > i) ? "," : "");
            }
        }
        message += "] " + ship.getShipType() + " player " + playerNr;

        System.out.println(message);
        //send message to server, wait for response, return that
        SendMessage(message);
    }

    public void readyUp(int playerNr)
    {
        String message = "ready " + playerNr;
        System.out.println(message);
        SendMessage(message);
    }

    public void newGame()
    {
        //Alleen voor nieuw spel, niet voor 1ste game.
        String message = "newgame";
        System.out.println(message);
        SendMessage(message);
    }
}