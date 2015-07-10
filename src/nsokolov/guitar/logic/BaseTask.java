package nsokolov.guitar.logic;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.interfaces.IYoutubeQuerieExecutor;
import nsokolov.guitar.interfaces.IYoutubeQuery;
import android.os.AsyncTask;

public abstract class BaseTask<T extends YoutubeEntity> extends AsyncTask<Void,Void,Void> {

	protected IYoutubeQuery<T> _youtubeQuery;
	protected IHandleTaskResult<T> _handlerResult;
	
	public BaseTask(IYoutubeQuery<T> youtubeQuery,IHandleTaskResult<T> handler)
	{
		_handlerResult = handler;
		_youtubeQuery = youtubeQuery;
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		IYoutubeQuerieExecutor executor = CreateYoutubeQueryExecutor(_youtubeQuery);
		executor.Execute();
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(_handlerResult != null)
		{
			_handlerResult.HandleResult(_youtubeQuery.GetResult());
		}
	}
	public  abstract  IYoutubeQuerieExecutor CreateYoutubeQueryExecutor(IYoutubeQuery<T> youtubeQuery);
}
