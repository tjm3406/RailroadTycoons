package student;

import java.util.ArrayList;
import model.Baron;
import model.RailroadBaronsObserver;

/**
 * This is the class that handles the lonely version of the game (vs. 3 AI)
 */
public class RailroadBaronsLonely extends RailroadBarons {

  public RailroadBaronsLonely(){
    super.observers = new ArrayList<>();
    super.players = new ArrayList<>();

    model.Player player1 = new Player(Baron.RED);
    ComputerPlayer player2 = new ComputerPlayer(Baron.BLUE, this);
    ComputerPlayer player3 = new ComputerPlayer(Baron.GREEN, this);
    ComputerPlayer player4 = new ComputerPlayer(Baron.YELLOW, this);
    players.add(player1);
    players.add(player2);
    players.add(player3);
    players.add(player4);

  }



}
