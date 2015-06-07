package nsokolov.guitar.youtube;


import com.google.gdata.data.youtube.VideoEntry;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.entities.YoutubeEntityTrack;
import nsokolov.guitar.entities.YoutubeQueryTracks;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.logic.YoutubeTrackListExecutor;
import android.os.AsyncTask;

public class RequestTrackListTask extends AsyncTask <Void, Void,Void > {

	private String  _playListId = null;
	private IHandleTaskResult<Iterable<YoutubeEntityTrack>> _handlePlayListResult = null;
	private YoutubeQueryTracks _query;
	
	public  RequestTrackListTask(String playListId, IHandleTaskResult<Iterable<YoutubeEntityTrack>> handler) {
		// TODO Auto-generated constructor stub
		_playListId = playListId;
		_handlePlayListResult = handler;
		
	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub

		_query = new YoutubeQueryTracks(_playListId);
		YoutubeTrackListExecutor trackListExecutor = new YoutubeTrackListExecutor(_query);
		trackListExecutor.Execute();
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		_handlePlayListResult.HandleResult(_query.GetResult());
	}

}
