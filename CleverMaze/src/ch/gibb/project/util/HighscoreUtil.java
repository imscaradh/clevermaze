package ch.gibb.project.util;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

public class HighscoreUtil {
	private static final String PREFS_NAME = "HIGHSCORE";
	private SharedPreferences mSettings;
	private static HighscoreUtil singleton;

	private HighscoreUtil(Context context) {
		mSettings = context.getSharedPreferences(PREFS_NAME, 0);
	}

	public static synchronized HighscoreUtil getInstance(Context context) {
		if (singleton == null) {
			singleton = new HighscoreUtil(context);
		}
		return singleton;
	}

	public void persist(String key, String value) {
		SharedPreferences.Editor editor = mSettings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public Map<String, ?> loadAll() {
		return mSettings.getAll();
	}

	public void clear() {
		mSettings.edit().clear().commit();
	}

}
