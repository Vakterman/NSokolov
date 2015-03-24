package nsokolov.guitar.youtube;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.YouTubeThumbnailView.OnInitializedListener;




public class ThumbnailInitializeListener implements OnInitializedListener {

	@Override
	public void onInitializationFailure(YouTubeThumbnailView arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitializationSuccess(YouTubeThumbnailView view,
			YouTubeThumbnailLoader loader) {
		// TODO Auto-generated method stub
		String videoId = (String) view.getTag();
	    loader.setVideo(videoId);
	}

}
