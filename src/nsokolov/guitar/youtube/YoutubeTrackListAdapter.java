package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.logic.VideoEntityItemAdapter;
import android.view.ViewGroup;

public class YoutubeTrackListAdapter<YoutubeEntityTrack> extends VideoEntityItemAdapter {

	public YoutubeTrackListAdapter(IContext context, int textViewResourceId,
			List<YoutubeEntity> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	protected void CreateView(ViewGroup view, YoutubeEntity youtubeEntity) {
		// TODO Auto-generated method stub
		view.addView(createImageViewByImgLink(youtubeEntity.GetImage()));
		view.addView(createSpecifiedTextViewByText(youtubeEntity.GetPlayListName()));
	}
	
}
