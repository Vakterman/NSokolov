package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.entities.YoutubeEntityTrack;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.logic.Utilities;
import nsokolov.guitar.logic.VideoEntityItemAdapter;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class YoutubeTrackListAdapter extends VideoEntityItemAdapter<YoutubeEntityTrack> {

	public YoutubeTrackListAdapter(IContext<YoutubeEntityTrack> context, int textViewResourceId,
			List<YoutubeEntityTrack> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void CreateView(ViewGroup view, YoutubeEntity youtubeEntity) {
		int screen_resolution = new Utilities(_Context.GetContext()).GetSizeCategory();
		TextView textView = (TextView)view.findViewById(R.id.TrackTitle);
		textView.setTextSize(ResolutionManager.GetFontSize(screen_resolution));
		textView.setText(youtubeEntity.GetPlayListName());
		
		ImageView imageView = (ImageView)view.findViewById(R.id.TrackImage);
		imageView.setImageBitmap(youtubeEntity.GetImage());
		
	    view.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	}
	
}
