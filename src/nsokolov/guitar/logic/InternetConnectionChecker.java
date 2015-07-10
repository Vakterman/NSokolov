package nsokolov.guitar.logic;

import java.io.IOException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public final  class InternetConnectionChecker {
	
	public static Boolean CheckInternetConnection(){
		  Runtime runtime = Runtime.getRuntime();
		    try {

		        Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
		        int     exitValue = ipProcess.waitFor();
		        return (exitValue == 0);

		    } catch (IOException e)          { e.printStackTrace(); } 
		      catch (InterruptedException e) { e.printStackTrace(); }

		    return false;
	} 
}
