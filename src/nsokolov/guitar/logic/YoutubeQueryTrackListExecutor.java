package nsokolov.guitar.logic;

import java.io.IOException;

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

import nsokolov.guitar.entities.YoutubePlaylist;
import nsokolov.guitar.entities.YoutubeQueryTracks;

public class YoutubeQueryTrackListExecutor implements IYoutubeQuerieExecutor {

	private String _urlQueryTrackList;
	public YoutubeQueryTrackListExecutor(YoutubeQueryTracks queryTrack, String playListId)
	{
		_urlQueryTrackList = queryTrack.GetQueryUrl().replace("_ID_",playListId);
	}
	
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpUriRequest request = new HttpGet(_urlQueryTrackList);
			
			HttpResponse httpResponse = httpClient.execute(request);
			
			String jsonString = EntityUtils.toString(httpResponse.getEntity());
			 JSONObject json = new JSONObject(jsonString);
	         JSONArray jsonArray = json.getJSONObject("feed").getJSONArray("entry");
	         
	         for (int i = 0; i < jsonArray.length(); i++) {
	        	 JSONObject jsonObject = jsonArray.getJSONObject(i);

	             String video = jsonObject.getJSONObject("content").getString("src");
	             String title = jsonObject.getJSONObject("title").getString("$t");
	             
	             VideoEntry videoEntry = new VideoEntry(title);
	             videoEntry.setId(video);
	             videoEntry.setTitle(TextConstruct.plainText(title));
	     
	        
	         }
		}
		catch (ClientProtocolException e) {
	         //Log.e("Feck", e);
	     } catch (IOException e) {
	         //Log.e("Feck", e);
	     } catch (JSONException e) {
	         //Log.e("Feck", e);
	    }	
		
		synchronized(this)
		{
		 notifyAll();
		}
	}

}
