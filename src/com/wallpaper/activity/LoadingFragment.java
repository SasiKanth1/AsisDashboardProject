package com.wallpaper.activity;

import sasikanth.isas.dash.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class LoadingFragment extends SherlockFragment {

	public static final String TAG = "RetryFragment";

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.wallpaper_fragment_loading, null, false);
	}
}
