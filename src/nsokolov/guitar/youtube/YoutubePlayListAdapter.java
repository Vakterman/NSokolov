package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.logic.VideoEntityItemAdapter;
import android.view.ViewGroup;
import android.widget.GridView;

public class YoutubePlayListAdapter extends VideoEntityItemAdapter {


	 
	 public YoutubePlayListAdapter(IContext context, int textViewResourceId, List<YoutubeEntity> youtubeEntitylist) {
		super(context, textViewResourceId,youtubeEntitylist);
		// TODO Auto-generated constructor stub
	}


		@Override
		protected void CreateView(ViewGroup view, nsokolov.guitar.entities.YoutubeEntity youtubeEntity) {
			view.addView(createImageViewByImgLink(youtubeEntity.GetImage()));
			view.addView(createSpecifiedTextViewByText(youtubeEntity.GetPlayListName()));
			view.setLayoutParams(new GridView.LayoutParams(190, 200));
		}}
