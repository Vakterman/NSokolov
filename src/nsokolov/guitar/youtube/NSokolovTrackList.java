package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntityTrack;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.interfaces.IOnNetworkStateChangeHandler;
import nsokolov.guitar.logic.ErrorState;
import nsokolov.guitar.logic.InternetConnectionChecker;
import nsokolov.guitar.logic.NetworkStateChangedReceiver;
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

public class NSokolovTrackList extends Activity implements IContext<YoutubeEntityTrack>,IOnNetworkStateChangeHandler {

	private LinearLayout _lessonPanel; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.track_list);
		
		_lessonPanel = (LinearLayout)findViewById(R.id.skype_lesson_panel);
		
		NetworkStateChangedReceiver networkStateChangedReceiver = new NetworkStateChangedReceiver(this);
		registerReceiver(networkStateChangedReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
		
		
		if(InternetConnectionChecker.CheckInternetConnection())
		{
			LoadLessonsPanel();
			
		}
		else
		{
			LoadNetworkWarningPanel();
		}
		
		
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
		Intent intent = new Intent(NSokolovTrackList.this, YoutubePlayLessonActivity.class);
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
		Intent intent = new Intent(NSokolovTrackList.this, YoutubePlayLessonActivity.class);
		Bundle b = new Bundle();
		b.putString("videoCode", trackId);
		intent.putExtras(b);
		startActivity(intent);
	}
	
	private void LoadNetworkWarningPanel()
	{
		_lessonPanel.removeAllViews();
		
		_lessonPanel.addView(LoadPanel(R.layout.network_errors,new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				ShowNetworkDialog();
				return false;
			}
		}));
	}
	
	@SuppressLint("InflateParams")
	private LinearLayout LoadPanel(int resourceId, OnTouchListener onTouchListener)
	{
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		
		LinearLayout layout = (LinearLayout) inflater.inflate(resourceId, null);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;
		LayoutParams layoutParams  = new LayoutParams(width, 50);
		layout.setLayoutParams(layoutParams);
		
		if(onTouchListener != null)
		{
			layout.setOnTouchListener(onTouchListener);
		}
	
		return layout;
	}
	
	public void StartNSokolovLessons()
	{
		String url = "http://nnsokolov.ru/skype";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	private void ShowNetworkDialog()
	{
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this).
				setIcon(android.R.drawable.ic_dialog_alert)
				.setMessage(R.string.network_error_dialog_text)
				.setTitle(R.string.network_dialog_title)
				.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 MakeNetworkRequest();
			}
		})
		.setNegativeButton(R.string.network_dialog_negative_button,null)
		.setCancelable(true);
	    dlgAlert.create().show();
	}
	
	private void LoadLessonsPanel()
	{
		_lessonPanel.removeAllViews();
		
		_lessonPanel.addView(LoadPanel(R.layout.custom_title,new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				StartNSokolovLessons();
				
				return false;
			}
		} ));
	}
	
	private void MakeNetworkRequest()
	{
		RequestTrackListTask trackListTask = new RequestTrackListTask(GetPlayListId(), new IHandleTaskResult<YoutubeEntityTrack>() {
			
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
		MakeNetworkRequest();
	}


	@Override
	public void OnNetworkStateDisconnected() {
		// TODO Auto-generated method stub
		LoadNetworkWarningPanel();
	}
}
