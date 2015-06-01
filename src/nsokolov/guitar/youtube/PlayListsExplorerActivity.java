package nsokolov.guitar.youtube;

import nsokolov.guitar.entities.YoutubeQueriePlayList;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class PlayListsExplorerActivity extends Activity implements IContext {
	
	private GridView _playListExplorer;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		 // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
       // setContentView(R.layout.activity_playlists)
		
		setContentView(R.layout.nsokolov_playlists);
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
}
