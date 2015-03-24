package nsokolov.guitar.youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.VideoEntry;

import android.os.AsyncTask;

public class RequestVideosTask extends AsyncTask <Void, Void,Void > {
	
	private List<VideoEntry> _videoEntryList = new ArrayList<VideoEntry>();
	
	protected Void doInBackground(Void...args)
	{
		GetVideoCollection();
		return null;	
	}
	
	
	
	private void GetVideoCollection()
	{
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpUriRequest request = new HttpGet("http://gdata.youtube.com/feeds/api/users/NikolaySokolovGuitar/uploads?v=2&alt=jsonc");
			
			HttpResponse httpResponse = httpClient.execute(request);
			
			String jsonString = EntityUtils.toString(httpResponse.getEntity());
			 JSONObject json = new JSONObject(jsonString);
	         JSONArray jsonArray = json.getJSONObject("data").getJSONArray("items");
	         
	         for (int i = 0; i < jsonArray.length(); i++) {
	        	 JSONObject jsonObject = jsonArray.getJSONObject(i);

	             String title = jsonObject.getString("title");
	             String video = jsonObject.getString("id");
	             
	             VideoEntry videoEntry = new VideoEntry(title);
	             videoEntry.setId(video);
	             videoEntry.setTitle(TextConstruct.plainText(title));
	     
	        	 _videoEntryList.add(videoEntry);
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
	
	protected void onPostExecute(Long result) {
        notifyAll();
    }
	
	
	public List<VideoEntry> GetCollectedVideos()
	{
		List<VideoEntry> collectedVideos = Collections.unmodifiableList(_videoEntryList);
		return collectedVideos;
	}
}
