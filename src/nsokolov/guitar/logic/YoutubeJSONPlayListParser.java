package nsokolov.guitar.logic;

import org.json.JSONObject;

import android.graphics.Bitmap;
import nsokolov.guitar.entities.YoutubeEntity;

public class YoutubeJSONPlayListParser extends YoutubeJSONEntityParser<YoutubeEntity> {

	public YoutubeJSONPlayListParser(int deviceSizeCategory) {
		super(deviceSizeCategory);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void ParseAdditionalSection(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected YoutubeEntity CreateYoutubeEntity(String entityName,
			String entityId, Bitmap bitmap) {
		// TODO Auto-generated method stub
		return new YoutubeEntity(entityName,entityId,bitmap);
	}

}
