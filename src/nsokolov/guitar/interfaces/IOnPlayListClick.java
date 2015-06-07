package nsokolov.guitar.interfaces;

import nsokolov.guitar.entities.YoutubeEntity;


public interface IOnPlayListClick<T extends YoutubeEntity> {
		public void OnPlayListClik(T playList);
}
