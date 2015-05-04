package nsokolov.guitar.youtube;

import nsokolov.guitar.entities.YoutubePlaylist;
import nsokolov.guitar.entities.YoutubeQueriePlayList;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PlayListsActivity extends Activity {
			
		private ListView mListView = null;
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			
			
			 // We'll define a custom screen layout here (the one shown above), but
	         // typically, you could just use the standard ListActivity layout.
	        // setContentView(R.layout.activity_playlists)
			
			setContentView(R.layout.activity_playlists);
		
			LinearLayout lessonPanel = (LinearLayout)findViewById(R.id.skype_lesson_panel);
			lessonPanel.addView(LoadPanel());
			
			 
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
				
				  StartTrackListTask(playlist.GetId());		 
				  }
				});
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
		
		
		public void SetListViewAdapter(YoutubePlayListAdapter youtubePlayListAdapter){
			mListView.setAdapter(youtubePlayListAdapter);
		}
		
		
		public void StartTrackListTask(String playListId)
		{
			Intent intent = new Intent(PlayListsActivity.this, NSokolovTrackList.class);
			Bundle b = new Bundle();
			b.putString("playListId", playListId);
			intent.putExtras(b);
			startActivity(intent);
		}
}
