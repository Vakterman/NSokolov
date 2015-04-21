package nsokolov.guitar.entities;

import java.util.ArrayList;

import com.google.gdata.data.youtube.VideoEntry;

public class YoutubePlaylist {
	private String _contentLink;
	private String _playListName;
	
	public  YoutubePlaylist(String contentLink, String playListName)
	{
		_contentLink = contentLink;
		_playListName = playListName;
	}
	
	public String	GetContentLink(){
		return _contentLink;
	}
	
	public String GetPlayListName()
	{
		return _playListName;
	}
}
