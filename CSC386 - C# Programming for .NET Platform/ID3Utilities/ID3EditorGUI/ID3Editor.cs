using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Data;
using ID3Utilities;
using System.Text;
using System.IO;

namespace ID3EditorGUI
{
	/// <summary>
	/// Summary description for Form1.
	/// </summary>
	public class ID3Editor : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.ComboBox comboBox1;
		private System.Windows.Forms.MainMenu mainMenu1;
		private System.Windows.Forms.MenuItem menuItem1;
		private System.Windows.Forms.MenuItem mOpen;
		private System.Windows.Forms.MenuItem mSave;
		private System.Windows.Forms.MenuItem mExit;
		private System.Windows.Forms.TextBox SongTitle;
		private System.Windows.Forms.TextBox ArtistName;
		private System.Windows.Forms.TextBox Comment;
		private System.Windows.Forms.TextBox Track;
		private System.Windows.Forms.TextBox Year;
		private System.Windows.Forms.TextBox AlbumName;
		private ID3Tag id3;
		private System.Windows.Forms.Label status;
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;

		public ID3Editor()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
			Array a = Enum.GetValues(typeof(ID3Utilities.GenreType));
			comboBox1.DataSource = a;
		}

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if (components != null) 
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}

		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.label5 = new System.Windows.Forms.Label();
			this.label6 = new System.Windows.Forms.Label();
			this.label7 = new System.Windows.Forms.Label();
			this.comboBox1 = new System.Windows.Forms.ComboBox();
			this.mainMenu1 = new System.Windows.Forms.MainMenu();
			this.menuItem1 = new System.Windows.Forms.MenuItem();
			this.mOpen = new System.Windows.Forms.MenuItem();
			this.mSave = new System.Windows.Forms.MenuItem();
			this.mExit = new System.Windows.Forms.MenuItem();
			this.SongTitle = new System.Windows.Forms.TextBox();
			this.ArtistName = new System.Windows.Forms.TextBox();
			this.Comment = new System.Windows.Forms.TextBox();
			this.Track = new System.Windows.Forms.TextBox();
			this.Year = new System.Windows.Forms.TextBox();
			this.AlbumName = new System.Windows.Forms.TextBox();
			this.status = new System.Windows.Forms.Label();
			this.SuspendLayout();
			// 
			// label1
			// 
			this.label1.Location = new System.Drawing.Point(8, 16);
			this.label1.Name = "label1";
			this.label1.TabIndex = 0;
			this.label1.Text = "Song";
			this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label2
			// 
			this.label2.Location = new System.Drawing.Point(8, 48);
			this.label2.Name = "label2";
			this.label2.TabIndex = 1;
			this.label2.Text = "Artist";
			this.label2.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label3
			// 
			this.label3.Location = new System.Drawing.Point(8, 112);
			this.label3.Name = "label3";
			this.label3.TabIndex = 2;
			this.label3.Text = "Year";
			this.label3.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label4
			// 
			this.label4.Location = new System.Drawing.Point(8, 80);
			this.label4.Name = "label4";
			this.label4.TabIndex = 3;
			this.label4.Text = "Album";
			this.label4.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label5
			// 
			this.label5.Location = new System.Drawing.Point(8, 152);
			this.label5.Name = "label5";
			this.label5.TabIndex = 4;
			this.label5.Text = "Comment";
			this.label5.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label6
			// 
			this.label6.Location = new System.Drawing.Point(8, 192);
			this.label6.Name = "label6";
			this.label6.TabIndex = 5;
			this.label6.Text = "Genre";
			this.label6.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// label7
			// 
			this.label7.Location = new System.Drawing.Point(184, 112);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(48, 23);
			this.label7.TabIndex = 6;
			this.label7.Text = "Track #";
			this.label7.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
			// 
			// comboBox1
			// 
			this.comboBox1.Location = new System.Drawing.Point(120, 192);
			this.comboBox1.MaxLength = 30;
			this.comboBox1.Name = "comboBox1";
			this.comboBox1.Size = new System.Drawing.Size(121, 21);
			this.comboBox1.TabIndex = 7;
			// 
			// mainMenu1
			// 
			this.mainMenu1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.menuItem1});
			// 
			// menuItem1
			// 
			this.menuItem1.Index = 0;
			this.menuItem1.MenuItems.AddRange(new System.Windows.Forms.MenuItem[] {
																					  this.mOpen,
																					  this.mSave,
																					  this.mExit});
			this.menuItem1.Text = "File";
			this.menuItem1.Click += new System.EventHandler(this.menuItem1_Click);
			// 
			// mOpen
			// 
			this.mOpen.Index = 0;
			this.mOpen.Text = "Open";
			this.mOpen.Click += new System.EventHandler(this.mOpen_Click);
			// 
			// mSave
			// 
			this.mSave.Index = 1;
			this.mSave.Text = "Save";
			this.mSave.Click += new System.EventHandler(this.mSave_Click);
			// 
			// mExit
			// 
			this.mExit.Index = 2;
			this.mExit.Text = "Exit";
			this.mExit.Click += new System.EventHandler(this.mExit_Click);
			// 
			// SongTitle
			// 
			this.SongTitle.Location = new System.Drawing.Point(120, 16);
			this.SongTitle.MaxLength = 30;
			this.SongTitle.Name = "SongTitle";
			this.SongTitle.TabIndex = 8;
			this.SongTitle.Text = "";
			// 
			// ArtistName
			// 
			this.ArtistName.Location = new System.Drawing.Point(120, 48);
			this.ArtistName.MaxLength = 30;
			this.ArtistName.Name = "ArtistName";
			this.ArtistName.TabIndex = 9;
			this.ArtistName.Text = "";
			// 
			// Comment
			// 
			this.Comment.Location = new System.Drawing.Point(120, 152);
			this.Comment.MaxLength = 30;
			this.Comment.Name = "Comment";
			this.Comment.TabIndex = 10;
			this.Comment.Text = "";
			// 
			// Track
			// 
			this.Track.Location = new System.Drawing.Point(248, 112);
			this.Track.MaxLength = 2;
			this.Track.Name = "Track";
			this.Track.Size = new System.Drawing.Size(32, 20);
			this.Track.TabIndex = 11;
			this.Track.Text = "";
			// 
			// Year
			// 
			this.Year.Location = new System.Drawing.Point(120, 112);
			this.Year.MaxLength = 4;
			this.Year.Name = "Year";
			this.Year.Size = new System.Drawing.Size(40, 20);
			this.Year.TabIndex = 12;
			this.Year.Text = "";
			// 
			// AlbumName
			// 
			this.AlbumName.Location = new System.Drawing.Point(120, 80);
			this.AlbumName.MaxLength = 30;
			this.AlbumName.Name = "AlbumName";
			this.AlbumName.TabIndex = 13;
			this.AlbumName.Text = "";
			// 
			// status
			// 
			this.status.Location = new System.Drawing.Point(8, 240);
			this.status.Name = "status";
			this.status.Size = new System.Drawing.Size(184, 23);
			this.status.TabIndex = 14;
			// 
			// ID3Editor
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(292, 273);
			this.Controls.AddRange(new System.Windows.Forms.Control[] {
																		  this.status,
																		  this.AlbumName,
																		  this.Year,
																		  this.Track,
																		  this.Comment,
																		  this.ArtistName,
																		  this.SongTitle,
																		  this.comboBox1,
																		  this.label7,
																		  this.label6,
																		  this.label5,
																		  this.label4,
																		  this.label3,
																		  this.label2,
																		  this.label1});
			this.Menu = this.mainMenu1;
			this.Name = "ID3Editor";
			this.Text = "ID3 Editor v1.1";
			this.Load += new System.EventHandler(this.Form1_Load);
			this.ResumeLayout(false);

		}
		#endregion

		/// <summary>
		/// The main entry point for the application.
		/// </summary>
		[STAThread]
		static void Main() 
		{
			Application.Run(new ID3Editor());
		}

		private void Form1_Load(object sender, System.EventArgs e)
		{
		
		}

		private void mOpen_Click(object sender, System.EventArgs e)
		{
			OpenFileDialog ofd = new OpenFileDialog();
			ofd.Filter = "MP3 Files|*.mp3";
            ofd.ShowDialog();
			try 
			{
				id3 = new ID3Tag(ofd.FileName);
			}
			catch 
			{ 
			}

			try 
			{
				id3.Load();
				status.Text = "ID3 Loaded..";
				SongTitle.Text = id3.Song;
				AlbumName.Text = id3.Album;
				ArtistName.Text = id3.Artist;
				Comment.Text = id3.Comment;
				Year.Text = id3.Year;
				byte[] b = new byte[1];
				b[0] = id3.Track;
				Track.Text = Encoding.ASCII.GetString(b);
				byte blah = (byte)id3.Genre;
				GenreType g = (GenreType)blah;
				comboBox1.Text = g.ToString();
			} 
			catch
			{
				status.Text = "Could not load ID3 Tag properly...";
				SongTitle.Text = "";
				AlbumName.Text = "";
				ArtistName.Text = "";
				Comment.Text = "";
				Year.Text = "";
				Track.Text = "";
			}
		}

		private void mSave_Click(object sender, System.EventArgs e)
		{
			id3.Song = SongTitle.Text;
			id3.Album = AlbumName.Text;
			id3.Artist= ArtistName.Text;
			id3.Comment = Comment.Text;
			id3.Year = Year.Text;
			byte[] b = Encoding.ASCII.GetBytes(Track.Text);
			id3.Track = b[0];
			id3.Genre = (GenreType)comboBox1.SelectedItem;
			id3.Save();
			status.Text = "ID3 Saved...";
		}

		private void mExit_Click(object sender, System.EventArgs e)
		{
			Application.Exit();
		}

		private void menuItem1_Click(object sender, System.EventArgs e)
		{
		
		}
	}
}
