package com.example.nsokolov2;

import com.google.gdata.client.youtube.YouTubeService;

public final class YouTubeAccessSrvManager {
	private static YouTubeService _youtubeServiceManager;
	
	public static YouTubeService getInstance()
	{
		if(_youtubeServiceManager == null)
		{
			_youtubeServiceManager = new YouTubeService("NikolaySocolov");
		}
		_youtubeServiceManager.setConnectTimeout(2000);
		return _youtubeServiceManager;
	}
}
