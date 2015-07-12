package nsokolov.guitar.youtube;

import android.content.res.Configuration;

public final class ResolutionManager {
	
	public static class Rectangle{
		public int _width;
		public int _height;
		
		public Rectangle(int width, int height)
		{
			_width = width;
			_height = height;
		}
	}
	
	public static Rectangle GetPlayListRectArea(int screen_resolution)
	{
		if(screen_resolution == Configuration.SCREENLAYOUT_SIZE_NORMAL || screen_resolution == Configuration.SCREENLAYOUT_SIZE_LARGE){
			return new Rectangle(320, 250);
		}
	
		//it's by default 
		return new Rectangle(190, 200);
	
	}
	
	
	public static int GetFontSize(int screen_resolution){
		if(screen_resolution == Configuration.SCREENLAYOUT_SIZE_NORMAL || screen_resolution == Configuration.SCREENLAYOUT_SIZE_LARGE){
			return 18;
		}
	
		//it's by default 
		return 14;
	}
	
	
	public static int GetColumnWidthForGridPlaylists(int screen_resolution){
		if(screen_resolution == Configuration.SCREENLAYOUT_SIZE_NORMAL || screen_resolution == Configuration.SCREENLAYOUT_SIZE_LARGE){
			return 330;
		}
	
		//it's by default 
		return 200;
	}
}
