package nsokolov.guitar.youtube;

import nsokolov.guitar.entities.AndroidDeveloperKeys;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class YoutubePlayLessonActivity extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	YouTubePlayer mPlayer = null;
	String m_videoCode;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.start_new_video);
		YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
				.findFragmentById(R.id.youtube_fragment);
		youTubePlayerFragment.initialize(AndroidDeveloperKeys.DeveloperKey,
				this);

		Bundle b = getIntent().getExtras();
		m_videoCode = b.getString("videoCode");
	}

	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean wasRestored) {
		mPlayer = player;
		// TODO Auto-generated method stub
		if (!wasRestored) {
			player.cueVideo(m_videoCode);
			Configuration config = getResources().getConfiguration();
			
			if(config.orientation != Configuration.ORIENTATION_LANDSCAPE)
			{
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}
			else
			{
				StartPlayer();
			}
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
				&& mPlayer != null) {
			StartPlayer();
		}
	}

	public void StartPlayer()
	{
		mPlayer.setFullscreen(true);
		
		Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() { 
	         public void run() { 
	        	 mPlayer.play();
	         } 
	    }, 1000); 
	}
}
