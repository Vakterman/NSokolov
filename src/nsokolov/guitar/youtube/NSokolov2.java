package nsokolov.guitar.youtube;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NSokolov2 extends Activity implements OnInitializedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nsokolov2);
		 
		
		if(CheckThereIsNoInternet())
		{
			ShowAlertDialog("Нет соединения с интернетом!");
		}
		else
		{
			StartSpecifiedYoutubePlayList();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nsokolov2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean wasRestored) {
		// TODO Auto-generated method stub
	//	if (!wasRestored) {
		//      player.cueVideo("nCgQDjiotG0");
		  //    player.play();
		 //   }
		
	}
	
	
	private boolean CheckThereIsNoInternet()
	{
		 ConnectivityManager cm =
			        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo netInfo = cm.getActiveNetworkInfo();
			    return netInfo == null || !netInfo.isConnectedOrConnecting();
	}
	
	private void ShowAlertDialog(String messageTitle)
	{
		AlertDialog.Builder  dialog = new AlertDialog.Builder(this);
		dialog.setTitle(messageTitle).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton("Ok", null);
		dialog.show();
	}
	
	private void StartSpecifiedYoutubePlayList()
	{
		Intent intent = new Intent(NSokolov2.this, PlayListsExplorerActivity.class);
		startActivity(intent);
	}
}
