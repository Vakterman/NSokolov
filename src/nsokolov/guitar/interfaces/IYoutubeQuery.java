package nsokolov.guitar.interfaces;

public interface IYoutubeQuery<T> {
	Iterable<T> GetResult();
	String GetQueryUrl();
	void SetResult(Iterable<T> result);
	int GetSizeConfiguration();
}

