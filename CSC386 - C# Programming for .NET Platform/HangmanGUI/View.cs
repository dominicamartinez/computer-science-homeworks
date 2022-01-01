using System;
using System.Windows.Forms;
using System.Drawing;
using System.IO;

namespace HangmanGUI
{

	public class View : Panel
	{
		private Model model;
		
		public View(Model m)
		{
			model = m;
			this.Size = new Size(500, 400);
			SetStyle(ControlStyles.DoubleBuffer|ControlStyles.AllPaintingInWmPaint|ControlStyles.UserPaint, true);
		}

		protected override void OnPaint(PaintEventArgs e)
		{
			Graphics g = e.Graphics;
			if (model.State == "Game over (won)")
			{
				MessageBox.Show(this, "YOU WON THE GAME");				
			}
			else if (model.State == "Game over (lost)") 
			{
				MessageBox.Show(this, "YOU LOST", "LOST GAME", MessageBoxButtons.OK);
			}
			if (model.NumOfGuessesLeft == 6) 
			{
				string d = Directory.GetCurrentDirectory();
				d += "\\hangman6.gif";
				Bitmap b = new Bitmap(d);
				g.DrawImage(b, 0, 100);
				SolidBrush sb = new SolidBrush(Color.Black);
				Font f = new Font("Courier", 20, FontStyle.Bold);
				g.DrawString(model.GuessWord, f, sb, 200, 200);
			}
			else if (model.NumOfGuessesLeft == 5) 
			{
				string d = Directory.GetCurrentDirectory();
				d += "\\hangman5.gif";
				Bitmap b = new Bitmap(d);
				g.DrawImage(b, 0, 100);
				SolidBrush sb = new SolidBrush(Color.Black);
				Font f = new Font("Courier", 20, FontStyle.Bold);
				g.DrawString(model.GuessWord, f, sb, 200, 200);

			}
			else if (model.NumOfGuessesLeft == 4) 
			{
				string d = Directory.GetCurrentDirectory();
				d += "\\hangman4.gif";
				Bitmap b = new Bitmap(d);
				g.DrawImage(b, 0, 100);
				SolidBrush sb = new SolidBrush(Color.Black);
				Font f = new Font("Courier", 20, FontStyle.Bold);
				g.DrawString(model.GuessWord, f, sb, 200, 200);

			}
			else if (model.NumOfGuessesLeft == 3) 
			{
				string d = Directory.GetCurrentDirectory();
				d += "\\hangman3.gif";
				Bitmap b = new Bitmap(d);
				g.DrawImage(b, 0, 100);
				SolidBrush sb = new SolidBrush(Color.Black);
				Font f = new Font("Courier", 20, FontStyle.Bold);
				g.DrawString(model.GuessWord, f, sb, 200, 200);

			}
			else if (model.NumOfGuessesLeft == 2) 
			{
				string d = Directory.GetCurrentDirectory();
				d += "\\hangman2.gif";
				Bitmap b = new Bitmap(d);
				g.DrawImage(b, 0, 100);
				SolidBrush sb = new SolidBrush(Color.Black);
				Font f = new Font("Courier", 20, FontStyle.Bold);
				g.DrawString(model.GuessWord, f, sb, 200, 200);

			}
			else if (model.NumOfGuessesLeft == 1) 
			{
				string d = Directory.GetCurrentDirectory();
				d += "\\hangman1.gif";
				Bitmap b = new Bitmap(d);
				g.DrawImage(b, 0, 100);
				SolidBrush sb = new SolidBrush(Color.Black);
				Font f = new Font("Courier", 20, FontStyle.Bold);
				g.DrawString(model.GuessWord, f, sb, 200, 200);

			}
			else 
			{
				string d = Directory.GetCurrentDirectory();
				d += "\\hangman0.gif";
				Bitmap b = new Bitmap(d);
				g.DrawImage(b, 0, 100);
				SolidBrush sb = new SolidBrush(Color.Black);
				Font f = new Font("Courier", 20, FontStyle.Bold);
				g.DrawString(model.SecretWord, f, sb, 200, 200);
			}
		}
	}
}
