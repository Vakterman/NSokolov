package nsokolov.guitar.entities;

public class JSONSectionNotFound extends Exception {

	private String _sectionName = "";
	
	public JSONSectionNotFound(String name)
	{
		_sectionName = name;
	}
	
	public String GetSectionName()
	{
		return _sectionName;
	}
}
