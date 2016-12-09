/**
 * Contains a main method to start and run a game of SmartRochambeau.
 * 
 * @author cesiu
 */

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SmartRochambeau extends Application {
  private static UIController curInterface;
  private static GameUI curUI;
  
  public static void main(String[] args) {
    if (curInterface == null) {
      File serFile = new File(GameSerializer.SERIALIZATION_FILE_NAME);
      if (serFile.exists() && !serFile.isDirectory()) {
        curInterface = new UIController(true);
      }
      else {
        curInterface = new UIController(false);
      }
    }
    
    if (args.length > 0 && args[0].equals("-c")) {
      curUI = new ConsoleUI(curInterface);
      curInterface.setUI(curUI);
      ((ConsoleUI)curUI).run();
    }
    else {
      curUI = new JavaFXGui(curInterface);
      curInterface.setUI(curUI);
      launch(args);
    }
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      FXMLLoader loader  = new FXMLLoader(getClass().getResource("SmartRochambeau.fxml"));
      loader.setController(curUI);
      Parent root = loader.load();
      
      Scene scene = new Scene(root,700,500);
      primaryStage.setScene(scene);
      primaryStage.show();
      
      ((JavaFXGui)curUI).statSetup();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
