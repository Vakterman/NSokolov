package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubePlaylist;
import nsokolov.guitar.entities.YoutubeQueriePlayList;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PlayListsActivity extends Activity {
			
		private ListView mListView = null;
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			
			 // We'll define a custom screen layout here (the one shown above), but
	         // typically, you could just use the standard ListActivity layout.
	        // setContentView(R.layout.activity_playlists);
			setContentView(R.layout.activity_playlists);
			final YoutubeQueriePlayList youtubeQueryPlayList = new  YoutubeQueriePlayList();
			
			
			RequestPlayListTask playListTask = new RequestPlayListTask(youtubeQueryPlayList, this);
			playListTask.execute();
			
			mListView = (ListView)findViewById(R.id.playlist);
			
			mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
				  YoutubePlaylist playlist = (YoutubePlaylist)mListView.getItemAtPosition(position);
				  RequestTrackListTask requestPlayListTask = new RequestTrackListTask(playlist.GetId());
				  requestPlayListTask.execute();
				}
				
			});
		}
		
		
		
		
		
		public void SetListViewAdapter(YoutubePlayListAdapter youtubePlayListAdapter){
			mListView.setAdapter(youtubePlayListAdapter);
		}
}
