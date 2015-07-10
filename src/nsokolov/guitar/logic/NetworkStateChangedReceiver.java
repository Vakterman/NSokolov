package nsokolov.guitar.logic;

import nsokolov.guitar.interfaces.IOnNetworkStateChangeHandler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkStateChangedReceiver extends BroadcastReceiver{
			
			private IOnNetworkStateChangeHandler _networkStateChangeHandler;
			
			
			public NetworkStateChangedReceiver(IOnNetworkStateChangeHandler networkStateChangeHandler) {
				// TODO Auto-generated constructor stub
				_networkStateChangeHandler = networkStateChangeHandler;
			}
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				if(intent.getExtras()!=null) 
				{	
			        if(InternetConnectionChecker.CheckInternetConnection()) 
			        {
			        	_networkStateChangeHandler.OnNetworkWorkStateConnected();
			        } 
			        else 
			        {
			        	_networkStateChangeHandler.OnNetworkStateDisconnected();
			            Log.d("NSokolovGuitar","There's no network connectivity");
			        }
			    }
			}
}
