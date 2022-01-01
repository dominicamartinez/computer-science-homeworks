using System;
using System.Windows.Forms;

namespace AdKiller
{

	public class AdKiller
	{
		public static void Main() 
		{
			Controller c = new Controller();
			c.Width = 500;
			c.Height = 500;
			c.Text = "AdKiller";
			Application.Run(c);
		}
	}
}
