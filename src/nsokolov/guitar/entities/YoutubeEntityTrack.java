package nsokolov.guitar.entities;

import android.graphics.Bitmap;

public class YoutubeEntityTrack extends YoutubeEntity {

	public YoutubeEntityTrack(String playListName, String contentLink,
			Bitmap bitmap) {
		super(playListName, contentLink, bitmap);
		// TODO Auto-generated constructor stub
	}
	
	protected String _videoCode;
	
	public String GetVideoCode()
	{
		return _videoCode;
	}

	public void SetVideoCode(String videoCode)
	{
		_videoCode = videoCode;
	}
}
