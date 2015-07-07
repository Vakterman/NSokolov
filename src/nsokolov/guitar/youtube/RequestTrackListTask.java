package nsokolov.guitar.youtube;

import nsokolov.guitar.entities.YoutubeEntityTrack;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.interfaces.IYoutubeQuerieExecutor;
import nsokolov.guitar.interfaces.IYoutubeQuery;
import nsokolov.guitar.logic.BaseTask;
import nsokolov.guitar.logic.YoutubeTrackListExecutor;

public class RequestTrackListTask extends BaseTask<YoutubeEntityTrack> {


	public  RequestTrackListTask(IYoutubeQuery<YoutubeEntityTrack>queryPlayList, IHandleTaskResult<YoutubeEntityTrack> iHandleTaskResult) {

		super(queryPlayList,iHandleTaskResult);
	}



	@Override
	public IYoutubeQuerieExecutor CreateYoutubeQueryExecutor(
			IYoutubeQuery<YoutubeEntityTrack> youtubeQuery) {
		// TODO Auto-generated method stub
		return new YoutubeTrackListExecutor(youtubeQuery);
	}
	
	
}
