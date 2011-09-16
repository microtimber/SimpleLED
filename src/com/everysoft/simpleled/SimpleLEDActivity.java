/*
 * SimpleLED
 * Copyright (C) 2010 EverySoft
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.everysoft.simpleled;

import com.droidled.demo.DroidLED;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SimpleLEDActivity extends Activity implements OnClickListener {
	SharedPreferences prefs;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		if (prefs.getBoolean("enabled", false)) {
			try {
				DroidLED dl = new DroidLED();
				dl.enable(!dl.isEnabled());
			} catch (Exception e) {
				Log.e("SimpleLED", e.getMessage());
			}
			finish();
		}
		else {
			setContentView(R.layout.main);
			findViewById(R.id.accept).setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View arg0) {
		SharedPreferences.Editor pref_edit = prefs.edit();
		pref_edit.putBoolean("enabled", true);
		pref_edit.commit();
		finish();
	}
}