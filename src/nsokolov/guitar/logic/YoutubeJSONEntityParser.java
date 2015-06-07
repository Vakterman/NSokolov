package nsokolov.guitar.logic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import nsokolov.guitar.entities.JSONSectionNotFound;
import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IParser;

public abstract class YoutubeJSONEntityParser<T extends YoutubeEntity> implements IParser<T> {

	@Override
	public List<T> Parse(String jsonResponse) {
		// TODO Auto-generated method stub
		 List<T> videoEntriesResult = new LinkedList<T>();
		 JSONObject json;
		try {
			json = new JSONObject(jsonResponse);
		
			JSONArray jsonArray;

			jsonArray = json.getJSONArray("items");
			for (int i = 0; i < jsonArray.length(); i++) {
	        	 JSONObject jsonObject = jsonArray.getJSONObject(i);
	        	 
	        	 if(!jsonObject.has("id"))
	        	 {
	        		 throw new JSONSectionNotFound("id");
	        	 }
	        	 String id = jsonObject.getString("id");
	        	 
	        	 
	        	 if(!jsonObject.has("snippet"))
	        	 {
	        		 throw new JSONSectionNotFound("snippet");
	        	 }
	        	 
	        	 JSONObject snippetObject = jsonObject.getJSONObject("snippet");
	        	 
	        	 if(!snippetObject.has("title"))
	        	 {
	        		 throw new JSONSectionNotFound("title");
	        	 }
	        	 
	        	 String title = snippetObject.getString("title");
	        	 
	        	 ParseAdditionalSection(jsonObject);	
	        	
	        	 T playList =  CreateYoutubeEntity(title, id, GetImageByLink(GetAppropriateBitmapLink(snippetObject)));
	         
	        	
	        	 
	             videoEntriesResult.add(playList);
	         }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(JSONSectionNotFound e)
		{
			Log.e("ParseError",String.format("Section %s not found in play lists JSON",  e.GetSectionName()));
		}
         
		
		return videoEntriesResult;
	}

	private Bitmap GetImageByLink(String link){
    	Bitmap bm = null;
		try {
            URL aURL = new URL(link);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            
            return bm;
       } catch (IOException e) {
           Log.e("LoadImage", "Error getting bitmap", e);
       }
		return null;
	}
		
	private String GetAppropriateBitmapLink(JSONObject jsonSnippetObject) throws JSONSectionNotFound, JSONException
	{
		if(!jsonSnippetObject.has("thumbnails"))
		{
			throw new JSONSectionNotFound("thumbnails");
		}
		
		JSONObject thumbnails = jsonSnippetObject.getJSONObject("thumbnails");
		if(thumbnails.has("default")){
			JSONObject jsonDefaultObject = thumbnails.getJSONObject("default");
			return jsonDefaultObject.getString("url");
		}
		return "";
	}
	
	protected abstract void ParseAdditionalSection(JSONObject jsonObject) throws JSONSectionNotFound, JSONException;
	
	protected abstract T CreateYoutubeEntity(String entityName, String entityId, Bitmap bitmap);
}
