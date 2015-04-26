package nsokolov.guitar.youtube;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import nsokolov.guitar.entities.IHandleTaskResult;
import nsokolov.guitar.entities.YoutubeQueryTracks;
import nsokolov.guitar.logic.YoutubeQueryTrackListExecutor;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.youtube.VideoEntry;

import android.net.Uri;
import android.os.AsyncTask;

public class RequestTrackListTask extends AsyncTask <Void, Void,Void > {

	private String  _playListId = null;
	private IHandleTaskResult<Iterable<VideoEntry>> _handlePlayListResult = null;
	private YoutubeQueryTracks _query;
	
	public  RequestTrackListTask(String playListId, IHandleTaskResult<Iterable<VideoEntry>> handler) {
		// TODO Auto-generated constructor stub
		_playListId = playListId;
		_handlePlayListResult = handler;
		
	}
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub

		_query = new YoutubeQueryTracks(_playListId);
		YoutubeQueryTrackListExecutor trackListExecutor = new YoutubeQueryTrackListExecutor(_query);
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
