package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Splash extends Activity{
	private MediaPlayer mp1;
	private final int SPLASH_TIME_OUT = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(Splash.this , MainActivity.class);
				startActivity(intent);
				
				finish();
				
			}
		}, SPLASH_TIME_OUT);
		mp1 = MediaPlayer.create(this, R.raw.splashsound);
	    mp1.start ();	
	    Log.d ("Splash", "LauncherActivity.onCreate - created MediaPlayer");
	}

}
