package nsokolov.guitar.logic;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import nsokolov.guitar.entities.YoutubePlaylist;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class YoutubePlayListParser {
	 // We don't use namespaces
    private static final String ns = null;
    
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
    	String contentLink = "";
		String playlistName = "";
		String defaultImage = "";
    	while(!(readyToReadingParser.getEventType() == XmlPullParser.END_TAG && readyToReadingParser.getName().equals("entry")))
    	{
    		
    		if(readyToReadingParser.getEventType() == XmlPullParser.START_TAG && readyToReadingParser.getName().equals("content"))
    		{
    			contentLink =  readyToReadingParser.getAttributeValue(null, "src");
    		}
    		
    		if(readyToReadingParser.getEventType() == XmlPullParser.START_TAG && readyToReadingParser.getName().equals("title"))
    		{
    			playlistName =  extractText(readyToReadingParser);
    		}
    		
    		
    		if(readyToReadingParser.getEventType() == XmlPullParser.START_TAG && readyToReadingParser.getName().equals("media:thumbnail"))
    		{
    			defaultImage =  readyToReadingParser.getAttributeValue(null, "url");
    		}
    		
    		readyToReadingParser.next();
    	}
    	
    	YoutubePlaylist playListResult = new YoutubePlaylist(contentLink, playlistName, defaultImage);
    	
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
}
