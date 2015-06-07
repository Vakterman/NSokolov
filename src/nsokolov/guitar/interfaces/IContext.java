package nsokolov.guitar.interfaces;

import nsokolov.guitar.entities.YoutubeEntity;
import android.content.Context;
import android.widget.BaseAdapter;

public interface IContext<T extends YoutubeEntity> extends IOnPlayListClick<T> {
		Context getContext();
		void SetAdapter(BaseAdapter adapter);
}
