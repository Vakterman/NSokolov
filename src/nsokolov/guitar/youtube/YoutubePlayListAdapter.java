package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.logic.Utilities;
import nsokolov.guitar.logic.VideoEntityItemAdapter;
import android.content.res.Configuration;
import android.view.ViewGroup;
import android.widget.GridView;

public class YoutubePlayListAdapter extends VideoEntityItemAdapter<YoutubeEntity> {

	public YoutubePlayListAdapter(IContext<YoutubeEntity> context, int textViewResourceId, List<YoutubeEntity> youtubeEntitylist) {
		super(context, textViewResourceId,youtubeEntitylist);
		// TODO Auto-generated constructor stub
	}


		@Override
		protected void CreateView(ViewGroup view, nsokolov.guitar.entities.YoutubeEntity youtubeEntity) {
			view.addView(createImageViewByImgLink(youtubeEntity.GetImage()));
			view.addView(createSpecifiedTextViewByText(youtubeEntity.GetPlayListName()));
			
			view.setMinimumHeight(280);
			view.setMinimumWidth(320);
			
			int _deviceSizeCategory = new Utilities(_Context.GetContext()).GetSizeCategory();
			if(_deviceSizeCategory == Configuration.SCREENLAYOUT_SIZE_NORMAL || _deviceSizeCategory == Configuration.SCREENLAYOUT_SIZE_LARGE)
			{
				view.setLayoutParams(new GridView.LayoutParams(320, 250));
			}
			else
			{
				view.setLayoutParams(new GridView.LayoutParams(190, 200));
			}
		}
}
