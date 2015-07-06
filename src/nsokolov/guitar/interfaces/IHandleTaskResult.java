package nsokolov.guitar.interfaces;

public interface IHandleTaskResult<T> {
	
	void HandleResult(Iterable<T> result);
}
