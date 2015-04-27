package nsokolov.guitar.youtube;

import com.google.gdata.data.docs.Feature;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class BaseCustomeTitleActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		
		
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.);
	}
}
