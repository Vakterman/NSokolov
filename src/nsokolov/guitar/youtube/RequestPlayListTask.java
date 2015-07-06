package nsokolov.guitar.youtube;


import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.interfaces.IYoutubeQuerieExecutor;
import nsokolov.guitar.interfaces.IYoutubeQuery;
import nsokolov.guitar.logic.BaseTask;
import nsokolov.guitar.logic.YoutubePlayListExecutor;

public class RequestPlayListTask extends BaseTask<YoutubeEntity> {

	public static final int STATUS_DOWNLOAD_END = 1253;
	
	public  RequestPlayListTask( IYoutubeQuery<YoutubeEntity>queryPlayList, IHandleTaskResult<YoutubeEntity> listActivity) {
		super(queryPlayList,listActivity);
	}

	@Override
	public IYoutubeQuerieExecutor CreateYoutubeQueryExecutor(IYoutubeQuery<YoutubeEntity> youtubeQuery) {
		
		// TODO Auto-generated method stub
		return new YoutubePlayListExecutor(youtubeQuery);
	}
	
	

}
