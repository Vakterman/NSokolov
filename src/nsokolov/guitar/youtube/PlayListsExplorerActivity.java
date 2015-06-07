package nsokolov.guitar.youtube;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.entities.YoutubeQueriePlayList;
import nsokolov.guitar.interfaces.IContext;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
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

public class PlayListsExplorerActivity extends Activity implements IContext {
	
	private GridView _playListExplorer;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		 // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
       // setContentView(R.layout.activity_playlists)
		setContentView(R.layout.nsokolov_playlists);
		LinearLayout lessonPanel = (LinearLayout)findViewById(R.id.skype_lesson_panel);
		lessonPanel.addView(LoadPanel());
		
		
		_playListExplorer = (GridView) findViewById(R.id.playlists_explorer);
		
		 
		final YoutubeQueriePlayList youtubeQueryPlayList = new  YoutubeQueriePlayList();
		
		
		RequestPlayListTask playListTask = new RequestPlayListTask(youtubeQueryPlayList, this);
		playListTask.execute();
		
		adjustGridView();
	}
	
	private void  adjustGridView(){
		_playListExplorer.setNumColumns(GridView.AUTO_FIT);
		_playListExplorer.setStretchMode(GridView.NO_STRETCH);
	}

	@Override
	public Context getContext() {
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
	
	private LinearLayout LoadPanel()
	{
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService
			      (this.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.custom_title, null);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;
		LayoutParams layoutParams  = new LayoutParams(width, 50);
		layout.setLayoutParams(layoutParams);
		layout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				String url = "http://nnsokolov.ru/skype";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
				return false;
			}
		});
		return layout;
	}

}
