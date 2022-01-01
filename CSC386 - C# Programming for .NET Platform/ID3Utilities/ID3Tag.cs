using System;
using System.IO;
using System.Text;

namespace ID3Utilities
{
	public enum GenreType
	{
		Unknown = 255,
		// since Blues = 0, ClassicRock = 1, Country = 2, and so on
		Blues = 0, ClassicRock, Country, Dance, Disco, Funk, Grunge,
		HipHop, Jazz, Metal, NewAge, Oldies, Other, Pop, RandB, Rap,
		Reggae, Rock, Techno, Industrial, Alternative, Ska, DeathMetal,
		Pranks, Soundtrack, EuroTechno, Ambient, TripHop, Vocal,
		JazzFunk, Fusion, Trance, Classical, Instrumental, Acid, House,
		Game, SoundClip, Gospel, Noise, AlternRock, Bass, Soul, Punk,
		Space, Meditative, InstrumentalPop, InstrumentalRock, Ethnic,
		Gothic, Darkwave, TechnoIndustrial, Electronic, PopFolk, 
		Eurodance, Dream, SouthernRock, Comedy, Cult, Gangsta, Top40,
		ChristianRap, PopFunk, Jungle, NativeAmerican, Cabaret, NewWave,
		Psychadelic, Rave, Showtunes, Trailer, LoFi, Tribal, AcidPunk,
		AcidJazz, Polka, Retro, Musical, RockNRoll, HardRock, Folk,
		FolkRock, NationalFolk, Swing, Bebob, Latin, Revival, Celtic,
		Bluegrass, Avantgarde, GothicRock, ProgressiveRock, 
		PsychedelicRock, SymphonicRock, SlowRock, BigBand, Chorus, 
		EasyListening, Acoustic, Humour, Speech, Chanson, Opera, 
		ChamberMusic, Sonata, Symphony, BootyBass, Primus, PornGroove,
		Satire, SlowJam, Club, Tango, Samba, Folklore 
	}

	/// <summary>
	/// Summary description for ID3Tag.
	/// 
	/// Written by Dominic Martinez
	/// </summary>
	public class ID3Tag
	{
		#region Instance Variables

		private string path, artist, song, album, year, comment;
		private GenreType genre;
		private byte track;
		private bool exists = false;

		#endregion
		#region Constructor

		public ID3Tag(string path)
		{
			this.path = path;
		}
		#endregion

		#region Properties
		
		public string Path 
		{
			get
			{
				return path;
			}
		}

		public string Song 
		{ 
			get
			{	
				return song;
			}
			set
			{
				byte[] bytes = Encoding.ASCII.GetBytes(value); 
				if (bytes.Length > 30) 
				{ 
					Console.WriteLine("Invalid length for Song Title");
				}
				else 
				{
					song = value;
				}
			}
		}

		public string Artist 
		{ 
			get
			{
				return artist;
			} 
			set
			{
				byte[] bytes = Encoding.ASCII.GetBytes(value); 
				if (bytes.Length > 30) 
				{ 
					Console.WriteLine("Invalid length for Artist Name");
				}
				else 
				{
					artist = value;
				}
			}
		}

		public string Album 
		{ 
			get
			{
				return album;
			}
			set
			{
				byte[] bytes = Encoding.ASCII.GetBytes(value); 
				if (bytes.Length > 30) 
				{ 
					Console.WriteLine("Invalid length for Song Title");
				}
				else 
				{
					album = value;
				}
			}
		}

		public string Year 
		{ 
			get
			{
				return year;	
			}
			set
			{
				byte[] bytes = Encoding.ASCII.GetBytes(value); 
				if (bytes.Length > 4) 
				{ 
					Console.WriteLine("Invalid length for Song Title");
				}
				else 
				{
					year = value;
				}
			} 
		}

		public string Comment
		{ 
			get
			{
				return comment;			
			}
			set
			{
				byte[] bytes = Encoding.ASCII.GetBytes(value); 
				if (bytes.Length > 30) 
				{ 
					Console.WriteLine("Invalid length for Song Title");
				}
				else 
				{
					comment = value;
				}
			} 
		}

		public GenreType Genre
		{ 
			get
			{
				return genre;
			}
			set
			{
				genre = (GenreType)value;
			}
		}

		public byte Track 
		{ 
			get
			{
				return track;
			}
			set
			{
				if (value > 255) 
				{
					Console.WriteLine("Too big for a track number");
				}
				else 
				{
					track = value;
				}
			} 
		}
		#endregion

		#region Method
		public void Reset()
		{
			song = "";
			artist = "";
			album = "";
			year = "";
			comment = "";
			genre = GenreType.Unknown;
			track = 0;
		}

		public void Load()
		{
			FileStream fs;
			try 
			{
				fs = new FileStream(path, FileMode.Open, FileAccess.Read);
			}
			catch 
			{
				throw new IOException("Cannot read file");
			}
			// read in TAG
			byte[] buffer = new byte[3];
			fs.Seek((fs.Length-(long)128), 0);
			fs.Read(buffer, 0, buffer.Length);
			string tag = Encoding.ASCII.GetString(buffer);
			if (tag == "TAG") 
			{
				exists = true;
				// read in song Title
				buffer = new byte[30];
				fs.Read(buffer, 0, buffer.Length);
				song = Encoding.ASCII.GetString(buffer);
			
				// read in the artist
				buffer = new byte[30];
				fs.Read(buffer, 0, buffer.Length);
				artist = Encoding.ASCII.GetString(buffer);
			
				// read in the album
				buffer = new byte[30];
				fs.Read(buffer, 0, buffer.Length);
				album = Encoding.ASCII.GetString(buffer);
			
				// read in the year
				buffer = new byte[4];
				fs.Read(buffer, 0, buffer.Length);
				year = Encoding.ASCII.GetString(buffer);
			
				// read in the comment
				buffer = new byte[30];
				fs.Read(buffer, 0, buffer.Length);
				comment = Encoding.ASCII.GetString(buffer);
				if (buffer[28] == 0) 
				{
					track = buffer[29];
				}				
									
				// read in the genre
				buffer = new byte[1];
				fs.Read(buffer, 0, buffer.Length);
				byte b = buffer[0];
				genre = (GenreType)b;
				fs.Close();
			}
			else 
			{
				Console.WriteLine("TAG Marker does not exist");
				exists = false;
				Reset();
				fs.Close();
			}
		}

		public void Save()
		{
			FileStream fs;
			if (exists == true) 
			{
				try 
				{
					fs = new FileStream(path, FileMode.Open, FileAccess.Write);
				}
				catch 
				{
					throw new IOException("Cannot Write to file: Check Permissions");
				}
				// rewrite TAG marker
				fs.Seek((fs.Length-(long)128), 0);
				byte[] buffer = Encoding.ASCII.GetBytes("TAG");
				fs.Write(buffer, 0, buffer.Length);

				// write out song Title
				buffer = new byte[30];
				byte[] temp = Encoding.ASCII.GetBytes(song);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);
			
				// write out the artist
				buffer = new byte[30];
				temp = Encoding.ASCII.GetBytes(artist);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);
			
				// write out the album
				buffer = new byte[30];
				temp = Encoding.ASCII.GetBytes(album);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);		
	
				// write out the year
				buffer = new byte[4];
				temp = Encoding.ASCII.GetBytes(year);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);		
	
				// write out the comment
				buffer = new byte[30];
				temp = Encoding.ASCII.GetBytes(comment);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				if (buffer[28] == 0) 
				{
					buffer[29] = track;
				}
				fs.Write(buffer, 0, buffer.Length);
				
				// write out genre
				buffer = new byte[1];
				buffer[0] = (byte)genre;
				fs.Write(buffer, 0, buffer.Length);
									
				fs.Close();
			}
			else 
			{
				try 
				{
					fs = new FileStream(path, FileMode.Append);
				}
				catch 
				{
					throw new IOException("Cannot Append to file: Check Permissions");
				}
				// write out the TAG marker
				byte[] buffer = Encoding.ASCII.GetBytes("TAG");
				fs.Write(buffer, 0, buffer.Length);

				// write out song Title
				buffer = new byte[30];
				byte[] temp = Encoding.ASCII.GetBytes(song);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);			

				// write out the artist
				buffer = new byte[30];
				temp = Encoding.ASCII.GetBytes(artist);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);
			
				// write out the album
				buffer = new byte[30];
				temp = Encoding.ASCII.GetBytes(album);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);
			
				// write out the year
				buffer = new byte[4];
				temp = Encoding.ASCII.GetBytes(year);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				fs.Write(buffer, 0, buffer.Length);
			
				// write out the comment
				buffer = new byte[30];
				temp = Encoding.ASCII.GetBytes(comment);
				for(int i = 0; i < buffer.Length; i++) 
				{
					if (i >= temp.Length) 
					{
						buffer[i] = 0;
					}
					else 
					{
						buffer[i] = temp[i];
					}
				}
				if (buffer[28] == 0) 
				{
					buffer[29] = track;
				}
				fs.Write(buffer, 0, buffer.Length);
									
				// write out genre
				buffer = new byte[1];
				buffer[0] = (byte)genre;
				fs.Write(buffer, 0, buffer.Length);
									
				fs.Close();
			}
		}
		#endregion
		
		/*
		public static void Main() 
		{
			ID3Tag id = new ID3Tag(@"H:\cheattheme.mp3");
			id.Load();
			id.Song = "PERLY WHITE";
			id.Save();
			id.Load();
			Console.WriteLine(id.Song);
			Console.WriteLine(id.Track);
		}*/
	}
}

