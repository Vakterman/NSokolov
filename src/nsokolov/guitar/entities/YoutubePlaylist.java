package nsokolov.guitar.entities;

import java.util.ArrayList;

import com.google.gdata.data.youtube.VideoEntry;

public class YoutubePlaylist {
	private String _contentLink;
	private String _playListName;
	private String _defaultImageLink;
	
	public  YoutubePlaylist(String contentLink, String playListName, String defaultImageLink)
	{
		_contentLink = contentLink;
		_playListName = playListName;
		_defaultImageLink = defaultImageLink;
	}
	
	public String	GetContentLink(){
		return _contentLink;
	}
	
	public String GetPlayListName()
	{
		return _playListName;
	}
}
