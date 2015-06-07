package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.interfaces.IYoutubeQuery;
import nsokolov.guitar.logic.YoutubePlayListExecutor;
import android.os.AsyncTask;

public class RequestPlayListTask extends AsyncTask<Void,Void,Void> {

	public static final int STATUS_DOWNLOAD_END = 1253;
	private IYoutubeQuery<YoutubeEntity> mQueryPlayList;
	private IContext mListActivity;
	
	public  RequestPlayListTask( IYoutubeQuery<YoutubeEntity>queryPlayList, IContext listActivity) {
		// TODO Auto-generated constructor stub
		mQueryPlayList = queryPlayList;
		mListActivity = listActivity;
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		YoutubePlayListExecutor plExec = new YoutubePlayListExecutor(mQueryPlayList);
		
		plExec.Execute();
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		YoutubePlayListAdapter adapter = new YoutubePlayListAdapter(mListActivity,R.layout.youtube_thumb_item, (List<YoutubeEntity>) mQueryPlayList.GetResult());
		mListActivity.SetAdapter(adapter);
	}
	
	

}
