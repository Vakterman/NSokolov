package nsokolov.guitar.logic;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import nsokolov.guitar.entities.IYoutubeQuery;
import nsokolov.guitar.entities.YoutubePlaylist;

public class YoutubePlayListExecutor implements
		IYoutubeQuerieExecutor<YoutubePlaylist> {

	private IYoutubeQuery<YoutubePlaylist> _playListQuery;

	public YoutubePlayListExecutor(IYoutubeQuery<YoutubePlaylist> query) {
		_playListQuery = query;
	}

	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest request = new HttpGet(_playListQuery.GetQueryUrl());

		try {
			HttpResponse httpResponse = httpClient.execute(request);

			String xmlString = EntityUtils.toString(httpResponse.getEntity());
			YoutubePlayListParser youtubePlayListParser = new YoutubePlayListParser();
			try {
				youtubePlayListParser.parse(xmlString);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
