package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntityTrack;
import nsokolov.guitar.entities.YoutubeQueryTracks;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.interfaces.IOnNetworkStateChangeHandler;
import nsokolov.guitar.logic.ErrorState;
import nsokolov.guitar.logic.InternetConnectionChecker;
import nsokolov.guitar.logic.NetworkStateChangedReceiver;
import nsokolov.guitar.logic.Utilities;
import nsokolov.guitar.logic.VideoEntityItemAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class YoutubeTrackListActivity extends BaseNetworkActivity implements IContext<YoutubeEntityTrack> {

	public YoutubeTrackListActivity() {
		super(R.layout.track_list);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
	private String GetPlayListId()
	{
		Intent intent = getIntent();
		
		Bundle extras = intent.getExtras();
		String playListId = (String) extras.get("playListId");
		intent.putExtras(extras);
		return playListId;
	}
	
	private void  InitVideoEntriesList(Iterable<YoutubeEntityTrack> result)
	{
		
		List<YoutubeEntityTrack> listVideoEntry = (List<YoutubeEntityTrack>)result;
		
		VideoEntityItemAdapter<YoutubeEntityTrack> videoEntityItemAdapter = new YoutubeTrackListAdapter(this,R.layout.youtube_track_item,listVideoEntry);
		
		final ListView listView = (ListView)findViewById(R.id.track_list);
		listView.setAdapter(videoEntityItemAdapter);
		
		listView.setOnItemClickListener(
				   new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						YoutubeEntityTrack videoEntry = (YoutubeEntityTrack)listView.getItemAtPosition(position);
						StartSelectedLesson(videoEntry.GetVideoCode());
					}
					   
				   }
				);
		
	}
	
	
	private void StartSelectedLesson(String videoTag)
	{
		Intent intent = new Intent(YoutubeTrackListActivity.this, YoutubePlayLessonActivity.class);
		Bundle b = new Bundle();
		b.putString("videoCode",videoTag); //Your id
		intent.putExtras(b); //Put your id to your next Intent
		startActivity(intent);
	}
	


	

	@Override
	public void OnPlayListClik(YoutubeEntityTrack track) {
		// TODO Auto-generated method stub
		StartTrackListTask(track.GetVideoCode());
	}


	@Override
	public Context GetContext() {
		// TODO Auto-generated method stub
		return this;
	}


	@Override
	public void SetAdapter(BaseAdapter adapter) {
		// TODO Auto-generated method stub
		
	}
	
	public void StartTrackListTask(String trackId)
	{
		Intent intent = new Intent(YoutubeTrackListActivity.this, YoutubePlayLessonActivity.class);
		Bundle b = new Bundle();
		b.putString("videoCode", trackId);
		intent.putExtras(b);
		startActivity(intent);
	}
	
	protected void RetryNetworkOperation()
	{
		RequestTrackListTask trackListTask = new RequestTrackListTask(new YoutubeQueryTracks(GetPlayListId(), new Utilities(this).GetSizeCategory()), new IHandleTaskResult<YoutubeEntityTrack>() {
			
			@Override
			public void HandleResult(Iterable<YoutubeEntityTrack> result) {
				// TODO Auto-generated method stub
				if(result != null)
				{
					InitVideoEntriesList(result);
					LoadLessonsPanel();
				}
				else{
					LoadNetworkWarningPanel();
				}
			}
		});
		
		trackListTask.execute();
	}


	@Override
	public void OnNetworkWorkStateConnected() {
		// TODO Auto-generated method stub
		RetryNetworkOperation();
	}


	@Override
	public void OnNetworkStateDisconnected() {
		// TODO Auto-generated method stub
		LoadNetworkWarningPanel();
	}
}
