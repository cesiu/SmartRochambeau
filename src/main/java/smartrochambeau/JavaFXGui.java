package smartrochambeau;
/**
 * Controls the Java FX GUI.
 * @author cesiu
 * @version December 7, 2016, a date which will live in infamy.
 */

import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class JavaFXGui implements GameUI {
  @FXML
  private Pane startPane;
  @FXML
  private SplitMenuButton aiMenu;
  @FXML
  private MenuItem julieChoice;
  @FXML
  private MenuItem paulChoice;
  @FXML
  private MenuItem theresaChoice;
  @FXML
  private MenuItem dalbeyChoice;
  @FXML
  private Button playButton;
  @FXML
  private Button rockButton;
  @FXML
  private Button paperButton;
  @FXML
  private Button scissorsButton;
  @FXML
  private TextArea historyArea;
  @FXML
  private Label timerLabel;
  @FXML
  private Label julieWins;
  @FXML
  private Label julieLosses;
  @FXML
  private Label julieDraws;
  @FXML
  private Label paulWins;
  @FXML
  private Label paulLosses;
  @FXML
  private Label paulDraws;
  @FXML
  private Label theresaWins;
  @FXML
  private Label theresaLosses;
  @FXML
  private Label theresaDraws;
  @FXML
  private Label dalbeyWins;
  @FXML
  private Label dalbeyLosses;
  @FXML
  private Label dalbeyDraws;
  
  private HashMap<Class, HashMap<Integer, Label>> aiStats;
  private GameModerator.GameRound lastRound;
  private static UIController curController;
  private static Class curAI;
  private boolean isRunning = false;
  
  private Timer secondsTimer;
  private TimerTask timerTask;
  public static final int TIMER_INTERVAL = 1000;
  
  public JavaFXGui(UIController curController) {
    this.curController = curController;
    
    // Default to pattern matching.
    curAI = PatternMatchingAI.class;
    this.curController.getGame().setAI(curAI);
    
    aiStats = new HashMap<Class, HashMap<Integer, Label>>();
    
    secondsTimer = new Timer();
    timerTask = new TimerTask() {
      public static final int MAX_TIME = 11;
      private int timeRemaining = MAX_TIME;
      public boolean cancel() {
        timeRemaining = MAX_TIME;
        return true;
      }
      public void run() {
        Platform.runLater(new Runnable() {
          public void run() {
            if (timerLabel != null && historyArea != null && isRunning) {
              if (timeRemaining > 0) {
                timeRemaining--;
                timerLabel.setText(String.format("00:%02d", timeRemaining));
              }
              else {
                // Perform first-time setup:
                if (aiStats.size() == 0) {
                  statSetup();
                }
                
                historyArea.appendText("\nYou took too long; you lose.\n");
                Label tempLabel = aiStats.get(curAI).get(1);
                tempLabel.setText(Integer.toString(Integer.parseInt(tempLabel.getText()) + 1));
                timeRemaining = MAX_TIME;
              }
            }
          }
        });
      }
    };
        
    secondsTimer.scheduleAtFixedRate(timerTask, 0, TIMER_INTERVAL);
  }
  
  public void statSetup() {
    // Add labels if necessary.
    if (aiStats.size() == 0) {
      HashMap<Integer, Label> temp = new HashMap<Integer, Label>();
      temp.put(1, julieWins);
      temp.put(-1, julieLosses);
      temp.put(0, julieDraws);
      aiStats.put(MarkovAI.class, temp);
      
      temp = new HashMap<Integer, Label>();
      temp.put(1, paulWins);
      temp.put(-1, paulLosses);
      temp.put(0, paulDraws);
      aiStats.put(NaiveBayesAI.class, temp);
      
      temp = new HashMap<Integer, Label>();
      temp.put(1, theresaWins);
      temp.put(-1, theresaLosses);
      temp.put(0, theresaDraws);
      aiStats.put(PatternMatchingAI.class, temp);
      
      temp = new HashMap<Integer, Label>();
      temp.put(1, dalbeyWins);
      temp.put(-1, dalbeyLosses);
      temp.put(0, dalbeyDraws);
      aiStats.put(RandomAI.class, temp);
    }
    
    // Restore existing stats.
    HashMap<Class, int[]> tempStats = curController.getGame().getStats();
    if (tempStats != null && tempStats.size() > 0) {
      for (Class tempAI : tempStats.keySet()) {
        aiStats.get(tempAI).get(1).setText(Integer.toString(tempStats.get(tempAI)[0]));
        aiStats.get(tempAI).get(-1).setText(Integer.toString(tempStats.get(tempAI)[1]));
        aiStats.get(tempAI).get(0).setText(Integer.toString(tempStats.get(tempAI)[2]));
      }
    }
  }
  
  @Override
  public void display() {
    // Shouldn't be necessary, but check for first time setup:
    if (aiStats.size() == 0) {
      statSetup();
    }
    
    lastRound = curController.getGame().getLastRound();
    historyArea.appendText("\nYou played " + lastRound.playerThrow 
                           + ", computer played " + lastRound.aiThrow
                           + "; " + (lastRound.result == -1 ? "you win" 
                           : lastRound.result== 0 ? "draw" : "you lose" ) + ".\n");
    
    Label tempLabel = aiStats.get(curAI).get(lastRound.result);
    tempLabel.setText(Integer.toString(Integer.parseInt(tempLabel.getText()) + 1));

    rockButton.setDisable(false);
    paperButton.setDisable(false);
    scissorsButton.setDisable(false);
  }
  
  public void rockClicked() {
    buttonClicked();
    curController.runRound(GameModerator.GameThrow.ROCK);
  }
  
  public void paperClicked() {
    buttonClicked();
    curController.runRound(GameModerator.GameThrow.PAPER);
  }
  
  public void scissorsClicked() {
    buttonClicked();
    curController.runRound(GameModerator.GameThrow.SCISSORS);
  }
  
  private void buttonClicked() {
    rockButton.setDisable(true);
    paperButton.setDisable(true);
    scissorsButton.setDisable(true);
    timerTask.cancel();
  }
  
  public void startClicked() {
    startPane.setVisible(false);
    isRunning = true;
  }
  
  public void juliePicked() {
    aiMenu.setText("Julie");
    curAI = MarkovAI.class;
    curController.getGame().setAI(curAI);
  }
  
  public void paulPicked() {
    aiMenu.setText("Paul");
    curAI = NaiveBayesAI.class;
    curController.getGame().setAI(curAI);
  }
  
  public void theresaPicked() {
    aiMenu.setText("Theresa");
    curAI = PatternMatchingAI.class;
    curController.getGame().setAI(curAI);
  }
  
  public void dalbeyPicked() {
    aiMenu.setText("Mr. Dalbey");
    curAI = RandomAI.class;
    curController.getGame().setAI(curAI);
  }
  
  public void saveClicked() {
    curController.saveGame();
    System.exit(0);
  }
}
