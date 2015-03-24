package nsokolov.guitar.youtube;

import java.util.ArrayList;
import java.util.List;

import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.gdata.data.youtube.VideoEntry;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class YoutubeThumbnailsListViewAdapter extends ArrayAdapter<VideoEntry> {

	private Context m_context;
	private List<VideoEntry> m_VideoEntrys;
	
	
	public YoutubeThumbnailsListViewAdapter(Context context, List<VideoEntry> videoEntrys,
			int list) {
		
		super(context, list,videoEntrys);
		// TODO Auto-generated constructor stub
		m_context = context;
		m_VideoEntrys = videoEntrys;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	    LayoutInflater layoutInflater = (LayoutInflater)m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.youtube_thumb_item,parent,false);
		YouTubeThumbnailView thumbnailVIew = new YouTubeThumbnailView(m_context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		thumbnailVIew.setLayoutParams(params);
		
		thumbnailVIew.initialize(AndroidDeveloperKeys.DeveloperKey, new ThumbnailInitializeListener());
		thumbnailVIew.setTag(m_VideoEntrys.get(position).getId());
		linearLayout.addView(thumbnailVIew);
		
		
		TextView textView = new TextView(m_context);
		textView.setText(m_VideoEntrys.get(position).getTitle().getPlainText());
		textView.setLayoutParams(params);
		linearLayout.addView(textView);
		
		
		linearLayout.setTag(thumbnailVIew.getTag());
		return linearLayout;
	}
	
	 @Override
	    public int getCount() {
	        // TODO Auto-generated method stub
	        return m_VideoEntrys.size();
	    }
	 
	 
	 public long getItemId(int position) {

		 return position;
		 }

}
