package nsokolov.guitar.entities;

import java.util.List;

import nsokolov.guitar.interfaces.IYoutubeQuery;

import com.google.common.collect.Lists;


public class YoutubeQueriePlayList implements IYoutubeQuery<YoutubeEntity>
{
	private List<YoutubeEntity> mYoutubePlayList;
	
	public void SetPlayList(YoutubeEntity playList){
		
	}
	
	@Override
	public Iterable<YoutubeEntity> GetResult() {
		// TODO Auto-generated method stub
		return mYoutubePlayList;
	}
	

	@Override
	public String GetQueryUrl() {
		// TODO Auto-generated method stub
		return "https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCEE0t9M1_kDhBJscM-UGBuw&key=" + AndroidDeveloperKeys.DeveloperKey ;
	}

	@Override
	public void SetResult(Iterable<YoutubeEntity> result) {
		// TODO Auto-generated method stub
		mYoutubePlayList = Lists.newLinkedList(result);
	}
	
}