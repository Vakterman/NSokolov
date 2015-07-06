package nsokolov.guitar.youtube;

import com.google.common.collect.Lists;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.entities.YoutubeQueriePlayList;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.interfaces.IOnNetworkStateChangeHandler;
import nsokolov.guitar.logic.ErrorState;
import nsokolov.guitar.logic.InternetConnectionChecker;
import nsokolov.guitar.logic.NetworkStateChangedReceiver;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.DownloadManager.Query;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

public class PlayListsExplorerActivity extends Activity implements IContext<YoutubeEntity>, IOnNetworkStateChangeHandler {
	
	private GridView _playListExplorer;
	private LinearLayout _lessonPanel; 
	private ErrorState _errorState = ErrorState.AllFine;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
			super.onCreate(savedInstanceState);
		
		 // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
       // setContentView(R.layout.activity_playlists)
		setContentView(R.layout.nsokolov_playlists);
		
		
		_lessonPanel = (LinearLayout)findViewById(R.id.skype_lesson_panel);
		_playListExplorer = (GridView)findViewById(R.id.playlists_explorer);
		
		NetworkStateChangedReceiver networkStateChangedReceiver = new NetworkStateChangedReceiver(this);
		registerReceiver(networkStateChangedReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
		
		if(InternetConnectionChecker.CheckInternetConnection())
		{
			LoadLessonsPanel();
			
			QueryPlayListCollection();
			
			AdjustGridView();
		}
		else
		{
			LoadNetworkWarningPanel();
			SetErrorState(ErrorState.NetWorkProblems);
		}
		
	}
	
	private void  AdjustGridView(){
		_playListExplorer.setNumColumns(GridView.AUTO_FIT);
		_playListExplorer.setStretchMode(GridView.NO_STRETCH);
	}

	@Override
	public Context GetContext() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void SetAdapter(BaseAdapter adapter) {
		// TODO Auto-generated method stub
		_playListExplorer.setAdapter(adapter);
	}
	
	public void OnPlayListClik(YoutubeEntity playList){
		  StartTrackListTask(playList.GetId());	
	}
	
	public void StartTrackListTask(String playListId)
	{   	
		Intent intent = new Intent(PlayListsExplorerActivity.this, NSokolovTrackList.class);
		Bundle b = new Bundle();
		b.putString("playListId", playListId);
		intent.putExtras(b);
		startActivity(intent);
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
	
	public  IContext<YoutubeEntity> getIContext(){
		return this;
	}
	
	public void StartNSokolovLessons()
	{
		String url = "http://nnsokolov.ru/skype";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	@Override
	public void OnNetworkWorkStateConnected() {
		// TODO Auto-generated method stub
		LoadLessonsPanel();
		
		if(_errorState == ErrorState.NetWorkProblems)
		{
			QueryPlayListCollection();
		}
	}

	@Override
	public void OnNetworkStateDisconnected() {
		// TODO Auto-generated method stub
		LoadNetworkWarningPanel();
		SetErrorState(ErrorState.NetWorkProblems);
	}

	public  void  QueryPlayListCollection()
	{
		
		final YoutubeQueriePlayList youtubeQueryPlayList = new  YoutubeQueriePlayList();
		
		
		RequestPlayListTask playListTask = new RequestPlayListTask(youtubeQueryPlayList, new IHandleTaskResult<YoutubeEntity>() {
			
			@Override
			public void HandleResult(Iterable<YoutubeEntity> result) {
				if(result != null)
				{
					// TODO Auto-generated method stub
					SetAdapter(new YoutubePlayListAdapter(getIContext(), R.layout.youtube_thumb_item,Lists.newArrayList(result)));
					SetErrorState(ErrorState.AllFine);
				}
				else
				{
					SetErrorState(ErrorState.NetWorkProblems);
				}
			}
		});
		
		playListTask.execute();
	}
	
	private void SetErrorState(ErrorState errorState)
	{
		_errorState = errorState;
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
				QueryPlayListCollection();
			}
		})
		.setNegativeButton(R.string.network_dialog_negative_button,null)
		.setCancelable(true);
	    dlgAlert.create().show();
	}
}
