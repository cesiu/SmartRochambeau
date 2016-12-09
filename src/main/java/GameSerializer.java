import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Provides a standardized method (no pun intended) for serialization of the states of games of
 * SmartRochambeau.
 * 
 * @author cesiu
 * @author dpolansky
 * @version October 12, 2016
 */

public class GameSerializer {

  public static final String SERIALIZATION_FILE_NAME = "game_data.ser";

  public static void saveGame(GameModerator curGame) throws IOException {
    // try with resource to handle proper closing, but still throw exceptions to let the client
    // handle it.
    try (FileOutputStream fileOut = new FileOutputStream(SERIALIZATION_FILE_NAME);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);) {

      out.writeObject(curGame);
    }
  }

  public static GameModerator restoreGame() throws IOException, ClassNotFoundException {
    GameModerator game = null;

    // try with resource to handle proper closing, but still throw exceptions to let the client
    // handle it.
    try (FileInputStream fileIn = new FileInputStream(SERIALIZATION_FILE_NAME);
        ObjectInputStream in = new ObjectInputStream(fileIn);) {

      game = (GameModerator) in.readObject();
    }

    return game;
  }
}
