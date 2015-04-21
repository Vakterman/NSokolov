package nsokolov.guitar.youtube;

public class YoutubeQueryMgr {
	
	public String  _urlAdress = "http://gdata.youtube.com/feeds/api/users/username/playlists?v=2";
	public enum ObjectOptions {
		XmlObjectOption,
		JsonObjectOption
	}
	
	public ObjectOptions _objectOption = ObjectOptions.JsonObjectOption;
	
	
	
	public void  GetPlaylists()
	{
		 
	}

}
