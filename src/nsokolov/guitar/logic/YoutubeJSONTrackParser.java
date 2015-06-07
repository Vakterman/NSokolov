package nsokolov.guitar.logic;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import nsokolov.guitar.entities.JSONSectionNotFound;
import nsokolov.guitar.entities.YoutubeEntityTrack;

public class YoutubeJSONTrackParser extends YoutubeJSONEntityParser<YoutubeEntityTrack>{

	private String _videoCode;
	@Override
	protected void ParseAdditionalSection(JSONObject jsonObject) throws JSONSectionNotFound, JSONException {
		// TODO Auto-generated method stub
		if(!jsonObject.has("snippet"))
		{
			throw new JSONSectionNotFound("snippet");
		}
		
		JSONObject snippet = jsonObject.getJSONObject("snippet");
		// TODO Auto-generated method stub
		if(!snippet.has("resourceId"))
		{
			throw new JSONSectionNotFound("resourceId");
		}
		_videoCode = snippet.getJSONObject("resourceId").getString("videoId");
	}

	@Override
	protected YoutubeEntityTrack CreateYoutubeEntity(String entityName,
			String entityId, Bitmap bitmap) {
		// TODO Auto-generated method stub
		YoutubeEntityTrack result =  new YoutubeEntityTrack(entityName,entityId,bitmap);
		result.SetVideoCode(_videoCode);
		return result;
	}

}
