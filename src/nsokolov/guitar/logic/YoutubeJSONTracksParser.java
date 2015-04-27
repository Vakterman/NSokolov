package nsokolov.guitar.logic;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gdata.data.TextConstruct;
import com.google.gdata.data.youtube.VideoEntry;

public class YoutubeJSONTracksParser {

	public List<VideoEntry> parse(String jsonResponse){
		 List<VideoEntry> videoEntriesResult = new LinkedList<VideoEntry>();
		 JSONObject json;
		try {
			json = new JSONObject(jsonResponse);
		
			JSONArray jsonArray;

			jsonArray = json.getJSONObject("feed").getJSONArray("entry");
			for (int i = 0; i < jsonArray.length(); i++) {
	        	 JSONObject jsonObject = jsonArray.getJSONObject(i);

	             String video = jsonObject.getJSONObject("media$group").getJSONObject("yt$videoid").getString("$t");
	             String title = jsonObject.getJSONObject("title").getString("$t");
	             
	             VideoEntry videoEntry = new VideoEntry(title);
	             videoEntry.setId(video);
	             videoEntry.setTitle(TextConstruct.plainText(title));
	     
	             videoEntriesResult.add(videoEntry);
	         }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         
         
         return videoEntriesResult;
	}
}
