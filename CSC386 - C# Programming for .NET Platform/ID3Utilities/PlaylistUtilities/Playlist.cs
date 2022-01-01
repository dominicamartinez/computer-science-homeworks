using System;
using ID3Utilities;
using MP3Utilities;
using System.IO;
using System.Collections;

/* Dominic Martinez */

namespace PlaylistCreator
{
	[Serializable]
	public class Playlist : IEnumerable
	{
		#region Data Fields

		// data fields
		private string path;
		private int startyear;
		private int endyear;
		private ArrayList playlist;
		private ArrayList genres;
		private ArrayList artists;
		private ArrayList albums;

		#endregion

		#region IEnumerable Implementation
		
		public IEnumerator GetEnumerator()
		{
			return playlist.GetEnumerator();
		}
		
		#endregion

		#region Constructor

		public Playlist(string path)
		{
			this.path = path;
			playlist = new ArrayList();
			startyear = int.MinValue;
			endyear = int.MaxValue;
			genres = new ArrayList();
			artists = new ArrayList();
			albums = new ArrayList();
		}

		#endregion

		#region Methods

		public void Add(ID3Tag song)
		{
				playlist.Add(song);
		}

		public bool Contains(ID3Tag song)
		{
			return playlist.Contains(song);
		}

		public int IndexOf(ID3Tag song)
		{
			return playlist.IndexOf(song);
		}
		
		public void Remove(ID3Tag song)
		{
			playlist.Remove(song);
		}
		
		public void RemoveAt(int index)
		{
			playlist.RemoveAt(index);
		}

		public void Reset()
		{
			playlist = new ArrayList();
			startyear = int.MinValue;
			endyear = int.MaxValue;
		}

		public void Search()
		{
			Search(-1);
		}

		public void Search(int length)
		{
			dfs(path);
		}
		
		public void dfs(string directory) 
		{
			string[] directories = Directory.GetDirectories(directory);
			foreach(string d in directories) 
			{
				dfs(d);
			}

			string[] files = Directory.GetFiles(directory);
			foreach(string f in files) 
			{
				try 
				{
					ID3Tag t = new ID3Tag(f);
					try 
					{
						t.Load();
					}
					catch {}
					if (genres.Count == 0 && artists.Count == 0 && albums.Count == 0 && startyear == int.MinValue && endyear == int.MaxValue) 
					{
						playlist.Add(t);
					}
					else if (genres.Count == 0 && artists.Count == 0 && albums.Count == 0 && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{ 
						playlist.Add(t); 
					}
					else if (genres.Contains(t.Genre) && artists.Count == 0 && albums.Count == 0 && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{
						playlist.Add(t);
					}
					else if (genres.Count == 0 && artists.Contains(t.Artist) && albums.Count == 0 && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{
						playlist.Add(t);
					}
					else if (genres.Count == 0 && artists.Count == 0 && albums.Contains(t.Album) && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{
						playlist.Add(t);
					}
					else if (genres.Contains(t.Genre) && artists.Contains(t.Artist) && albums.Count == 0 && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{ 
						playlist.Add(t); 
					}
					else if (albums.Contains(t.Album) && artists.Contains(t.Artist) && genres.Count == 0 && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{
						playlist.Add(t);
					}
					else if (genres.Contains(t.Genre) && albums.Contains(t.Album) && artists.Count == 0 && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{
						playlist.Add(t);
					}
					else if (genres.Contains(t.Genre) && albums.Contains(t.Album) && artists.Contains(t.Album) && int.Parse(t.Year) >= startyear && int.Parse(t.Year) <= endyear) 
					{
						playlist.Add(t);
					}
					else { }
				}
				catch
				{
				}
			}
		}

		#endregion				

		#region Properties

		public string Path
		{
			get
			{
				return path;
			}

			set
			{
				path = value;
			}
		}


		public IList Artists
		{
			get
			{
				return artists;
			}
		}

		public IList Albums
		{
			get
			{
				return albums;
			}
		}

		public IList Genres
		{
			get
			{
				return genres;
			}
		}

		public ID3Tag this[int i]
		{
			get
			{
				return (ID3Tag)playlist[i];
			}
		}

		public int Count
		{
			get
			{
				return playlist.Count;
			}
		}

		public int StartYear
		{
			get
			{
				return startyear;
			}
			set
			{
				startyear = value;
			}
		}

		public int EndYear
		{
			get
			{
				return endyear;
			}
			set
			{
				endyear = value;
			}
		}

		#endregion

		#region Static Members
		public static void Save(string filename, Playlist list)
		{
			FileStream fs = new FileStream(filename, FileMode.Create);
			try 
			{
				M3UFormatter m = new M3UFormatter(MP3Utilities.ListType.Extended);
				ID3Tag[] plist = new ID3Tag[list.Count];
				list.playlist.CopyTo(0, plist, 0, list.Count);
				m.Serialize(fs, plist);
			}
			catch {}
		}
		#endregion

		#region Overridden Members

		public override string ToString()
		{
			string s = "";
			foreach (ID3Tag tag in playlist) 
			{
				s += tag.Path + "\n";
			}
			return s;
		}

		#endregion
	}
}