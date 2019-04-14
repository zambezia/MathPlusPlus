package com.zambezia.mathplusplus.helper;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.preference.PreferenceManager;

import com.zambezia.mathplusplus.App;
import com.zambezia.mathplusplus.CalculatorType;

//import com.palmtronix.explorez.v1.Enums;
//import com.palmtronix.explorez.v1.exception.LocationInvalidException;

//import org.apache.common.codec.digest.DigestUtils;

import java.io.File;

public final class PreferenceHelper {

	private Context m_appContext;

	public static final String PREF_HOME_DIR = "homeDir";
	public static final String PREF_SECUREDELTE_MODE = "secureDeletedMode";
	public static final String PREF_SDCARD_OPTIONS = "sdCardOptions";
	public static final String PREF_SHOW_DIRS_FIRST = "showDirsFirst";
	public static final String PREF_SHOW_HIDDEN = "showHiddenEntries";
	public static final String PREF_SHOW_SYSFILES = "showSysFiles";
	public static final String PREF_SORT_DIR = "sort.dir";
	public static final String PREF_SORT_FIELD = "sort.field";
	public static final String PREF_USE_BACK_BUTTON = "useBackButton";
	private static final String PREF_NAVIGATE_FOCUS_ON_PARENT = "focusOnParent";
	public static final String PREF_USE_QUICKACTIONS = "useQuickActions";
	public static final String PREF_ZIP_ENABLE = "zipEnable";
	public static final String PREF_ZIP_USE_ZIP_FOLDER = "useZipFolder";
	public static final String PREF_ZIP_LOCATION = "zipLocation";
	public static final String PREF_MEDIA_EXCLUSIONS = "media_exclusions";
	public static final String PREF_SHOW_CHECKSUM = "showChecksumInProperties";
	public static final String PREF_USE_LONGCLICK_MENU = "useLongClickMenu";

	public static final String PREF_SHRED_ALGO = "shred.algo";
	public static final String PREF_NUMBER_SHRED_PASSES = "number_of_passes";
	public static final String PREF_PIN = "app.pin";
	public static final String CONFIG_PINSETUP_ACK = "pinsetup.ack";
	public static final String CONFIG_RATING_REQUESTED = "rating.requested";
	public static final String LIC_SHRED_JOB_COUNT = "shredjob.count";
	public static final String CURRENT_CALCULATOR = "calculator.current";

	private String EULA_PREFIX = "eula_";

	public PreferenceHelper(Context context) {
		m_appContext = context;

	}

	public boolean isShowDirsOnTop() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_SHOW_DIRS_FIRST, true);
	}

	public boolean isShowHidden() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_SHOW_HIDDEN, true);
	}

	public boolean useBackNavigation() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_USE_BACK_BUTTON, true);
	}

	public boolean useQuickActions() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_USE_QUICKACTIONS, true);
	}

//	public Enums.SortField getSortField() {
//		String field = PreferenceManager.getDefaultSharedPreferences(m_appContext)
//				.getString(PREF_SORT_FIELD, Enums.SortField.MTIME.toString());
//		return Enums.SortField.toEnumVal(field);
//	}
//
//	public Enums.ShredAlgo getShredAlgorithm(){
//
//		String sortAlgo = PreferenceManager.getDefaultSharedPreferences(m_appContext)
//				.getString(PREF_SHRED_ALGO, Enums.ShredAlgo.ZERO_FILLER.toString());
//		return Enums.ShredAlgo.toEnumVal(sortAlgo);
//	}
//
//	public int getShreddingPasses(){
//		String val = PreferenceManager.getDefaultSharedPreferences(m_appContext).
//				getString(PREF_NUMBER_SHRED_PASSES, "1");
//		return Integer.parseInt(val);
//	}
//
//	public Enums.SortOrder getSortDir() {
//		String field = PreferenceManager.getDefaultSharedPreferences(m_appContext)
//				.getString(PREF_SORT_DIR, Enums.SortOrder.DESCENDING.toString());
//		return Enums.SortOrder.toEnumVal(field);
//	}
//
//	public boolean setSortOrderAndField(Enums.SortField field, Enums.SortOrder order){
//		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(m_appContext).edit();
//		editor.putString(PREF_SORT_DIR, order.toString());
//		editor.putString(PREF_SORT_FIELD, field.toString());
//		return editor.commit();
//	}

	public CalculatorType getCurentCalculatorType() {
		String strCallType = PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getString(CURRENT_CALCULATOR, "");
		return CalculatorType.stringToEnum(strCallType);
	}

	public void setCurrentCalculatorType(CalculatorType currentCalculatorType){
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(m_appContext).edit();
		editor.putString(CURRENT_CALCULATOR, currentCalculatorType.toString());
		editor.apply();
		return;
	}
	public File getDefaultDirectory() {
		String dirPath = PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getString(PREF_HOME_DIR, "/");
		File homeDir = new File(dirPath);
		return homeDir;
	}


	public boolean isShowSystemFiles() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_SHOW_SYSFILES, false);
	}

//	public File getZipDestinationDir() throws LocationInvalidException {
//		String dirPath = PreferenceManager
//				.getDefaultSharedPreferences(m_appContext).getString(
//						PREF_ZIP_LOCATION, "/sdcard/zipped");
//		Boolean useZipFolder = PreferenceManager.getDefaultSharedPreferences(
//				m_appContext).getBoolean(PREF_ZIP_USE_ZIP_FOLDER, false);
//
//		if (!useZipFolder) {
//			return null;
//		}
//		File dir = new File(dirPath);
//
//		if (dir.exists() && dir.isDirectory()) {
//			return dir;
//		} else if (!dir.exists()) {
//			dir.mkdirs();
//			return dir;
//		} else {
//			throw new LocationInvalidException(dirPath);
//		}
//	}


	public boolean isEnableSdCardOptions() {

		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_SDCARD_OPTIONS, true);
	}

	public boolean focusOnParent() {

		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_NAVIGATE_FOCUS_ON_PARENT, true);
	}

	public boolean isZipEnabled() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_ZIP_ENABLE, true);
	}

	public boolean isMediaExclusionEnabled() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_MEDIA_EXCLUSIONS, false);
	}

	public boolean isSecureDeleteOn() {
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_SECUREDELTE_MODE, false);
	}

	public boolean isShowChecksumOn(){
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_SHOW_CHECKSUM, true);
	}

	public boolean isUseLongClickMenu(){
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(PREF_USE_LONGCLICK_MENU, true);
	}

//	public boolean isEulaAccepted(){
//
//		PackageInfo versionInfo = EulaHelper.getPackageInfo(m_appContext);
//		final String eulaKey = EULA_PREFIX + versionInfo.versionCode;
//		return PreferenceManager.getDefaultSharedPreferences(m_appContext).
//				getBoolean(eulaKey, false);
//	}
//
//	public boolean doAcceptEula(){
//
//		PackageInfo versionInfo = EulaHelper.getPackageInfo(m_appContext);
//		final String eulaKey = EULA_PREFIX + versionInfo.versionCode;
//		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(m_appContext).edit();
//		editor.putBoolean(eulaKey, true);
//		return editor.commit();
//	}

	public String getPin(){
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getString(PREF_PIN, "");
	}

//	public boolean setPin(String pin){
//		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(m_appContext).edit();
//		String sha1 = DigestUtils.sha1Hex(pin);
//		editor.putString(PREF_PIN, sha1);
//		return editor.commit();
//	}

	public boolean isPinSetupAck(){
		return PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(CONFIG_PINSETUP_ACK, false);
	}

	public boolean doPinSetupAck(boolean value){
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(m_appContext).edit();
		editor.putBoolean(CONFIG_PINSETUP_ACK, true);
		return editor.commit();
	}

	public boolean isRatingRequested(){
		boolean val = PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getBoolean(CONFIG_RATING_REQUESTED, false);
		return val;
	}

	public void setRatingRequested(boolean value){
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(m_appContext).edit();
		editor.putBoolean(CONFIG_RATING_REQUESTED, value);
		editor.commit();
	}

	/**
	 * Updates the shredding volume performed by user so far, when certain threshold is achieved user
	 * is shown the rating dialog
	 */
	public void countShredJob(){
		int nShredJob = PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getInt(LIC_SHRED_JOB_COUNT, 0);
		nShredJob++;
		SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(m_appContext).edit();
		editor.putInt(LIC_SHRED_JOB_COUNT, nShredJob);
		editor.commit();
	}

	/**
	 * Check if the required shredding volume is met, if yes the api returns true
	 * @return True if threshold for shredding volume is met.
     */
	public boolean showRatingDialog(){
		if (PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getInt(LIC_SHRED_JOB_COUNT, 0) >= 3){
			return true;
		}else{
			return false;
		}
	}

	public int getTheme() {

        return App.THEME_WHITE_BLACK;
        /*
		String theme = PreferenceManager.getDefaultSharedPreferences(m_appContext)
				.getString(PREF_THEME, VALUE_THEME_WHITE);
		if (VALUE_THEME_BLACK.equalsIgnoreCase(theme)) {
			return FileExplorerApp.THEME_BLACK;
		} else if (VALUE_THEME_WHITE_BLACK.equalsIgnoreCase(theme)) {
			return FileExplorerApp.THEME_WHITE_BLACK;
		} else {
			return FileExplorerApp.THEME_WHITE;
		}
		*/
	}
}
