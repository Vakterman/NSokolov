package nsokolov.guitar.entities;

public class JSONSectionNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
