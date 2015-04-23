package nsokolov.guitar.youtube;
import java.util.List;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.gdata.data.youtube.VideoEntry;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NSokolov2 extends Activity implements OnInitializedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nsokolov2);
		 
		
		if(CheckThereIsNoInternet())
		{
			ShowAlertDialog("Нет соединения с интернетом!");
		}
		//else
		//{
			//YouTubePlayerFragment youtubePlayer = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.youtube_fragment);
			//youtubePlayer.initialize(AndroidDeveloperKeys.DeveloperKey,this);
		//}
		
		RequestPlayListTask playListTask = new RequestPlayListTask();
		playListTask.execute();
		
		RequestVideosTask requestTask = new RequestVideosTask();
		requestTask.execute();
		try {
			synchronized(requestTask)
			{
				requestTask.wait();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LayoutInflater layoutInflater = getLayoutInflater();
		YoutubeThumbnailsListViewAdapter thumbnailLvAdapter;
		List<VideoEntry> listVideoEntry;
		synchronized(requestTask)
		{
			listVideoEntry = requestTask.GetCollectedVideos();
		}
		
		thumbnailLvAdapter = new YoutubeThumbnailsListViewAdapter(this,listVideoEntry,R.layout.youtube_thumb_item);
		
		final ListView listView = (ListView)findViewById(R.id.youtube_list);
		listView.setAdapter(thumbnailLvAdapter);
		
		listView.setOnItemClickListener(
				   new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						VideoEntry videoEntry = (VideoEntry)listView.getItemAtPosition(position);
						StartSelectedLesson(videoEntry.getId());
					}
					   
				   }
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nsokolov2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean wasRestored) {
		// TODO Auto-generated method stub
	//	if (!wasRestored) {
		//      player.cueVideo("nCgQDjiotG0");
		  //    player.play();
		 //   }
		
	}
	
	
	private boolean CheckThereIsNoInternet()
	{
		 ConnectivityManager cm =
			        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo netInfo = cm.getActiveNetworkInfo();
			    return netInfo == null || !netInfo.isConnectedOrConnecting();
	}
	
	private void ShowAlertDialog(String messageTitle)
	{
		AlertDialog.Builder  dialog = new AlertDialog.Builder(this);
		dialog.setTitle(messageTitle).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("Ok", null);
		dialog.show();
	}
	
	private void StartSelectedLesson(String videoTag)
	{
		Intent intent = new Intent(NSokolov2.this, YoutubePlayLessonActivity.class);
		Bundle b = new Bundle();
		b.putString("videoCode",videoTag); //Your id
		intent.putExtras(b); //Put your id to your next Intent
		startActivity(intent);
	}
}
