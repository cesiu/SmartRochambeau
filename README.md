[![Build Status](https://travis-ci.org/cpe305/fall2016-project-cesiu.svg?branch=master)](https://travis-ci.org/cpe305/fall2016-project-cesiu)

# SmartRochambeau
![startmenu](https://raw.githubusercontent.com/cpe305/fall2016-project-cesiu/master/images/Screen%20Shot%202016-12-08%20at%207.20.10%20PM.png)
SmartRochambeau is a single player Rock-Paper-Scissors game featuring four
different AI opponents, developed as an individual project for Professor
Gudrun Socher's CPE 305 at Cal Poly, Fall 2016.

## Installing
The cross-platform JAR (`SmartRochambeau.jar`) is required to run the game. 

## Running
SmartRochambeau can be run by double clicking on the JAR or from the command 
line using `java -jar SmartRochambeau.jar`. The command line mode can be 
reached by adding the `-c` flag.
![mainscreen](https://raw.githubusercontent.com/cpe305/fall2016-project-cesiu/master/images/Screen%20Shot%202016-12-08%20at%207.21.31%20PM.png)
In addition to providing AI opponents against which to play Rock-Paper-Scissors,
SmartRochambeau keeps statistics on the performance of each opponent and can
save its state when the player quits, allowing the machine learning algorithms
to pick up where they left off when a new session begins and allowing the 
algorithms to be compared.

## Opponents
SmartRochambeau features four AI opponents:
* A **random AI** as a control, which simply selects a random throw every round.
* A **Markov Chain AI** modeled using nine states and three intermediate states, 
  which answers the question, "Considering what the player threw last and 
  whether or not he won, what is he likely to throw next?". At any given time, 
  the current state is determined by the player's last throw and the result of 
  that round, e.g., "Rock, win". Each state contains frequencies logging how 
  often, when the game is at that current state, the player has moved according 
  to each of the three intermediate states, which contain only a throw, e.g., 
  "Paper". For example, the state "Rock, win" would contain three numbers 
  corresponding to how often, after playing Rock and winning, the player then 
  threw Rock, Paper, and Scissors. Based on those numbers, the AI can predict 
  the player's most likely next move. The AI trains as it runs, updating the 
  frequencies once it knows the outcome of the current round before it moves to
  the next state.
* A **Naive Bayes AI** -- implemented mostly as an experiment, not because I thought
  it was necessarily a good algorithm for this problem -- that attempts to
  answer the question, "Considering the player's last few throws, what is he
  leading up to throwing?". The idea being, a Markov-Chain-based is vulnerable
  because Rock-Paper-Scissors doesn't really satisfy the Markov Property; what
  the player does next is not solely dependant on what he did last. The Naive
  Bayes AI attempts to defeat 'longer' strategies wherein the player makes a
  few moves to set his opponent up for a later move. The AI maintains a queue
  of the player's last few throws as well as nine frequencies for how often each
  individual throw 'led up to' each of the other throws. For example, if the
  last three throws were "Rock, Paper, Rock", the AI can look at how often,
  historically, Rock and Paper have indivdually led up to Rock, Paper, or
  Scissors, then combine those probabilities using Bayes Rule. Like the Markov
  Chain AI, the Naive Bayes AI is capable of training as it goes, though this
  makes it vulnerable to overfitting and thus long term shifts in strategy.
* A **Pattern Matching AI**, which I personally predicted would do best of the 
  three, that attempts to answer the same question as the Naive Bayes AI, but
  based on the pattern of the individual throws, not their general overall
  frequencies. The AI maintains a complete ternary tree of throws to allow
  for faster pattern matching, with each possible pattern represented as a
  unique path from root to a leaf, and each leaf, as you might expect, contains
  frequencies for each of the three throws. The Pattern Matching AI, too, is
  capable of training as it runs..

![consoleview](https://raw.githubusercontent.com/cpe305/fall2016-project-cesiu/master/images/Screen%20Shot%202016-12-08%20at%207.22.54%20PM.png)

## Limitations
These AI all share a common drawback: training as they go makes them easy to
set up and play immediately, but it makes them vulnerable to overfitting. A
player might make a slight change to his strategy, and the AI might take
a long time to catch up.

One possible solution is to count how many times the AI has used a throw for 
training. After a certain threshold, whether that be related to number of throws
trained on, win rate, or some combination thereof, the AI stops saving
information after each round. After a certain amount of time or after the win
rate drops below some other threshold, the AI can start training again, perhaps
partially or completely "wiping the slate" and deleting some or all of its saved
information.

## Architecture
![class diagram](https://github.com/cpe305/fall2016-project-cesiu/blob/master/diagrams/classDiagram.png?raw=true)
SmartRochambeau uses a modified Model-View-Controller architecture pattern, with
a controller class providing a generalized interface to the UI for the core
logic and also giving the UI access to methods of the core logic. Both the UI
and the AI implement interfaces designed to make adding new machine learning AI
easy.
![mainclasses] (https://github.com/cpe305/fall2016-project-cesiu/blob/master/images/diagramMain.png?raw=true)
The core of SmartRochambeau is the GameModerator class, which handles all game
logic, the UIController class, which provides generalized methods for the 
GameModerator to notify user interfaces, the GameAI interface, which 
standardizes the hooks required for a functioning AI, and the GameSerializer
class, which saves and restores games.
![aiclasses] (https://github.com/cpe305/fall2016-project-cesiu/blob/master/images/diagramAI.png?raw=true)
Included are four AI classes, as elaborated upon above. Adding a new AI is as
simple as implementing the makeThrow method, which returns the AI's next choice
of move (and typically calls the prediction logic), and the storeResult method,
which returns the results of the last round to the AI so it can train itself
as appropriate.
![uiclasses] (https://github.com/cpe305/fall2016-project-cesiu/blob/master/images/diagramUI.png?raw=true)
On the UI end, both a controller and an interface are used: the interface
requires only that a UI have some method to display results. The UIController
then provides a number of methods to send or receive data to or from the 
GameModerator, which a UI may or may not need to call.

## Design Patterns
* The **Observer** pattern is combined with the MVC architecture pattern to 
  allow multiple types of user interfaces -- which may or may not be running on
  the same thread as the moderator -- to update their displays. Note, however,
  that a pure Observer patter is not used; the subject is instantiated with
  one observer, and no observers can be registered or unregistered. It is
  expected that a single player game will require one UI, and that won't change
  throughout a session.
* The **Strategy** pattern allows multiple AI to be easily implemented and
  swapped. Adding new AI strategies is made much easier. (Though making the UI
  changes to choose that new AI...still not so easy.)
* The **State** pattern is used, for obvious reasons, by the Markov Chain AI,
  since, well, Markov Chains represent transitions of states.
* The **Composite** pattern, similarly, is used by the Pattern Matching AI to
  represent its tree of patterns.

## Academic Takeaways
* Things I liked best:
  * First and foremost, I greatly enjoyed the opportunity to choose and propose
    my own project and its goals for this class. I know that it's not really 
    an industry-accurate scenario, but I had a lot more fun working on this
    project than on my 308/309 (Software Engineering I, II) project and my
    ongoing 402/405/406 (capstone) project.
    * On that note, that meant that I could decide on the process I liked best;
      I could start with Waterfall and move to Agile whenever I wanted to.
      (Even if I deviated from the schedule as the quarter got busier...)
  * Playtesting SmartRochambeau with my roommates appears to confirm my initial
    hypotheses that Pattern Matching would do well and Naive Bayes, less so.
    Always nice when that happens.
  * JavaFX is tedious and verbose, but it's amazing compared to Swing. It's
    like eating steak with a paring knife instead of with a butter knife.
* Key learnings:
  * I had an idea of how software process worked, and I'd done it in team
    settings, but this was the first time I'd gotten a chance to use it
    on an individual project, and it worked well. It kept me accountable.
