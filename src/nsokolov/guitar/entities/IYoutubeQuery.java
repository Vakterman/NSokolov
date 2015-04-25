package nsokolov.guitar.entities;

public interface IYoutubeQuery<T> {
	Iterable<T> GetResult();
	String GetQueryUrl();
	void SetResult(Iterable<T> result);
}

