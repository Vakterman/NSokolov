package nsokolov.guitar.logic;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import nsokolov.guitar.entities.YoutubeQueryTracks;

public class YoutubeQueryTrackListExecutor implements IYoutubeQuerieExecutor {

	private YoutubeQueryTracks _youtubeQuery;;
	public YoutubeQueryTrackListExecutor(YoutubeQueryTracks queryTrack)
	{
		_youtubeQuery = queryTrack;
	}
	
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		
		HttpClient httpDefaultClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(_youtubeQuery.GetQueryUrl());
		
		try {
				HttpResponse response = 	httpDefaultClient.execute(getRequest);
				String jSonResponse = EntityUtils.toString(response.getEntity());
				_youtubeQuery.SetResult((new YoutubeJSONTracksParser()).parse(jSonResponse));
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
