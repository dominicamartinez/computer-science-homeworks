using System;
using System.Windows.Forms;
using System.Drawing;

namespace HangmanGUI
{
	/// <summary>
	/// Summary description for Controller.
	/// </summary>
	public class Controller : Form
	{
		private Model model;
		private View view;
     
		public Controller(Model model, View view)
		{
			this.model = model;
			this.view = view;

			model.Change += new ChangeHandler(view.Refresh);

			Button aButton = new Button();
			Button bButton = new Button();
			Button cButton = new Button();
			Button dButton = new Button();
			Button eButton = new Button();
			Button fButton = new Button();
			Button gButton = new Button();
			Button hButton = new Button();
			Button iButton = new Button();
			Button jButton = new Button();
			Button kButton = new Button();
			Button lButton = new Button();
			Button mButton = new Button();
			Button nButton = new Button();
			Button oButton = new Button();
			Button pButton = new Button();
			Button qButton = new Button();
			Button rButton = new Button();
			Button sButton = new Button();
			Button tButton = new Button();
			Button uButton = new Button();
			Button vButton = new Button();
			Button wButton = new Button();
			Button xButton = new Button();
			Button yButton = new Button();
			Button zButton = new Button();

			aButton.Text = "a";
			aButton.Location = new Point(40, 400);
			aButton.Size = new Size(25, 25);
			aButton.Click += new EventHandler(click);
			bButton.Text = "b";
			bButton.Location = new Point(65, 400);
			bButton.Size = new Size(25, 25);
			bButton.Click += new EventHandler(click);
			cButton.Text = "c";
			cButton.Location = new Point(90, 400);
			cButton.Size = new Size(25, 25);
			cButton.Click += new EventHandler(click);
			dButton.Text = "d";
			dButton.Location = new Point(115, 400);
			dButton.Size = new Size(25, 25);
			dButton.Click += new EventHandler(click);
			eButton.Text = "e";
			eButton.Location = new Point(140, 400);
			eButton.Size = new Size(25, 25);
			eButton.Click += new EventHandler(click);
			fButton.Text = "f";
			fButton.Location = new Point(165, 400);
			fButton.Size = new Size(25, 25);
			fButton.Click += new EventHandler(click);
			gButton.Text = "g";
			gButton.Location = new Point(190, 400);
			gButton.Size = new Size(25, 25);
			gButton.Click += new EventHandler(click);
			hButton.Text = "h";
			hButton.Location = new Point(215, 400);
			hButton.Size = new Size(25, 25);
			hButton.Click += new EventHandler(click);
			iButton.Text = "i";
			iButton.Location = new Point(240, 400);
			iButton.Size = new Size(25, 25);
			iButton.Click += new EventHandler(click);
			jButton.Text = "j";
			jButton.Location = new Point(265, 400);
			jButton.Size = new Size(25, 25);
			jButton.Click += new EventHandler(click);
			kButton.Text = "k";
			kButton.Location = new Point(290, 400);
			kButton.Size = new Size(25, 25);
			kButton.Click += new EventHandler(click);
			lButton.Text = "l";
			lButton.Location = new Point(315, 400);
			lButton.Size = new Size(25, 25);
			lButton.Click += new EventHandler(click);
			mButton.Text = "m";
			mButton.Location = new Point(340, 400);
			mButton.Size = new Size(25, 25);
			mButton.Click += new EventHandler(click);
			nButton.Text = "n";
			nButton.Location = new Point(365, 400);
			nButton.Size = new Size(25, 25);
			nButton.Click += new EventHandler(click);
			oButton.Text = "o";
			oButton.Location = new Point(390, 400);
			oButton.Size = new Size(25, 25);
			oButton.Click += new EventHandler(click);
			pButton.Text = "p";
			pButton.Location = new Point(415, 400);
			pButton.Size = new Size(25, 25);
			pButton.Click += new EventHandler(click);
			qButton.Text = "q";
			qButton.Location = new Point(440, 400);
			qButton.Size = new Size(25, 25);
			qButton.Click += new EventHandler(click);
			rButton.Text = "r";
			rButton.Location = new Point(465, 400);
			rButton.Size = new Size(25, 25);
			rButton.Click += new EventHandler(click);
			sButton.Text = "s";
			sButton.Location = new Point(490, 400);
			sButton.Size = new Size(25, 25);
			sButton.Click += new EventHandler(click);
			tButton.Text = "t";
			tButton.Location = new Point(515, 400);
			tButton.Size = new Size(25, 25);
			tButton.Click += new EventHandler(click);
			uButton.Text = "u";
			uButton.Location = new Point(240, 425);
			uButton.Size = new Size(25, 25);
			uButton.Click += new EventHandler(click);
			vButton.Text = "v";
			vButton.Location = new Point(265, 425);
			vButton.Size = new Size(25, 25);
			vButton.Click += new EventHandler(click);
			wButton.Text = "w";
			wButton.Location = new Point(290, 425);
			wButton.Size = new Size(25, 25);
			wButton.Click += new EventHandler(click);
			xButton.Text = "x";
			xButton.Location = new Point(315, 425);
			xButton.Size = new Size(25, 25);
			xButton.Click += new EventHandler(click);
			yButton.Text = "y";
			yButton.Location = new Point(340, 425);
			yButton.Size = new Size(25, 25);
			yButton.Click += new EventHandler(click);
			zButton.Text = "z";
			zButton.Location = new Point(365, 425);
			zButton.Size = new Size(25, 25);
			zButton.Click += new EventHandler(click);
		
			Controls.Add(aButton);
			Controls.Add(bButton);
			Controls.Add(cButton);
			Controls.Add(dButton);
			Controls.Add(eButton);
			Controls.Add(fButton);
			Controls.Add(gButton);
			Controls.Add(hButton);
			Controls.Add(iButton);
			Controls.Add(jButton);
			Controls.Add(kButton);
			Controls.Add(lButton);
			Controls.Add(mButton);
			Controls.Add(nButton);
			Controls.Add(oButton);
			Controls.Add(pButton);
			Controls.Add(qButton);
			Controls.Add(rButton);
			Controls.Add(sButton);
			Controls.Add(tButton);
			Controls.Add(uButton);
			Controls.Add(vButton);
			Controls.Add(wButton);
			Controls.Add(xButton);
			Controls.Add(yButton);
			Controls.Add(zButton);
			Controls.Add(view);
		}

		private void click(object source, EventArgs e) 
		{
			Button b = (Button)source;
			if (b.BackColor != Color.Aqua) 
			{
				b.BackColor = Color.Aqua;
				model.update(b.Text);
			}
		}

		private void viewRefresh() 
		{
			view.Refresh();
		}
	}
}
