package smartrochambeau;
/*
 * A Rochambeau AI that makes throws based on pattern matching.
 * @author cesiu
 * @version November 17, 2016
 */

import java.io.Serializable;
import java.util.LinkedHashMap;

public class PatternMatchingAI extends GameAI implements Serializable {
  // The root of the pattern matching tree
  private GameNode patternRoot;
  // The linked list of the most recent pattern
  private GameNode queueHead;
  private GameNode queueTail;

  /**
   * Constructs a PatternMatchingAI with no training.
   */
  public PatternMatchingAI() {
    patternRoot = createSubtree(4);

    // Assume the initial pattern is Rock, Paper, Scissors.
    queueHead = new GameNode();
    queueHead.curThrow = GameModerator.GameThrow.ROCK;
    queueTail = queueHead;
    queueTail.next = new GameNode();
    queueTail = queueTail.next;
    queueTail.curThrow = GameModerator.GameThrow.PAPER;
    queueTail.next = new GameNode();
    queueTail = queueTail.next;
    queueTail.curThrow = GameModerator.GameThrow.SCISSORS;
  }

  /**
   * Checks the current state, makes a prediction, and returns the next throw.
   * 
   * @return A valid GameThrow
   */
  @Override
  public GameModerator.GameThrow makeThrow() {
    GameNode curLeaf = searchTree(queueHead);

    int numRocks = curLeaf.frequencies.get(GameModerator.GameThrow.ROCK);
    int numPapers = curLeaf.frequencies.get(GameModerator.GameThrow.PAPER);
    int numScissors = curLeaf.frequencies.get(GameModerator.GameThrow.SCISSORS);

    return GameAI.analyzeThrow(numRocks, numPapers, numScissors);
  }

  /** 
   * Updates the tree with the result of the last round.
   * 
   * @param playerThrow What the player threw last round
   * @param result The result of the last round
   */
  @Override
  public void storeResult(GameModerator.GameThrow playerThrow, int result) {
    GameNode oldLeaf = searchTree(queueHead);

    oldLeaf.frequencies.put(playerThrow,
                            oldLeaf.frequencies.get(playerThrow) + 1);

    queueTail.next = new GameNode();
    queueTail.next.curThrow = playerThrow;
    queueTail = queueTail.next;
    queueHead = queueHead.next;
  }

  /**
   * Stringifies the currently tracked pattern and frequencies based on it.
   *
   * @return The string.
   */
  @Override
  public String toString() {
    GameNode curLeaf = searchTree(queueHead);

    return "The currently tracked pattern is:\n   " + queueHead.curThrow
           + "\n   " + queueHead.next.curThrow + "\n   " + queueTail.curThrow 
           + "\n...which in the past leads to:\n   Rock: "
           + curLeaf.frequencies.get(GameModerator.GameThrow.ROCK) 
           + "\n   Paper: " + curLeaf.frequencies.get(
           GameModerator.GameThrow.PAPER) + "\n   Scissors: "
           + curLeaf.frequencies.get(GameModerator.GameThrow.SCISSORS);
  }

  /**
   * Searches the tree for a pattern.
   *
   * @param tempHead The head of the linked list pattern 
   * @return The resulting leaf
   */
  private GameNode searchTree(GameNode tempHead) {
    // Start at the root.
    GameNode tempNode = patternRoot;

    // For every node in the linked list...
    while (tempHead != null) {
      // Follow the current pattern.
      tempNode = tempNode.children.get(tempHead.curThrow);
      tempHead = tempHead.next;
    }

    // Return the leaf.
    return tempNode;
  }

  /**
   * Recursively creates a subtree.
   *
   * @param depth The depth of the subtree
   * @return The root of the subtree
   */
  private GameNode createSubtree(int depth) {
    if (depth == 0)
      return null;

    GameNode tempNode = new GameNode();
    tempNode.children = new LinkedHashMap<>();
    tempNode.frequencies = new LinkedHashMap<>();

    for (GameModerator.GameThrow tempThrow : GameModerator.GameThrow.values()) {
      tempNode.frequencies.put(tempThrow, 0);
      tempNode.children.put(tempThrow, createSubtree(depth - 1));
    }

    return tempNode;
  }

  /**
   * Represents one node in a tree or linked list.
   */
  private class GameNode implements Serializable {
    // The throw represented by this node
    private GameModerator.GameThrow curThrow;
    // The next node when used in a linked list
    private GameNode next;

    // The children of this node
    private LinkedHashMap<GameModerator.GameThrow, GameNode> children;
    // The frequencies of the children
    private LinkedHashMap<GameModerator.GameThrow, Integer> frequencies;
  }
}
