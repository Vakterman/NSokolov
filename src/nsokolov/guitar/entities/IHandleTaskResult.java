package nsokolov.guitar.entities;

public interface IHandleTaskResult<T> {
	
	void HandleResult(T result);
}
