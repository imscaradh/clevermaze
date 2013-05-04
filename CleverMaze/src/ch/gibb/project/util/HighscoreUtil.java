package ch.gibb.project.util;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

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

	public String getByKey(String key) {
		return mSettings.getString(key, null);
	}

	private Map<String, ?> loadAll() {
		return mSettings.getAll();
	}

	public void clear() {
		mSettings.edit().clear().commit();
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> loadAllSorted() {
		Map<String, String> entries = (Map<String, String>) loadAll();
		Map<String, String> sortedMap = new TreeMap<String, String>(
				Collections.reverseOrder());
		sortedMap.putAll(entries);
		return sortedMap;
	}

	public int getPosition(String key) {
		Map<String, String> entries = loadAllSorted();
		int i = 1;
		for (String keyFromList : entries.keySet()) {
			if (key.equals(keyFromList)) {
				return i;
			}
			i++;
		}
		return 0;
	}

}
