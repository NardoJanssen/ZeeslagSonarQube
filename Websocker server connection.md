#The wesbsocket communication
The websocket server will connect to the server and the server will send back a few messages.
The communication between the 2 will look like a command type connection.

For example:

* shot 4-4 player 15 
* message "hallo daar" player 15
* shot 4-4 player 15 miss
* ship [1-4,2-4,3-4,4-4] ship-type(b.v. cruiser) player 15


## client -> server
- player player-id
    - (Actie is gedaan door specifieke speler en tijdens welke sessie)
- shot x-y						
    - (Schot is afgevuurt op coordinaten)
- message "message-text"			
    - (Eventuele chat verstuurd)
- ready player-id		
    - (Speler geeft aan dat hij/zij klaar is om te beginnen)
- newgame								
    - (Speler geeft aan dat hij/zij een nieuw spel wilt beginnen)
- ships
    - (Een array val alle schepen en de coordinaten ervan)


## server -> client
- readyup
    - (Players can start placing ships)
- shot x-y [result] 				
    - (response when a player shoots, contains the coordinates of the shot and the result)
- game player-id		
    - (response when new game is requested, sent to all players but player id is different)
- startgame 							
    - (sent to all clients) 
- startturn 							
    - (sent to the client who's turn it is)