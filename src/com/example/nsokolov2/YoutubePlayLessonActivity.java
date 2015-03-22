package com.example.nsokolov2;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;


public class YoutubePlayLessonActivity extends YouTubeBaseActivity implements
YouTubePlayer.OnInitializedListener {

	String m_videoCode;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_new_video);
		YouTubePlayerFragment youTubePlayerFragment =
		        (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
		    youTubePlayerFragment.initialize(AndroidDeveloperKeys.DeveloperKey, this);
		    	
		    Bundle b = getIntent().getExtras();
		    m_videoCode = b.getString("videoCode");
		    m_videoCode = b.getString("videoCode");
	}
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean wasRestored) {
		// TODO Auto-generated method stub
		 if (!wasRestored) {
		      player.cueVideo(m_videoCode);
		    }
		
	}

}
