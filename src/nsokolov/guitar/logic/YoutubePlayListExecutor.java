package nsokolov.guitar.logic;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IYoutubeQuery;

public class YoutubePlayListExecutor extends YoutubeBaseExecutor<YoutubeEntity>{

	public YoutubePlayListExecutor(IYoutubeQuery<YoutubeEntity> playListQuery) {
		super(playListQuery);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected YoutubeJSONEntityParser<YoutubeEntity> CreateEntityParser() {
		// TODO Auto-generated method stub
		return new YoutubeJSONPlayListParser(_playListQuery.GetSizeConfiguration());
	}

	

}
