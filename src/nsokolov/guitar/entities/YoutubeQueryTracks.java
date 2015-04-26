package nsokolov.guitar.entities;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gdata.data.youtube.VideoEntry;

public class YoutubeQueryTracks implements IYoutubeQuery<VideoEntry> {
  private String _urlGetVideosFromPlayList = 	"http://gdata.youtube.com/feeds/api/playlists/_ID_?v=2&alt=json";
  private String _urlGetVideosById;
  private List<VideoEntry> _videoEntries;
  
  
  public YoutubeQueryTracks(String playListId) {
	// TODO Auto-generated constructor stub
	  _urlGetVideosById = _urlGetVideosFromPlayList.replace("_ID_",playListId);
  }
@Override
public Iterable<VideoEntry> GetResult() {
	// TODO Auto-generated method stub
	return _videoEntries;
}

@Override
public String GetQueryUrl() {
	// TODO Auto-generated method stub
	return _urlGetVideosById;
}

@Override
public void SetResult(Iterable<VideoEntry> result) {
	// TODO Auto-generated method stub
	_videoEntries = Lists.newLinkedList(result);
}
  
  
}
