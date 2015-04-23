package nsokolov.guitar.youtube;

import java.util.List;

import nsokolov.guitar.entities.YoutubePlaylist;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.google.gdata.data.youtube.VideoEntry;

public class YoutubePlayListAdapter extends ArrayAdapter<YoutubePlaylist> {

	private Context m_context;
	private List<YoutubePlaylist> _playListCollection;
	public YoutubePlayListAdapter(Context context, int textViewResourceId,
			List<YoutubePlaylist> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		m_context = context;
		_playListCollection  = objects;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = (LayoutInflater)m_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		ImageView imgView = new ImageView(m_context);
		
		
		
		return super.getView(position, convertView, parent);
	}
}
