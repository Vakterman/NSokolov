package nsokolov.guitar.logic;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IYoutubeQuerieExecutor;
import nsokolov.guitar.interfaces.IYoutubeQuery;

public abstract class YoutubeBaseExecutor<T extends YoutubeEntity> implements IYoutubeQuerieExecutor {

	protected IYoutubeQuery<T> _playListQuery;
	
	public YoutubeBaseExecutor(IYoutubeQuery<T> playListQuery)
	{
		_playListQuery = playListQuery;
	}
	
	@Override
	public void Execute() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest request = new HttpGet(_playListQuery.GetQueryUrl());
		
		try {
			HttpResponse httpResponse = httpClient.execute(request);

			String jsonResult = EntityUtils.toString(httpResponse.getEntity());
			YoutubeJSONEntityParser<T> youtubeJSONParser = CreateEntityParser();
			_playListQuery.SetResult(youtubeJSONParser.Parse(jsonResult));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract YoutubeJSONEntityParser<T> CreateEntityParser();

}
