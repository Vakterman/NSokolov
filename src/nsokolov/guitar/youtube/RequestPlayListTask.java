package nsokolov.guitar.youtube;

import nsokolov.guitar.entities.YoutubeQueriePlayList;
import nsokolov.guitar.logic.YoutubePlayListExecutor;
import android.os.AsyncTask;

public class RequestPlayListTask extends AsyncTask<Void,Void,Void> {

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		YoutubePlayListExecutor plExec = new YoutubePlayListExecutor(new YoutubeQueriePlayList());
		
		plExec.Execute();
		return null;
	}

}
