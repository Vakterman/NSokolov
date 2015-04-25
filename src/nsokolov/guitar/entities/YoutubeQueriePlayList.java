package nsokolov.guitar.entities;

import java.util.List;

import com.google.common.collect.Lists;


public class YoutubeQueriePlayList implements IYoutubeQuery<YoutubePlaylist>
{
	private List<YoutubePlaylist> mYoutubePlayList;
	
	public void SetPlayList(YoutubePlaylist playList){
		
	}
	
	@Override
	public Iterable<YoutubePlaylist> GetResult() {
		// TODO Auto-generated method stub
		return mYoutubePlayList;
	}
	

	@Override
	public String GetQueryUrl() {
		// TODO Auto-generated method stub
		return "http://gdata.youtube.com/feeds/api/users/NikolaySokolovGuitar/playlists?v=2";
	}

	@Override
	public void SetResult(Iterable<YoutubePlaylist> result) {
		// TODO Auto-generated method stub
		mYoutubePlayList = Lists.newLinkedList(result);
	}
	
}