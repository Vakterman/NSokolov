package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.logic.Utilities;
import nsokolov.guitar.logic.VideoEntityItemAdapter;
import android.content.res.Configuration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class YoutubePlayListAdapter extends VideoEntityItemAdapter<YoutubeEntity> {

	public YoutubePlayListAdapter(IContext<YoutubeEntity> context, int layoutResourceId, List<YoutubeEntity> youtubeEntitylist) {
		super(context, layoutResourceId,youtubeEntitylist);
		// TODO Auto-generated constructor stub
	}


		@Override
		protected void CreateView(ViewGroup view, nsokolov.guitar.entities.YoutubeEntity youtubeEntity) {

			int screen_resolution = new Utilities(_Context.GetContext()).GetSizeCategory();
			TextView textView = (TextView)view.findViewById(R.id.PlayListTitle);
			textView.setTextSize(ResolutionManager.GetFontSize(screen_resolution));
			textView.setText(youtubeEntity.GetPlayListName());
			
			ImageView imageView = (ImageView)view.findViewById(R.id.PlayListImage);
			imageView.setImageBitmap(youtubeEntity.GetImage());
			
		    view.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		}
}
