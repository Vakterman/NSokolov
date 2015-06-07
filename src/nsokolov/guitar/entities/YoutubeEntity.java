package nsokolov.guitar.entities;

import android.graphics.Bitmap;

public class YoutubeEntity {
	private String _ContentLink;
	private String _videoEntityName;
	private String _defaultImageLink;
	public Bitmap _image;
	
	public  YoutubeEntity(String playListId, String playListName, String defaultImageLink)
	{
		_ContentLink = playListId;
		_videoEntityName = playListName;
		_defaultImageLink = defaultImageLink;
	}
	
	public  YoutubeEntity(String playListName, String contentLink, Bitmap bitmap)
	{
		_ContentLink = contentLink;
		_videoEntityName = playListName;
		_image = bitmap;
	}
	
	public String	GetId(){
		return _ContentLink;
	}
	
	public String GetPlayListName()
	{
		return _videoEntityName;
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
