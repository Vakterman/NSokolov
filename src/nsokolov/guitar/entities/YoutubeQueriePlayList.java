package nsokolov.guitar.entities;


public class YoutubeQueriePlayList implements IYoutubeQuery<YoutubePlaylist>
{
	public void SetPlayList(YoutubePlaylist playList){
		
	}
	
	@Override
	public Iterable<YoutubePlaylist> GetResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetQueryUrl() {
		// TODO Auto-generated method stub
		return "http://gdata.youtube.com/feeds/api/users/NikolaySokolovGuitar/playlists?v=2";
	}
	
}