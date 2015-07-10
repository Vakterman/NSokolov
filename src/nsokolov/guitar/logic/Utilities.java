package nsokolov.guitar.logic;

import android.content.Context;
import android.content.res.Configuration;

public class Utilities {
	
	private Context _context;
	
	public Utilities(Context context)
	{
		_context = context;
	}
	
	public int  GetSizeCategory()
	{
		if ((_context.getResources().getConfiguration().screenLayout & 
			    Configuration.SCREENLAYOUT_SIZE_MASK) == 
			        Configuration.SCREENLAYOUT_SIZE_LARGE) {
			    // on a large screen device ...
				return  Configuration.SCREENLAYOUT_SIZE_LARGE;
			}
		
		if ((_context.getResources().getConfiguration().screenLayout & 
			    Configuration.SCREENLAYOUT_SIZE_MASK) == 
			        Configuration.SCREENLAYOUT_SIZE_NORMAL) {
			    // on a large screen device ...
				return  Configuration.SCREENLAYOUT_SIZE_NORMAL;
			}
		
		return  Configuration.SCREENLAYOUT_SIZE_SMALL;
	}
}
