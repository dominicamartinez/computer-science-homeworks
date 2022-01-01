using System;
using System.Windows.Forms;

namespace HangmanGUI
{
	/// <summary>
	/// Summary description for Hangman.
	/// </summary>
	public class Hangman
	{
		public static void Main() 
		{
			Model m = new Model();
			View v = new View(m);
			Controller c = new Controller(m, v);
			c.Width = 600;
			c.Height = 500;
			c.Text = "Hangman";
			Application.Run(c);
		}
	}
}
