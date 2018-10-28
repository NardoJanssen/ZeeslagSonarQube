package seabattlegame;

import models.Ship;
import webSocketsClient.WebSocketMessage;

import javax.websocket.Session;

public class WebSocketMessager
{
    WebSocketMessage webSocketMessage;

    public WebSocketMessager(Session session)
    {
        this.webSocketMessage = new WebSocketMessage(session);
    }

    public void firedShot(int playerNr, int x, int y)
    {
        webSocketMessage.firedShot(playerNr, x, y);
    }

    public void sendShip(int playerNr, Ship ship)
    {
        webSocketMessage.sendShip(playerNr, ship);
    }
}
