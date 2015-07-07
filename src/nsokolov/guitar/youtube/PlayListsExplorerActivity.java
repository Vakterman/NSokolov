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
import nsokolov.guitar.logic.Utilities;
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

public class PlayListsExplorerActivity extends BaseNetworkActivity implements IContext<YoutubeEntity> {
	
	public PlayListsExplorerActivity() {
		super(R.layout.nsokolov_playlists);
		// TODO Auto-generated constructor stub
	}
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);	
	}
	
	@Override
	public void RetryNetworkOperation() {
		// TODO Auto-generated method stub
		super.RetryNetworkOperation();
		QueryPlayListCollection();
		AdjustGridView();
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
		Intent intent = new Intent(PlayListsExplorerActivity.this, YoutubeTrackListActivity.class);
		Bundle b = new Bundle();
		b.putString("playListId", playListId);
		intent.putExtras(b);
		startActivity(intent);
	}
	
	public  IContext<YoutubeEntity> getIContext(){
		return this;
	}

	public  void  QueryPlayListCollection()
	{
		
		final YoutubeQueriePlayList youtubeQueryPlayList = new  YoutubeQueriePlayList(new Utilities(this).GetSizeCategory());
		
		
		RequestPlayListTask playListTask = new RequestPlayListTask(youtubeQueryPlayList, new IHandleTaskResult<YoutubeEntity>() {
			
			@Override
			public void HandleResult(Iterable<YoutubeEntity> result) {
				if(result != null)
				{
					// TODO Auto-generated method stub
					SetAdapter(new YoutubePlayListAdapter(getIContext(), R.layout.youtube_thumb_item,Lists.newArrayList(result)));
					LoadLessonsPanel();
				}
			}
		});
		
		playListTask.execute();
	}
}
