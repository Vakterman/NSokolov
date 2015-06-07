package nsokolov.guitar.logic;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;
import nsokolov.guitar.interfaces.IContext;
import nsokolov.guitar.youtube.R;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class VideoEntityItemAdapter<T extends YoutubeEntity> extends ArrayAdapter<YoutubeEntity> {

	protected int _resourceId;
	protected List<T> _playListCollection;
	protected IContext m_Context;
	public VideoEntityItemAdapter(IContext context, int textViewResourceId, List<T> objects) {
		super(context.getContext(), textViewResourceId);
		// TODO Auto-generated constructor stub
		_resourceId = textViewResourceId;
		m_Context = context;
		_playListCollection = objects;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater = (LayoutInflater)m_Context.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(_resourceId,parent,false);
		
		
		T youtubeEntity =	_playListCollection.get(position);
		
		CreateView(linearLayout, youtubeEntity);
		
		linearLayout.setTag(youtubeEntity);
		
		linearLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				m_Context.OnPlayListClik((T)v.getTag());
			}
		});
		return linearLayout;
	}
	
	protected abstract void CreateView(ViewGroup view,YoutubeEntity youtubeEntity);
	
	public int getCount() {
	        // TODO Auto-generated method stub
	        return _playListCollection.size();
	}
	 
	 
	 public long getItemId(int position) {

		 return position;
	 }
	 
	 @Override
	public YoutubeEntity getItem(int position) {
		// TODO Auto-generated method stub
		return _playListCollection.get(position);
	}
	 
	 protected ImageView createImageViewByImgLink(Bitmap image)
	{
		ImageView imgView = new ImageView(m_Context.getContext());
		imgView.setImageBitmap(image == null? GetDefaultBitmap(): image);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imgView.setLayoutParams(params);
		return imgView;
	}
	
	protected TextView createSpecifiedTextViewByText(String text)
	{
		TextView textView = new TextView(m_Context.getContext());
		textView.setText(text);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER_VERTICAL;
		params.leftMargin = 10;
		textView.setLayoutParams(params);
		textView.setTextSize(18);
		return textView;
	}
	
	protected Bitmap GetDefaultBitmap()
	{
		Bitmap bm = null;
		bm = BitmapFactory.decodeResource(m_Context.getContext().getResources(), R.drawable.acoustic_guitar);
		return bm;
	}
}
