package com.zambezia.mathplusplus;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.PowerManager;

import com.zambezia.mathplusplus.singleton.PreferenceHelperSingleton;

public class App extends Application {

	private static final String TAG = App.class.getSimpleName();
	private Intent fileAttachIntent;
	private static Context context;
	private static Intent s_batteryMonitor;
	private static PowerManager.WakeLock s_wakeLock;
//	public static final int THEME_BLACK = R.style.Theme_FileExplorer;
//	public static final int THEME_WHITE = R.style.Theme_FileExplorer_Light;
	public static final int THEME_WHITE_BLACK = android.R.style.Theme_Holo_Light_DarkActionBar;
	
	
	public static final String ACTION_OPEN_BOOKMARK = "com.palmtronix.shreddit.v1.action.OPEN_BOOKMARKS";
	public static final String ACTION_OPEN_FOLDER = "com.palmtronix.shreddit.v1.action.OPEN_FOLDER";
	public static final String EXTRA_IS_PICKER = "com.palmtronix.shreddit.v1.extra.IS_PICKER";
	public static final int REQ_PICK_FILE = 10;
	public static final int REQ_PICK_BOOKMARK = 11;
	public static final String EXTRA_SELECTED_BOOKMARK = "com.palmtronix.shreddit.v1.extra.SELECTED_BOOKMARK";
	public static final String EXTRA_FOLDER = "com.palmtronix.shreddit.v1.extra.FOLDER";

	public static Context getContext(){
		return App.context;
	}

	public static Resources R(){

		return getContext().getResources();
	}

	public Intent getFileAttachIntent() {
		return fileAttachIntent;
	}

	public void setFileAttachIntent(Intent fileAttachIntent) {
		this.fileAttachIntent = fileAttachIntent;
	}

	public static Intent getBatteryMonitor(){
		if (s_batteryMonitor == null){//First time creation
			s_batteryMonitor = context.registerReceiver(null,
					new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		}
		return s_batteryMonitor;
	}

	/**
	 * Get (or create if not already created) wake lock instance for the Application context
	 * @return return static instance of PowerManager.WakeLock
	 */
	public static PowerManager.WakeLock getWakeLock(){
		if (s_wakeLock == null){
			PowerManager powerManager = (PowerManager)getContext().getSystemService(Context.POWER_SERVICE);
			s_wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
		}
		return s_wakeLock;
	}

	public void onCreate() {
		super.onCreate();
		App.context = getApplicationContext();
		PreferenceHelperSingleton.initialize(App.context);
		/*TODO: It is not good to acquire wake lock here, there is no mechanism to release this
		wakelock. As Application class offers no onDestroy() or onKill() overrides.*/
		//getWakeLock().acquire();
	}

}
