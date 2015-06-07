package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.entities.YoutubeEntityTrack;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.interfaces.IHandleTaskResult;
import nsokolov.guitar.logic.VideoEntityItemAdapter;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NSokolovTrackList extends Activity implements IContext<YoutubeEntityTrack> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.track_list);
		
		LinearLayout lessonPanel = (LinearLayout)findViewById(R.id.skype_lesson_panel);
		lessonPanel.addView(LoadPanel());
		
		RequestTrackListTask trackListTask = new RequestTrackListTask(GetPlayListId(), new IHandleTaskResult<Iterable<YoutubeEntityTrack>>() {
			
			@Override
			public void HandleResult(Iterable<YoutubeEntityTrack> result) {
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
	
	private void  InitVideoEntriesList(Iterable<YoutubeEntityTrack> result)
	{
		
		List<YoutubeEntityTrack> listVideoEntry = (List<YoutubeEntityTrack>)result;
		
		VideoEntityItemAdapter videoEntityItemAdapter = new YoutubeTrackListAdapter(this,R.layout.youtube_track_item,listVideoEntry);
		
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
	
	private LinearLayout LoadPanel()
	{
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
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


	@Override
	public void OnPlayListClik(YoutubeEntityTrack track) {
		// TODO Auto-generated method stub
		StartTrackListTask(track.GetVideoCode());
	}


	@Override
	public Context getContext() {
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
	
}
