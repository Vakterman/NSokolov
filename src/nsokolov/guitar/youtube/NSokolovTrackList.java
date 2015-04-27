package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.IHandleTaskResult;

import com.google.gdata.data.youtube.VideoEntry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NSokolovTrackList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.track_list);
		
		RequestTrackListTask trackListTask = new RequestTrackListTask(GetPlayListId(), new IHandleTaskResult<Iterable<VideoEntry>>() {
			
			@Override
			public void HandleResult(Iterable<VideoEntry> result) {
				// TODO Auto-generated method stub
				InitVideoEntriesList(result);
			}
		});
		
		trackListTask.execute();
	}
	
	
	private String GetPlayListId()
	{
		Intent intent = getIntent();
		
		Bundle extras = intent.getExtras();
		String playListId = (String) extras.get("playListId");
		intent.putExtras(extras);
		return playListId;
	}
	
	private void  InitVideoEntriesList(Iterable<VideoEntry> result)
	{
		YoutubeThumbnailsListViewAdapter thumbnailLvAdapter;
		List<VideoEntry> listVideoEntry = (List<VideoEntry>)result;
		
		thumbnailLvAdapter = new YoutubeThumbnailsListViewAdapter(this,listVideoEntry,R.layout.youtube_thumb_item);
		
		final ListView listView = (ListView)findViewById(R.id.track_list);
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
	
	
	private void StartSelectedLesson(String videoTag)
	{
		Intent intent = new Intent(NSokolovTrackList.this, YoutubePlayLessonActivity.class);
		Bundle b = new Bundle();
		b.putString("videoCode",videoTag); //Your id
		intent.putExtras(b); //Put your id to your next Intent
		startActivity(intent);
	}
}
