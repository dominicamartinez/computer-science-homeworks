/* C SC 335, Spring 2003
 * Homework 4: Hangman Applet
 *
 * You should finish this mostly-done model.
 * Make it an observable model that notifies its observers
 * when important changes occur.
 */
package hangman.model;

import java.util.*;


/** Models a Hangman game using a singleton instance.
 *  Uses observer design pattern by extending Observable.
 *
 *  @author Martin Stepp (stepp@cs.arizona.edu)
 *  @version 1.0
 */
public class HangmanModel extends Observable {
  /** Constants for the game's state, which you can test
   *  against the value returned by getState().
   */
  public static final Object IN_PROGRESS = "In progress",
    GAME_LOST = "Game over (lost)",
    GAME_WON  = "Game over (won)";

  // number of guesses before game is over
  private static final int MAX_GUESSES = 6;

  // letter to fill in for unknown unguessed letters
  private static final char UNKNOWN_LETTER = '?';

  // list of (hard-coded GRE vocab) words to use
  private static final String[] ourWords = {
    "accretion", "boisterous", "dullard", "feigned", "haughty", "insipid",
    "noisome", "obdurate", "parsimonious", "sycophant"
  };

  // singleton instance
  private static final HangmanModel ourInstance = new HangmanModel();

  /** Returns singleton instance of this model class.
   *  @return the singleton model object.
   */
  public static HangmanModel getInstance()
  {
    return ourInstance;
  }


  // instance variables
  private String mySecretWord;
  private String myGuessWord;
  private String myGuessWordedLetters;
  private Object myState;
  private int myNumGuesses;


  // forbidden constructor; preserves singleton
  private HangmanModel()
  {
    reset();
  }

  /** Clears guesses and picks a new word, starting a new game. */
  public void reset()
  {
    int rand = (int)(Math.random() * ourWords.length);

    mySecretWord = ourWords[rand];
    int wordLength = mySecretWord.length();
    myGuessWord = "";
    for (int ii = 0;  ii < wordLength;  ii++)
      myGuessWord += UNKNOWN_LETTER;
    myGuessWordedLetters = "";
    myNumGuesses = MAX_GUESSES;
    myState = IN_PROGRESS;

    // added this
    setChanged();
    notifyObservers();
  }

  /** Returns a string representing the current word being guessed at.
   *  For example, if the current word is "banana", the value returned by this
   *  method will be "banana".
   *
   *  @return the answer string.
   */
  public String getSecretWord()
  {
    return mySecretWord;
  }

  /** Returns a string representing the current guesses made in this game.
   *  For example, if the current word is "banana" and the user has guessed
   *  'b', 'a' and 'q', the value returned by this method will be "ba?a?a".
   *
   *  @return the guess string.
   */
  public String getGuessWord()
  {
    return myGuessWord;
  }

  /** Returns the number of guesses left in this game.
   *  This number will decrement every time guess(char) is called
   *  using a letter that isn't part of the current word.
   *
   *  @return the number of guesses.
   */
  public int getNumGuessesLeft()
  {
    return myNumGuesses;
  }

  /** Returns the current state of this model, which will be one of
   *  Model.NO_GAME, IN_PROGRESS, GAME_LOST, or GAME_WON.
   *
   *  @return the model's state.
   */
  public Object getState()
  {
    return myState;
  }

  /** Returns whether or not the player has already guessed the given
   *  letter in this game.
   *
   *  @param letter the letter to check.
   *
   *  @return true if the given letter has been guessed, false otherwise.
   */
  public boolean hasGuessed(char letter)
  {
    return myGuessWordedLetters.indexOf(letter) >= 0;
  }

  /** Guesses whether the given letter is in the current secret word.
   *  If the letter is in the word, all instances of the letter in the secret
   *  word are revealed.  If the letter is not in the secret word, the number
   *  of guesses is decremented by one.  This method only has effect when the
   *  game is in progress and the given letter has
   *
   *  @param letter the character to guess.
   */
  public void guess(char letter)
  {
    // check error states:
    // - don't allow plays when game isn't in progress,
    // - make sure letter is valid (lowercase alphabet),
    // - make sure player hasn't already guessed this letter
    if (myState != IN_PROGRESS
    ||  hasGuessed(letter)
    ||  !Character.isLetter(letter)
    ||  !Character.isLowerCase(letter))
      return;

    // remember that the player has now guessed this letter
    myGuessWordedLetters += letter;

    // see if the letter is in the secret word or not
    if (mySecretWord.indexOf(letter) < 0)
    {
      // incorrect guess
      myNumGuesses--;
      if (hasLost())
        myState = GAME_LOST;
    }
    else
    {
      // correct guess; replace all occurrences of the letter
      revealAll(letter);

      if (hasWon())
        myState = GAME_WON;
    }

    // also added this
    setChanged();
    notifyObservers();
  }

  // returns whether or not the player has won the current game
  private boolean hasWon()
  {
    return myGuessWord.equals(mySecretWord);
  }

  // returns whether or not the player has lost the current game
  private boolean hasLost()
  {
    return myNumGuesses == 0;
  }

  // reveals all occurrences of the given letter in the secret word
  private void revealAll(char letter)
  {
    int wordLength = mySecretWord.length();
    char[] guessLetters = myGuessWord.toCharArray();
    for (int ii = 0;  ii < wordLength;  ii++)
      if (mySecretWord.charAt(ii) == letter)
        guessLetters[ii] = letter;
    myGuessWord = new String(guessLetters);
  }
}