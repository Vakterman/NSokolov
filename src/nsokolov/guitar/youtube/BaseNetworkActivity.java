package nsokolov.guitar.youtube;

import nsokolov.guitar.interfaces.IOnNetworkStateChangeHandler;
import nsokolov.guitar.logic.InternetConnectionChecker;
import nsokolov.guitar.logic.NetworkStateChangedReceiver;
import nsokolov.guitar.logic.Utilities;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
public class BaseNetworkActivity extends Activity implements IOnNetworkStateChangeHandler{
	
	private int _resourceId;
	protected GridView _playListExplorer;
	protected RelativeLayout _lessonPanel; 
	
	public BaseNetworkActivity(int resourceId)
	{
		_resourceId = resourceId;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(_resourceId);

		_lessonPanel = (RelativeLayout)findViewById(R.id.skype_lesson_panel);
		_playListExplorer = (GridView)findViewById(R.id.playlists_explorer);
		
		int reslution_type = new Utilities(this).GetSizeCategory();
		_playListExplorer.setColumnWidth(ResolutionManager.GetColumnWidthForGridPlaylists(reslution_type));
		
		NetworkStateChangedReceiver networkStateChangedReceiver = new NetworkStateChangedReceiver(this);
		registerReceiver(networkStateChangedReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
		
		if(InternetConnectionChecker.CheckInternetConnection())
		{
			LoadLessonsPanel();
			
			RetryNetworkOperation();
		}
		else
		{
			LoadNetworkWarningPanel();
		}
	}
	
	protected void LoadLessonsPanel()
	{
		_lessonPanel.removeAllViews();
		
		_lessonPanel.addView(LoadPanel(R.layout.custom_title,new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				StartNSokolovLessons();
				
				return false;
			}
		} ));
	}
	
	protected void StartNSokolovLessons()
	{
		String url = "http://nnsokolov.ru/skype";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}
	
	protected void ShowNetworkDialog()
	{
		AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this).
				setIcon(android.R.drawable.ic_dialog_alert)
				.setMessage(R.string.network_error_dialog_text)
				.setTitle(R.string.network_dialog_title)
				.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				RetryNetworkOperation();
			}
		})
		.setNegativeButton(R.string.network_dialog_negative_button,null)
		.setCancelable(true);
	    dlgAlert.create().show();
	}
	
	protected void LoadNetworkWarningPanel()
	{
		_lessonPanel.removeAllViews();
		
		_lessonPanel.addView(LoadPanel(R.layout.network_errors,new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				ShowNetworkDialog();
				return false;
			}
		}));
	}
	
	@SuppressLint("InflateParams")
	private LinearLayout LoadPanel(int resourceId, OnTouchListener onTouchListener)
	{
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		
		LinearLayout layout = (LinearLayout) inflater.inflate(resourceId, null);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;
		LayoutParams layoutParams  = new LayoutParams(width, 60);
		layout.setLayoutParams(layoutParams);
		
		if(onTouchListener != null)
		{
			layout.setOnTouchListener(onTouchListener);
		}
	
		return layout;
	}
	
	protected void RetryNetworkOperation() {}

	@Override
	public void OnNetworkWorkStateConnected() {
		// TODO Auto-generated method stub
		RetryNetworkOperation();
	}

	@Override
	public void OnNetworkStateDisconnected() {
		// TODO Auto-generated method stub
		LoadNetworkWarningPanel();
	}
}
