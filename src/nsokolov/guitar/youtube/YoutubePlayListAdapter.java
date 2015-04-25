package nsokolov.guitar.youtube;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import nsokolov.guitar.entities.YoutubePlaylist;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gdata.data.youtube.VideoEntry;

public class YoutubePlayListAdapter extends ArrayAdapter<YoutubePlaylist> {

	private String _TAG = "loadImageExceptions";
	
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
		
		LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(R.layout.youtube_thumb_item,parent,false);
		
		
		YoutubePlaylist youtubePlayList =	_playListCollection.get(position);

		linearLayout.addView(createImageViewByImgLink(youtubePlayList.GetImage()));
		linearLayout.addView(createSpecifiedTextViewByText(youtubePlayList.GetPlayListName()));
		
		return linearLayout;
	}
	
		
	public int getCount() {
	        // TODO Auto-generated method stub
	        return _playListCollection.size();
	}
	 
	 
	 public long getItemId(int position) {

		 return position;
	 }
	 
	 @Override
	public YoutubePlaylist getItem(int position) {
		// TODO Auto-generated method stub
		return _playListCollection.get(position);
	}
	 
	private ImageView createImageViewByImgLink(Bitmap image)
	{
		ImageView imgView = new ImageView(m_context);
		imgView.setImageBitmap(image == null? GetDefaultBitmap(): image);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imgView.setLayoutParams(params);
		return imgView;
	}
	
	private TextView createSpecifiedTextViewByText(String text)
	{
		TextView textView = new TextView(m_context);
		textView.setText(text);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textView.setLayoutParams(params);
		textView.setTextSize(21);
		return textView;
	}
	
	private Bitmap GetDefaultBitmap()
	{
		Bitmap bm = null;
		bm = BitmapFactory.decodeResource(m_context.getResources(), R.drawable.acoustic_guitar);
		return bm;
	}
}
