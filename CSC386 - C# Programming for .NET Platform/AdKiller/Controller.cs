using System;
using System.Windows.Forms;
using System.Drawing;
using System.ComponentModel;
using System.Runtime.InteropServices;

namespace AdKiller
{
	public class Controller : Form
	{
		private NotifyIcon ni;
		private IContainer components;
		private ContextMenu cm;
		private MenuItem mi;

		public Controller()
		{
			cm = new ContextMenu();
			components = new Container();
			mi = new MenuItem();

			cm.MenuItems.AddRange(new MenuItem[] {mi});

			mi.Index = 0;
			mi.Text = "Exit";
			mi.Click += new EventHandler(mi_click);

			ni = new NotifyIcon(components);
			ni.ContextMenu = cm;
			ni.Icon = new Icon("App.ico");
			ni.Click += new EventHandler(iconify);
			ni.DoubleClick += new EventHandler(double_click);

			this.Closing += new CancelEventHandler(frm_hide);
			
			Label cW = new Label();
			Label kW = new Label();
			Label session = new Label();
			Label sCount = new Label();
			ListBox current = new ListBox();
			ListBox kill = new ListBox();
			Button addToList = new Button();
			Button exit = new Button();
			Button remove = new Button();

			addToList.Text = "Add to List";
			addToList.Location = new Point(400, 20);
			exit.Text = "Exit";
			exit.Location = new Point(0, 440);
			remove.Text = "Remove";
			remove.Location = new Point(400, 240);
            
			cW.Text = "Current Windows";
			kW.Text = "Kill List";
			session.Text = "Pop-Ups killed this session: ";
			sCount.Text = "Test";
			sCount.Location = new Point(260, 440);
			sCount.Size = new Size(40, 20);
			session.Location = new Point(100, 440);
			session.Size = new Size(160, 20);
			cW.Size = new Size(100, 20);
			cW.Location = new Point(0, 0);
			kW.Size = new Size(100, 20);
			kW.Location = new Point(0, 220);

			kill.Size = new Size(400, 200);
			current.Size = new Size(400, 200);
			current.Location = new Point(0, 20);
			kill.Location = new Point(0, 240);

			exit.Click += new EventHandler(click);
			addToList.Click += new EventHandler(click);
			remove.Click += new EventHandler(click);

			Controls.Add(session);
			Controls.Add(sCount);
			Controls.Add(addToList);
			Controls.Add(exit);
			Controls.Add(remove);
			Controls.Add(cW);
			Controls.Add(kW);
			Controls.Add(current);
			Controls.Add(kill);
		}

		public void click(object source, EventArgs e) 
		{
			Button b = (Button)source;
			if (b.Text == "Exit") 
			{
				Application.Exit();
			}
			else if (b.Text == "Add to List") 
			{
				MessageBox.Show("Add to LIST");
			}
			else if (b.Text == "Remove") 
			{
				MessageBox.Show("Remove");
			}
		}

		private void double_click(object sender, EventArgs e) 
		{
			if (this.WindowState == FormWindowState.Minimized)
				this.WindowState = FormWindowState.Normal;

			this.Activate();
		}

		private void mi_click(object sender, EventArgs e) 
		{
			this.Close();
			Application.Exit();
		}

		private void frm_hide(object sender, CancelEventArgs cea)
		{
			cea.Cancel = true;

			this.Hide();
			ni.Visible = true;
		}

		private void iconify(object sender, EventArgs e) 
		{
			Show();
		}
	}
}
