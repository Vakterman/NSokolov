package nsokolov.guitar.entities;

import java.util.List;

import nsokolov.guitar.interfaces.IYoutubeQuery;
import android.content.res.Configuration;

import com.google.common.collect.Lists;

public class YoutubeQueryTracks implements IYoutubeQuery<YoutubeEntityTrack> {
  private String _urlGetVideosListFromPlayList ="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=_ID_&key=" + AndroidDeveloperKeys.DeveloperKey;
  
  private String _urlGetVideosById;
  private List<YoutubeEntityTrack> _videoEntries;
  protected int _deviceConfigurationSize = Configuration.SCREENLAYOUT_SIZE_NORMAL;
  
  public YoutubeQueryTracks(String playListId,int deviceConfigurationSize) {
	// TODO Auto-generated constructor stub
	  _urlGetVideosById = _urlGetVideosListFromPlayList.replace("_ID_",playListId);
	  _deviceConfigurationSize = deviceConfigurationSize;
  }
@Override
public Iterable<YoutubeEntityTrack> GetResult() {
	// TODO Auto-generated method stub
	return _videoEntries;
}

@Override
public String GetQueryUrl() {
	// TODO Auto-generated method stub
	return _urlGetVideosById;
}

@Override
public void SetResult(Iterable<YoutubeEntityTrack> result) {
	// TODO Auto-generated method stub
	_videoEntries = Lists.newLinkedList(result);
}
@Override
public int GetSizeConfiguration() {
	// TODO Auto-generated method stub
	return _deviceConfigurationSize;
}
  
  
}
