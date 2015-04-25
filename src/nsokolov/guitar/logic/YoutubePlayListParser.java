package nsokolov.guitar.logic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import nsokolov.guitar.entities.YoutubePlaylist;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.util.Xml;

public class YoutubePlayListParser {
	 public List<YoutubePlaylist> parse(String in) throws XmlPullParserException, IOException {
       
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(in));
            return extractPlayListEntries(parser);
    }
    
    
    public List<YoutubePlaylist> extractPlayListEntries(XmlPullParser readyToReadingParser) throws XmlPullParserException, IOException
    {
    	 List<YoutubePlaylist> entries = new ArrayList<YoutubePlaylist>();

    	 while (readyToReadingParser.next() != XmlPullParser.END_DOCUMENT) {
    		  if (readyToReadingParser.getEventType() == XmlPullParser.START_TAG && readyToReadingParser.getName().equals("entry") ) {
        			  YoutubePlaylist youtubePlayList =  extractYouTubePlayListEntry(readyToReadingParser);
        			  entries.add(youtubePlayList);
    	        }
    	 }
    	 
    	 return entries;
    }
    
    public YoutubePlaylist extractYouTubePlayListEntry(XmlPullParser readyToReadingParser) throws XmlPullParserException, IOException
    {
    	String playListId = "";
		String playlistName = "";
		String defaultImageLink = "";
		Bitmap imageBm = null;
		
    	while(!(readyToReadingParser.getEventType() == XmlPullParser.END_TAG && readyToReadingParser.getName().equals("entry")))
    	{
    		
    		if(readyToReadingParser.getEventType() == XmlPullParser.START_TAG && readyToReadingParser.getName().equals("yt:playlistId"))
    		{
    			playListId =  extractText(readyToReadingParser);
    		}
    		
    		if(readyToReadingParser.getEventType() == XmlPullParser.START_TAG && readyToReadingParser.getName().equals("title"))
    		{
    			playlistName =  extractText(readyToReadingParser);
    		}
    		
    		
    		if(readyToReadingParser.getEventType() == XmlPullParser.START_TAG && readyToReadingParser.getName().equals("media:thumbnail"))
    		{
    			defaultImageLink =  readyToReadingParser.getAttributeValue(null, "url");
    			imageBm = GetImageByLink(defaultImageLink, 250,250);
    		}
    		
    		
    		
    		readyToReadingParser.next();
    	}
    	
    	YoutubePlaylist playListResult = new YoutubePlaylist(playListId, playlistName, imageBm);
    	
    	return playListResult;
    }
    
    
    public String extractText(XmlPullParser readyToReadingParser) throws XmlPullParserException, IOException
    {
    	String result = "";
    	if(readyToReadingParser.next() == XmlPullParser.TEXT)
    	{
    		result = readyToReadingParser.getText();
    		readyToReadingParser.getText();
    	}
    	
    	return result;
    }
    
    
    
    
    private Bitmap GetImageByLink(String link,int width, int height)
	{
		Bitmap bm = null;
		try {
            URL aURL = new URL(link);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
            
            return resizeBitmapBySpecifiedWidth(bm,250);
       } catch (IOException e) {
           Log.e("LoadImage", "Error getting bitmap", e);
       }
		
		
		return bm;
	}
    
    private Bitmap resizeBitmapBySpecifiedWidth(Bitmap oldBitmap, int width)
    {
    	int oldWidth = oldBitmap.getWidth();
    	int oldHeight = oldBitmap.getHeight();
    	
    	if(width > oldWidth) width = oldWidth;

    	
    	float scaleWidth =  ((float)width)/oldWidth;
    	float scaleHeight = scaleWidth;
    	
    	Matrix matrix = new Matrix();
    	
    	matrix.postScale(scaleWidth, scaleHeight);
    	return Bitmap.createBitmap(oldBitmap, 0, 0, width, (int)(scaleHeight*oldHeight),matrix, false);
    	
    }
}
