package nsokolov.guitar.entities;

import android.graphics.Bitmap;

public class YoutubePlaylist {
	private String _Id;
	private String _playListName;
	private String _defaultImageLink;
	public Bitmap _image;
	
	public  YoutubePlaylist(String playListId, String playListName, String defaultImageLink)
	{
		_Id = playListId;
		_playListName = playListName;
		_defaultImageLink = defaultImageLink;
	}
	
	public  YoutubePlaylist(String contentLink, String playListName, Bitmap bitmap)
	{
		_Id = contentLink;
		_playListName = playListName;
		_image = bitmap;
	}
	
	public String	GetId(){
		return _Id;
	}
	
	public String GetPlayListName()
	{
		return _playListName;
	}
	
	public String GetDefaultImageLink()
	{
		return _defaultImageLink;
	}
	
	public Bitmap GetImage()
	{
		return _image;
	}
}
