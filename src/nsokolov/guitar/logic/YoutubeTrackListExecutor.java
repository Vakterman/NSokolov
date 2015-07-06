package nsokolov.guitar.logic;

import nsokolov.guitar.entities.YoutubeEntityTrack;
import nsokolov.guitar.interfaces.IYoutubeQuery;

public class YoutubeTrackListExecutor extends YoutubeBaseExecutor<YoutubeEntityTrack> {

	public YoutubeTrackListExecutor(IYoutubeQuery<YoutubeEntityTrack> query) {
		// TODO Auto-generated constructor stub
		super(query);
	}

	@Override
	protected YoutubeJSONEntityParser<YoutubeEntityTrack> CreateEntityParser() {
		// TODO Auto-generated method stub
		return new YoutubeJSONTrackParser();
	}

}
