package com.leo.floatheaderview;

import android.content.Context;
import android.widget.ScrollView;

public class CustomerScrollerView extends ScrollView {
	OnScrollChangerListener mListener;

	public CustomerScrollerView(Context context) {
		super(context);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mListener != null) {
			mListener.onScrollChanged(l, t, oldl, oldt);
		}
	}

	public void setOnScrollChangerListener(OnScrollChangerListener listener) {
		mListener = listener;
	}

	interface OnScrollChangerListener {
		void onScrollChanged(int l, int t, int oldl, int oldt);
	}
}
