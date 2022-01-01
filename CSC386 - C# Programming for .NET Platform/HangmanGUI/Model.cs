using System;
/* This code is used from my 335 days a little altered due to the fact
 * that this is C# and not java
 * */

namespace HangmanGUI
{

	public delegate void ChangeHandler();

	public class Model
	{
		public event ChangeHandler Change;

		public string IN_PROGRESS = "In progress",
		GAME_LOST = "Game over (lost)",
		GAME_WON  = "Game over (won)";

		// number of guesses before game is over
		private int MAX_GUESSES = 6;

		// letter to fill in for unknown unguessed letters
		private char UNKNOWN_LETTER = '?';

		// list of (hard-coded GRE vocab) words to use
		private string[] ourWords = 
	{
		"accretion", "boisterous", "dullard", "feigned", "haughty", "insipid",
		"noisome", "obdurate", "parsimonious", "sycophant"
	};


		// instance variables
		private string mySecretWord;
		private string myGuessWord;
		private string myGuessWordedLetters;
		private string myState;
		private int myNumGuesses;
		private int wordLength;

		public Model() 
		{
			Random r = new Random();
			mySecretWord = ourWords[r.Next(0, 9)];
			wordLength = mySecretWord.Length;
			myGuessWord = "";
			for (int ii = 0;  ii < wordLength;  ii++)
				myGuessWord += UNKNOWN_LETTER;
			myGuessWordedLetters = "";
			myNumGuesses = MAX_GUESSES;
			myState = IN_PROGRESS;
			OnChange();
		}

		// properties
		public int WordLength
		{
			get 
			{
				return wordLength;
			}
		}

		public string SecretWord
		{
			get 
			{
				return mySecretWord;
			}
		}

		public string GuessWord
		{
			get 
			{
				return myGuessWord;
			}
		}

		public int NumOfGuessesLeft
		{
			get 
			{
				return myNumGuesses;
			}
		}

		public string State {
			get
			{
				return myState;
			}
		}

		// methods
		public bool hasGuessed(char letter)
		{
			return myGuessWordedLetters.IndexOf(letter) >= 0;
		}

		public void update(string s)
		{
			char letter = char.Parse(s);
			// check error states:
			// - don't allow plays when game isn't in progress,
			// - make sure letter is valid (lowercase alphabet),
			// - make sure player hasn't already guessed this letter
			if (myState != IN_PROGRESS
				||  hasGuessed(letter)
				||  !char.IsLetter(letter)
				||  !char.IsLower(letter))
				return;

			// remember that the player has now guessed this letter
			myGuessWordedLetters += letter;

			// see if the letter is in the secret word or not
			if (mySecretWord.IndexOf(letter) < 0)
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
			OnChange();
		}

		// returns whether or not the player has won the current game
		private bool hasWon()
		{
			return myGuessWord.Equals(mySecretWord);
		}

		// returns whether or not the player has lost the current game
		private bool hasLost()
		{
			return myNumGuesses == 0;
		}
		
		// reveals all occurrences of the given letter in the secret word
		private void revealAll(char letter)
		{
			int wordLength = mySecretWord.Length;
			char[] guessLetters = myGuessWord.ToCharArray();
			char[] secretword = mySecretWord.ToCharArray();
			for (int ii = 0;  ii < wordLength;  ii++)
				if (secretword[ii] == letter)
					guessLetters[ii] = letter;
			myGuessWord = new String(guessLetters);
		}

		protected virtual void OnChange()
		{
			if(Change != null)
				Change();
		}
	}
}
