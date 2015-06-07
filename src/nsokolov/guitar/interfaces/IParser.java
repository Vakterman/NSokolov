package nsokolov.guitar.interfaces;

import java.util.List;

import nsokolov.guitar.entities.YoutubeEntity;

public interface IParser<T extends YoutubeEntity> {
	public  List<T> Parse(String jsonResponse);
}
